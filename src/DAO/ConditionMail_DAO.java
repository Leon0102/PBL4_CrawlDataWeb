package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.ConditionComponent;
import Model.ConditionMail;

import Model.Selected_Condition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class ConditionMail_DAO {

	public static void InsertConditionMail(ConditionMail c)
	{
		System.out.println("Insert");
		int priceKL=0,priceSell=0,priceBuy=0,amount=0,dtnn=0;
		if(c.PriceKLStart!=c.PriceKLEnd) priceKL=1;
		if(c.PriceSellStart!=c.PriceSellEnd)priceSell=1;
		if(c.PriceBuyStart!=c.PriceBuyEnd)priceBuy=1;
		if(c.AmountStart!=c.AmountEnd)amount=1;
		if(c.DTNNStart!=c.DTNNEnd)dtnn=1;
		try {
			Connection con = MySQLConnection.connectDb();
			//
			String query = "INSERT INTO `condition_mail`(`Exchange`, `IdStock`, `PriceKL`, `PriceSell`, `PriceBuy`, `Amount`, `DTNN`)"
					+ " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, c.Exchange);
			stm.setString(2, c.IdStock);
			stm.setInt(3, priceKL);
			stm.setInt(4, priceSell);
			stm.setInt(5, priceBuy);
			stm.setInt(6, amount);
			stm.setInt(7, dtnn);
			stm.executeUpdate();
			stm.close();
			con.close();
			InsertCondition("condition_pricekl", c.IdStock, c.PriceKLStart, c.PriceKLEnd);
			InsertCondition("condition_pricesell", c.IdStock, c.PriceSellStart, c.PriceSellEnd);
			InsertCondition("condition_pricebuy", c.IdStock, c.PriceBuyStart, c.PriceBuyEnd);
			InsertCondition("condition_amount", c.IdStock, c.AmountStart, c.AmountEnd);
			InsertCondition("condition_dtnn", c.IdStock, c.DTNNStart, c.DTNNEnd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void InsertCondition(String table,String id,double start,double end) throws SQLException
	{
		Connection con = MySQLConnection.connectDb();
		String query="INSERT INTO "+table+"(`IdStock`, `Start`, `End`)"
				+ " VALUES (?,?,?)";
		 PreparedStatement stm = con.prepareStatement(query);
		 stm.setString(1, id);
		 stm.setDouble(2, start);
		 stm.setDouble(3, end);
		 stm.executeUpdate();
		 stm.close();
	}
	public static void UpdateConditionMail(ConditionMail c)
	{
		System.out.println("Update");
		int priceKL=0,priceSell=0,priceBuy=0,amount=0,dtnn=0;
		if(c.PriceKLStart!=c.PriceKLEnd) priceKL=1;
		if(c.PriceSellStart!=c.PriceSellEnd)priceSell=1;
		if(c.PriceBuyStart!=c.PriceBuyEnd)priceBuy=1;
		if(c.AmountStart!=c.AmountEnd)amount=1;
		if(c.DTNNStart!=c.DTNNEnd)dtnn=1;
		try {
			Connection con = MySQLConnection.connectDb();
			//
			String query = "UPDATE `condition_mail` SET`PriceKL`=?,`PriceSell`=?,"
					+ "`PriceBuy`=?,`Amount`=?,`DTNN`=? WHERE `IdStock` =?";
			PreparedStatement stm = con.prepareStatement(query);

			stm.setInt(1, priceKL);
			stm.setInt(2, priceSell);
			stm.setInt(3, priceBuy);
			stm.setInt(4, amount);
			stm.setInt(5, dtnn);
			stm.setString(6, c.IdStock);
			stm.executeUpdate();
			stm.close();
			con.close();

			UpdateCondition("condition_pricekl", c.IdStock, c.PriceKLStart, c.PriceKLEnd);
			UpdateCondition("condition_pricesell", c.IdStock, c.PriceSellStart, c.PriceSellEnd);
			UpdateCondition("condition_pricebuy", c.IdStock, c.PriceBuyStart, c.PriceBuyEnd);
			UpdateCondition("condition_amount", c.IdStock, c.AmountStart, c.AmountEnd);
			UpdateCondition("condition_dtnn", c.IdStock, c.DTNNStart, c.DTNNEnd);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void UpdateCondition(String table, String id,double start,double end) throws SQLException
	{
		Connection con = MySQLConnection.connectDb();
		String query="UPDATE "+table+" SET `Start`=?,`End`=? WHERE IdStock= ?";
		 PreparedStatement stm = con.prepareStatement(query); 
		 stm.setDouble(1, start);
		 stm.setDouble(2, end);
		 stm.setString(3, id);
		 stm.executeUpdate();
		 stm.close();
	}
	public static boolean IsExistStock(String idStock) throws SQLException
	{
		Connection conn = MySQLConnection.connectDb();
		String query = "SELECT IdStock FROM `condition_mail` WHERE IdStock=? ";
		PreparedStatement stm = conn.prepareStatement(query);
		stm.setString(1, idStock);
		ResultSet rs = stm.executeQuery();
		return rs.next();
	}
	public static ObservableList<String> GetIdStock()
	{
		Connection conn = MySQLConnection.connectDb();
		ObservableList<String> data = FXCollections.observableArrayList();
		try {
			String query = "SELECT IdStock FROM `condition_mail` WHERE PriceKL=1 or"
					+ " PriceSell=1 or PriceBuy=1 or Amount=1 or DTNN=1";
			PreparedStatement stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {

				String ck = new String();
				ck=rs.getString(1);
				data.add(ck);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	public static Selected_Condition GetSelectedCondition(String idStock)
	{
		Connection conn = MySQLConnection.connectDb();
		Selected_Condition data = new Selected_Condition();
		try {
			String query = "SELECT * FROM `condition_mail` WHERE (PriceKL=1 or"
					+ " PriceSell=1 or PriceBuy=1 or Amount=1 or DTNN=1) and IdStock='"+idStock+"'";
			PreparedStatement stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			rs.next();
			data = new Selected_Condition(
				rs.getString(1),
				rs.getString(2),
				rs.getInt(3),
				rs.getInt(4),
				rs.getInt(5),
				rs.getInt(6),
				rs.getInt(7)
			);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	public static ConditionComponent GetConditionComponent(String component,String idStock)
	{
		Connection conn = MySQLConnection.connectDb();
		ConditionComponent data =new ConditionComponent();
		try {
			String query = "SELECT `IdStock`, `Start`, `End` "
					+ "FROM `condition_"+component+"` WHERE IdStock='"+idStock+"'" ;		
			PreparedStatement stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {

			data = new ConditionComponent(
				rs.getString(1),
				rs.getDouble(2),
				rs.getDouble(3)
				);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
