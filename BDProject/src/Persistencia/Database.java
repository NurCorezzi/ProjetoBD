package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Database {

	private Connection myConn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	
	public Database() {
		myConn = null;
		myStmt = null;
		myRs = null;
	}
	
	public void connect() throws SQLException{
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/csv_db", "root", "");
		System.out.println("Database connection successful!\n");
	}
	
	public DatabaseResponse query(String query) {
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(query);
			
			ResultSetMetaData meta = myRs.getMetaData();
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			DatabaseResponse dataRes = new DatabaseResponse(data);
			
			data.add(new Vector<Object>());
			for(int i = 1; i <= meta.getColumnCount(); i++)
				data.get(0).add(meta.getColumnLabel(i));
			
			while(myRs.next()) {
				data.add(new Vector<Object>());
				int i = data.size()-1;
				
				for(int j = 1; j <= meta.getColumnCount(); j++)
					data.get(i).add(myRs.getObject(j));
			}
			
			myStmt.close();
			myRs.close();
			System.out.println("Sucessfull query");
			return dataRes;
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return null;
	}
	
}