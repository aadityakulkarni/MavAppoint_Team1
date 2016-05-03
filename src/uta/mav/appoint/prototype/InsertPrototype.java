package uta.mav.appoint.prototype;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class InsertPrototype extends SQLPrototypeCmd implements
		RDBCmdPrototype, Cloneable {

	private Map<String, Object> insertParams;
	private String tableName;

	public InsertPrototype(Map<String, Object> params) {
		this.insertParams = params;
	}

	public RDBCmdPrototype clone() throws CloneNotSupportedException {
		Map<String, Object> newMap = new HashMap<String, Object>();
		return new InsertPrototype(newMap);
	}

	@Override
	public void queryDB() {
		try {
			StringBuilder command = new StringBuilder("INSERT INTO ");
			StringBuilder valueString = new StringBuilder(" values(");
			command.append(tableName).append(" (");
			for (Entry<String, Object> entry : insertParams.entrySet()) {
				command.append(entry.getKey()).append(",");
				valueString.append("?").append(",");
			}
			command.deleteCharAt(command.length() - 1).append(")");
			valueString.deleteCharAt(valueString.length() - 1).append(")");
			command.append(valueString.toString());

			PreparedStatement statement = conn.prepareStatement(command
					.toString());
			int i = 1;
			for (Entry<String, Object> entry : insertParams.entrySet()) {
				if (entry.getValue() instanceof Integer) {
					statement.setInt(i, (Integer) entry.getValue());
				} else {
					statement.setString(i, (String) entry.getValue());
				}
			}
			statement.executeUpdate();
			res = statement.getGeneratedKeys();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Override
	public void processResult() {
		try {
			while (res.next()) {
				result.add((Integer) res.getInt(1));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
