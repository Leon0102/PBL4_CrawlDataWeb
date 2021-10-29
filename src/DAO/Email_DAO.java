package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
