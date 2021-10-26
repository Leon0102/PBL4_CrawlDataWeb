package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.GDTT_HNX;
import Model.GDTT_Hose;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GDTT_HNX_DAO {
public static ObservableList<GDTT_HNX> findAll(){
		
		Connection conn = MySQLConnection.connectDb();
		ObservableList<GDTT_HNX> data = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM gdtt_hnx";
			PreparedStatement stm = conn .prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {

				GDTT_HNX ck = new GDTT_HNX();
				ck.setId(rs.getString(1));
				ck.setPrice(rs.getDouble(2));
				ck.setAmount(rs.getInt(3));
				ck.setValue(rs.getDouble(4));
				ck.setTime(rs.getString(5));
				data.add(ck);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	public void insert(GDTT_HNX ck) {
		try {
			Connection con = MySQLConnection.connectDb();
			String query = "Insert Into gdtt_hnx(id,price,amount,value,time) values(?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, ck.getId());
			stm.setDouble(2, (ck.getPrice()));
			stm.setInt(3, (ck.getAmount()));
			stm.setDouble(4, ck.getValue());
			stm.setString(5, ck.getTime());
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
