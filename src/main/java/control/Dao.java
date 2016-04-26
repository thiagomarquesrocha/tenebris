package control;

import java.sql.Connection;

import org.json.JSONObject;

import model.JSONData;

public abstract class Dao {
	private Connection con;
	private JSONData data;
	
	public Dao() {
		data = new JSONData();
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public JSONData getData() {
		return data;
	}

	public abstract JSONObject add(Object... o);
	public abstract void delete(Object... o);
	public abstract JSONObject update(Object... o);
	public abstract JSONObject list(Object... args);
	public abstract JSONObject find(Object... args);
}
