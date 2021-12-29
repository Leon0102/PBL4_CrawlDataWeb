package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.GDTT_Exchange;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GDTT_Exchange_DAO {
	public static ObservableList<GDTT_Exchange> findAll(String exchangeName){
		
		Connection conn = MySQLConnection.connectDb();
		ObservableList<GDTT_Exchange> data = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM gdtt_"+exchangeName;
			PreparedStatement stm = conn .prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {

				GDTT_Exchange ck = new GDTT_Exchange();
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
	public void insert(GDTT_Exchange ck,String exchangeName) {
		try {
			Connection con = MySQLConnection.connectDb();
			String query = "Insert Into gdtt_"+exchangeName+"(id,price,amount,value,time) values(?,?,?,?,?)";
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
	public void update(GDTT_Exchange ck,String nameExchange) {
		try {
			Connection con = MySQLConnection.connectDb();
			String query = "UPDATE `gdtt_"+nameExchange+"` SET `id`=? ,`price`=?, `amount`=?, `value`=?, `time`=? WHERE id ='"+ck.getId()+"'";
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
	public static void deleteAll(String name)
    {
        String query="DELETE FROM gdtt_"+ name; // name: hnx || hose
        Connection con = MySQLConnection.connectDb();
        try {
            PreparedStatement stm=con.prepareStatement(query);
            stm.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.print(e);
        }

    }
	public static int getSumAmount(String nameExchange) {		
		try {
			Connection con = MySQLConnection.connectDb();
			String query = "SELECT SUM(amount) FROM gdtt_"+nameExchange;
			PreparedStatement stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			rs.next();			
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		}
		
		public static double getSumValue(String nameExchange) {
			try {
				Connection con = MySQLConnection.connectDb();
				String query = "SELECT SUM(value) FROM gdtt_"+nameExchange;
				PreparedStatement stm = con.prepareStatement(query);
				ResultSet rs = stm.executeQuery();
				rs.next();
				return rs.getDouble(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
			
		}
}
