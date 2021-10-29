package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.HNX;
import Model.UPCOM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UPCOM_DAO {
public static ObservableList<UPCOM> findAll(){
		
		Connection conn = MySQLConnection.connectDb();
		ObservableList<UPCOM> data = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM upcom";
			PreparedStatement stm = conn .prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {

				UPCOM ck = new UPCOM();
				ck.setId(rs.getString(1));
				ck.setRefer(rs.getDouble(2));
				ck.setCeiling(rs.getDouble(3));
				ck.setFloor(rs.getDouble(4));
				ck.setBuy_Price3(rs.getDouble(5));
				ck.setBuy_Amount3(rs.getDouble(6));
				ck.setBuy_Price2(rs.getDouble(7));
				ck.setBuy_Amount2(rs.getDouble(8));
				ck.setBuy_Price1(rs.getDouble(9));
				ck.setBuy_Amount1(rs.getDouble(10));
				ck.setUpDownOrder(rs.getDouble(11));
				ck.setOrder_Price(rs.getDouble(12));
				ck.setOrder_Amount(rs.getDouble(13));
				ck.setTotalAmount(rs.getDouble(14));
				ck.setSell_Price1(rs.getDouble(15));
				ck.setSell_Amount1(rs.getDouble(16));
				ck.setSell_Price2(rs.getDouble(17));
				ck.setSell_Amount2(rs.getDouble(18));
				ck.setSell_Price3(rs.getDouble(19));
				ck.setSell_Amount3(rs.getDouble(20));
				ck.setHigh(rs.getDouble(21));
				ck.setLow(rs.getDouble(22));
				ck.setTime(rs.getString(23));
				ck.setTotal_buy(rs.getDouble(24));
				data.add(ck);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	public void insert(UPCOM ck) {
		try {
			Connection con = MySQLConnection.connectDb();
			String query = "Insert Into upcom(id,tc,tran,san,mua_gia3,mua_kl3,mua_gia2,mua_kl2,"
					+ "mua_gia1,mua_kl1,khoplenh_hieuso,khoplenh_gia,khoplenh_kl,"
					+ "khoplenh_tongkl,ban_gia1,ban_kl1,ban_gia2,ban_kl2,ban_gia3,ban_kl3,cao,thap,time,total_buy) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, ck.getId());
			stm.setDouble(2, (ck.getRefer()));
			stm.setDouble(3, (ck.getCeiling()));
			stm.setDouble(4, (ck.getFloor()));
			stm.setDouble(5, (ck.getBuy_Price3()));
			stm.setDouble(6, (ck.getBuy_Amount3()));
			stm.setDouble(7, (ck.getBuy_Price2()));
			stm.setDouble(8, (ck.getBuy_Amount2()));
			stm.setDouble(9, (ck.getBuy_Price1()));
			stm.setDouble(10, (ck.getBuy_Amount1()));
			stm.setDouble(11, (ck.getUpDownOrder()));
			stm.setDouble(12, (ck.getOrder_Price()));
			stm.setDouble(13, (ck.getOrder_Amount()));
			stm.setDouble(14, (ck.getTotalAmount()));
			stm.setDouble(15, (ck.getSell_Price1()));
			stm.setDouble(16, (ck.getSell_Amount1()));
			stm.setDouble(17, (ck.getSell_Price2()));
			stm.setDouble(18, (ck.getSell_Amount2()));
			stm.setDouble(19, (ck.getSell_Price3()));
			stm.setDouble(20, (ck.getSell_Amount3()));
			stm.setDouble(21, (ck.getHigh()));
			stm.setDouble(22, (ck.getLow()));
			stm.setString(23, (ck.getTime()));
			stm.setDouble(24, (ck.getTotal_buy()));			
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
public static ObservableList<UPCOM> findTop(){
		
		Connection conn = MySQLConnection.connectDb();
		ObservableList<UPCOM> data = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM `upcom` ORDER BY tc DESC LIMIT 5";
			PreparedStatement stm = conn .prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {

				UPCOM ck = new UPCOM();
				ck.setId(rs.getString(1));
				ck.setRefer(rs.getDouble(2));
				ck.setCeiling(rs.getDouble(3));
				ck.setFloor(rs.getDouble(4));
				ck.setBuy_Price3(rs.getDouble(5));
				ck.setBuy_Amount3(rs.getDouble(6));
				ck.setBuy_Price2(rs.getDouble(7));
				ck.setBuy_Amount2(rs.getDouble(8));
				ck.setBuy_Price1(rs.getDouble(9));
				ck.setBuy_Amount1(rs.getDouble(10));
				ck.setUpDownOrder(rs.getDouble(11));
				ck.setOrder_Price(rs.getDouble(12));
				ck.setOrder_Amount(rs.getDouble(13));
				ck.setTotalAmount(rs.getDouble(14));
				ck.setSell_Price1(rs.getDouble(15));
				ck.setSell_Amount1(rs.getDouble(16));
				ck.setSell_Price2(rs.getDouble(17));
				ck.setSell_Amount2(rs.getDouble(18));
				ck.setSell_Price3(rs.getDouble(19));
				ck.setSell_Amount3(rs.getDouble(20));
				ck.setHigh(rs.getDouble(21));
				ck.setLow(rs.getDouble(22));
				ck.setTime(rs.getString(23));
				ck.setTotal_buy(rs.getDouble(24));
				data.add(ck);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
public void update(UPCOM ck) {
	try {
		Connection con = MySQLConnection.connectDb();
		String query = "UPDATE `upcom` SET `tc`=?,`tran`=?,`san`=?,`mua_gia3`=?,"
				+ "`mua_kl3`=?,`mua_gia2`=?,`mua_kl2`=?,`mua_gia1`=?,`mua_kl1`=?,"
				+ "`khoplenh_hieuso`=?,`khoplenh_gia`=?,`khoplenh_kl`=?,`khoplenh_tongkl`=?,"
				+ "`ban_gia1`=?,`ban_kl1`=?,`ban_gia2`=?,`ban_kl2`=?,`ban_gia3`=?,"
				+ "`ban_kl3`=?,`cao`=?,`thap`=?,`time`=?,`total_buy`=? WHERE id='"+ck.getId()+"'";
		PreparedStatement stm = con.prepareStatement(query);
//		stm.setString(1, (ck.getId()));
		stm.setDouble(1, (ck.getRefer()));
		stm.setDouble(2, (ck.getCeiling()));
		stm.setDouble(3, (ck.getFloor()));
		stm.setDouble(4, (ck.getBuy_Price3()));
		stm.setDouble(5, (ck.getBuy_Amount3()));
		stm.setDouble(6, (ck.getBuy_Price2()));
		stm.setDouble(7, (ck.getBuy_Amount2()));
		stm.setDouble(8, (ck.getBuy_Price1()));
		stm.setDouble(9, (ck.getBuy_Amount1()));
		stm.setDouble(10, (ck.getUpDownOrder()));
		stm.setDouble(11, (ck.getOrder_Price()));
		stm.setDouble(12, (ck.getOrder_Amount()));
		stm.setDouble(13, (ck.getTotalAmount()));
		stm.setDouble(14, (ck.getSell_Price1()));
		stm.setDouble(15, (ck.getSell_Amount1()));
		stm.setDouble(16, (ck.getSell_Price2()));
		stm.setDouble(17, (ck.getSell_Amount2()));
		stm.setDouble(18, (ck.getSell_Price3()));
		stm.setDouble(19, (ck.getSell_Amount3()));
		stm.setDouble(20, (ck.getHigh()));
		stm.setDouble(21, (ck.getLow()));
		stm.setString(22, (ck.getTime()));
		stm.setDouble(23, (ck.getTotal_buy()));			
		stm.executeUpdate();
		stm.close();
		con.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
	}
}
