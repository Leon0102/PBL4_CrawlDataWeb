package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Email_DAO {

	public static void Insert(String email) {
		try {
			Connection con = MySQLConnection.connectDb();
			String query = "UPDATE `email` SET `Email`= ? ";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, email);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String GetEmail()
	{
		String email=" ";
		Connection conn = MySQLConnection.connectDb();
		String query="SELECT * FROM `email`";
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			rs.next();
			email= rs.getString("Email");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}
}