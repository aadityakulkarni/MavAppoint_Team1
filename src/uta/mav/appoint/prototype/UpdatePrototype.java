package uta.mav.appoint.prototype;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UpdatePrototype extends SQLPrototypeCmd implements
		RDBCmdPrototype, Cloneable {

	private Map<String, Object> setParams;
	private Map<String, Object> whereParams;
	/**
	 * @param setParams the setParams to set
	 */
	public void setSetParams(Map<String, Object> setParams) {
		this.setParams = setParams;
	}

	/**
	 * @param whereParams the whereParams to set
	 */
	public void setWhereParams(Map<String, Object> whereParams) {
		this.whereParams = whereParams;
	}

	private String tableName;
	private int prototypeResult;

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public UpdatePrototype(Map<String, Object> setParams, Map<String, Object> whereParams) {
		this.setParams = setParams;
		this.whereParams = whereParams;
	}

	public RDBCmdPrototype clone() throws CloneNotSupportedException {
		Map<String, Object> setParams = new HashMap<String, Object>();
		Map<String, Object> whereParams = new HashMap<String, Object>();
		return new UpdatePrototype(setParams, whereParams);
	}

	@Override
	public void queryDB() {
		try {
			StringBuilder command = new StringBuilder("UPDATE ");
			command.append(tableName).append(" SET ");
			for (Entry<String, Object> entry : setParams.entrySet()) {
				command.append(entry.getKey()).append(" = ").append("? ,");
			}
			command.deleteCharAt(command.length() - 1).append(" where ");

			for (Entry<String, Object> entry : whereParams.entrySet()) {
				command.append(entry.getKey()).append(" = ").append("? ").append(" AND ");
			}
			command.delete(command.length() - 5, command.length());
			
			PreparedStatement statement = conn.prepareStatement(command
					.toString());
			int i = 1;
			for (Entry<String, Object> entry : setParams.entrySet()) {
				if (entry.getValue() instanceof Integer) {
					statement.setInt(i, (Integer) entry.getValue());
				} else {
					statement.setString(i, (String) entry.getValue());
				}
				i++;
			}
			for (Entry<String, Object> entry : whereParams.entrySet()) {
				if (entry.getValue() instanceof Integer) {
					statement.setInt(i, (Integer) entry.getValue());
				} else {
					statement.setString(i, (String) entry.getValue());
				}
				i++;
			}
			prototypeResult = statement.executeUpdate();
			//res = statement.getGeneratedKeys();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * @return the prototypeResult
	 */
	public int getPrototypeResult() {
		return prototypeResult;
	}

	/**
	 * @param prototypeResult the prototypeResult to set
	 */
	public void setPrototypeResult(int prototypeResult) {
		this.prototypeResult = prototypeResult;
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
