package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.HNX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HNX_DAO {
public static ObservableList<HNX> findAll(){
		
		Connection conn = MySQLConnection.connectDb();
		ObservableList<HNX> data = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM hnx";
			PreparedStatement stm = conn .prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {

				HNX ck = new HNX();
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
	public void insert(HNX ck) {
		try {
			Connection con = MySQLConnection.connectDb();
			String query = "Insert Into hnx(id,tc,tran,san,mua_gia3,mua_kl3,mua_gia2,mua_kl2,"
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
public static ObservableList<HNX> findTop(){
		
		Connection conn = MySQLConnection.connectDb();
		ObservableList<HNX> data = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM `hnx` ORDER BY tc DESC LIMIT 30";
			PreparedStatement stm = conn .prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {

				HNX ck = new HNX();
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
public void update(HNX ck) {
	try {
		Connection con = MySQLConnection.connectDb();
		String query = "UPDATE `hnx` SET `id`='[value-1]',`tc`='[value-2]',`tran`='[value-3]',`san`='[value-4]',`mua_gia3`='[value-5]',"
				+ "`mua_kl3`='[value-6]',`mua_gia2`='[value-7]',`mua_kl2`='[value-8]',`mua_gia1`='[value-9]',`mua_kl1`='[value-10]',"
				+ "`khoplenh_hieuso`='[value-11]',`khoplenh_gia`='[value-12]',`khoplenh_kl`='[value-13]',`khoplenh_tongkl`='[value-14]',"
				+ "`ban_gia1`='[value-15]',`ban_kl1`='[value-16]',`ban_gia2`='[value-17]',`ban_kl2`='[value-18]',`ban_gia3`='[value-19]',"
				+ "`ban_kl3`='[value-20]',`cao`='[value-21]',`thap`='[value-22]',`time`='[value-23]',`total_buy`='[value-24]' WHERE id='"+ck.getId()+"'";
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
}
