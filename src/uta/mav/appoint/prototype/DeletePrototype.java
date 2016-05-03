package uta.mav.appoint.prototype;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DeletePrototype extends SQLPrototypeCmd implements
		RDBCmdPrototype, Cloneable {

	private Map<String, Object> whereParams;
	private String tableName;

	public DeletePrototype(Map<String, Object> params) {
		this.whereParams = params;
	}

	public RDBCmdPrototype clone() throws CloneNotSupportedException {
		Map<String, Object> newMap = new HashMap<String, Object>();
		return new DeletePrototype(newMap);
	}

	@Override
	public void queryDB() {
		try {
			StringBuilder command = new StringBuilder("DELETE FROM ");
			command.append(tableName).append(" WHERE ");
			for (Entry<String, Object> entry : whereParams.entrySet()) {
				command.append(entry.getKey()).append(" = ").append("? AND ");
			}
			command.delete(command.length() - 4, command.length());

			PreparedStatement statement = conn.prepareStatement(command
					.toString());
			int i = 1;
			for (Entry<String, Object> entry : whereParams.entrySet()) {
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
	public void processResult() {}
}
