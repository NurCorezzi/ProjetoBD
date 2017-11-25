package Persistencia;

import java.util.Vector;

public class DatabaseResponse {

	Vector<Vector<Object>> data;
	
	public DatabaseResponse(Vector<Vector<Object>> data) {
		this.data = data;
	}
	
	public Vector<Vector<Object>> getData() {
		return data;
	}

}
