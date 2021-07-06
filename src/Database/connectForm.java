package Database;
import java.sql.*;

import javax.swing.JOptionPane;

public class connectForm {
	public static Connection getConnection() throws ClassNotFoundException {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cards","root","");
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return c;
	}
}