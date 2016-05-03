package uta.mav.appoint.prototype;

import java.util.HashMap;
import java.util.Map;

public class PrototypeMgr {

	private static PrototypeMgr prototypeMgr;
	private final Map<String, RDBCmdPrototype> prototypePool;
	
	private PrototypeMgr() {
		prototypePool = new HashMap<String, RDBCmdPrototype>();
	}
	
	public static PrototypeMgr getInstance() {
		
		if(prototypeMgr == null){
			synchronized (PrototypeMgr.class) {
				prototypeMgr = new PrototypeMgr();
			}
		}
		return prototypeMgr;
	}
	
	public RDBCmdPrototype get(String key){
		return prototypePool.get(key);
	}
	
	public void add(String key, RDBCmdPrototype prototype){
		prototypePool.put(key, prototype);
	}
	
	public void remove(String key, RDBCmdPrototype prototype){
		prototypePool.remove(key);
	}
	
}
