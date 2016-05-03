package uta.mav.appoint.prototype;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import uta.mav.appoint.team3fall.singleton.ConfigFileReader;

public class SelectPrototype extends SQLPrototypeCmd implements
		RDBCmdPrototype, Cloneable {

	private Map<String, Object> whereParams;
	private String tableName;

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	private Object prototypeResult;

	public SelectPrototype(Map<String, Object> params) {
		this.whereParams = params;
	}

	public RDBCmdPrototype clone() throws CloneNotSupportedException {
		Map<String, Object> newMap = new HashMap<String, Object>();
		return new SelectPrototype(newMap);
	}

	public Object getPrototypeResult() {
		return prototypeResult;
	}

	public void setPrototypeResult(Object prototypeResult) {
		this.prototypeResult = prototypeResult;
	}

	@Override
	public void queryDB() {
		try {
			StringBuilder command = new StringBuilder("SELECT * FROM ");
			command.append(tableName).append(" where ");
			for (Entry<String, Object> entry : whereParams.entrySet()) {
				command.append(entry.getKey()).append(" = ? and ");
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
			res = statement.executeQuery();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Override
	public void processResult() {
		ConfigFileReader configFileReader = ConfigFileReader.getInstance();
		String className = prototypeResult.getClass().getName();
		Field[] fields = prototypeResult.getClass().getDeclaredFields();
		try {
			res.next();
			for (Field field : fields) {
				field.setAccessible(true);
				String fName = field.getName();
				Class type = field.getType();
				if(configFileReader.getValue(className
						+ "." + fName) == null){
					continue;
				}
				if (type.isPrimitive() && type.getName().contains("Integer")) {
					field.setInt(
							prototypeResult,
							res.getInt(configFileReader.getValue(className
									+ "." + fName)));
				} else if (type.getName().contains("String")) {
						field.set(
								prototypeResult,
								res.getString(configFileReader.getValue(className
										+ "." + fName)));
					}
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}