package final_Gosse_Danial_1912983;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class sqliteConnection {
	public static Connection dbConnector() {
		try {
		Class.forName("org.sqlite.JDBC");
		Connection conn =  DriverManager.getConnection("jdbc:sqlite:StudentDb.db");
		return conn;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
