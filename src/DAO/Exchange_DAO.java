package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Exchange_DAO {
	public static ObservableList<String> GetStockId(String exchange)
	{
		Connection conn = MySQLConnection.connectDb();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			 // NOTE	
			String query = "SELECT id FROM " + exchange;
			PreparedStatement stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			if(rs==null) return list;
			while (rs.next()) {
//				rs.getString(1);
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static ObservableList<String> GetExchangeName()
	{
		Connection conn = MySQLConnection.connectDb();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			String query = "SELECT Name FROM exchange";
			PreparedStatement stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
