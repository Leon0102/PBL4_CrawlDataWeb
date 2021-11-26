package Controller;

import java.sql.SQLException;

import DAO.ConditionMail_DAO;
import DAO.Exchange_DAO;
import Model.ConditionMail;
import Model.Exchange;
import Model.Selected_Condition;
import Model.ConditionComponent;

public class ConditionMail_BLL {
	public static void InsertConditionMail(ConditionMail c)
	{
		try {
			if(ConditionMail_DAO.IsExistStock(c.IdStock))
				ConditionMail_DAO.UpdateConditionMail(c);
			else 
				ConditionMail_DAO.InsertConditionMail(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ConditionMail GetConditionMail(String idStock)
	{
		
		String exchange= (ConditionMail_DAO.GetSelectedCondition(idStock)).Exchange;
		ConditionComponent c_pricekl= ConditionMail_DAO.GetConditionComponent("pricekl", idStock);
		ConditionComponent c_pricesell= ConditionMail_DAO.GetConditionComponent("pricesell", idStock);
		ConditionComponent c_pricebuy= ConditionMail_DAO.GetConditionComponent("pricebuy", idStock);
		ConditionComponent c_amount= ConditionMail_DAO.GetConditionComponent("amount", idStock);
		ConditionComponent c_dtnn= ConditionMail_DAO.GetConditionComponent("dtnn", idStock);
		ConditionMail c=new ConditionMail(
				exchange,
				idStock,
				c_pricekl.Start,
				c_pricekl.End,
				c_pricesell.Start,
				c_pricesell.End,
				c_pricebuy.Start,
				c_pricebuy.End,
				c_amount.Start,
				c_amount.End,
				c_dtnn.Start,
				c_dtnn.End
				);
		
		System.out.print(c.AmountStart+"-"+c.AmountEnd);
		return c;
	}
	public static String NotifyStock(String idStock, String exchangeName)
	{
		String rs="\n"+idStock;
		Exchange n= Exchange_DAO.GetStock(idStock,exchangeName);
		Selected_Condition selected=ConditionMail_DAO.GetSelectedCondition(idStock);
		ConditionMail c=GetConditionMail(idStock);
		if(selected.PriceKL==1) {
			if(c.PriceKLStart< n.order_Price&& n.order_Price<c.PriceKLEnd )
				rs+="\n-Gia khop lenh gan nhat: "+n.order_Price;
		}
		if(selected.PriceSell==1)
		{
			double tmp=0;
			if((c.PriceSellStart<n.sell_Price1 && n.sell_Price1<c.PriceSellEnd)
				|| (c.PriceSellStart<n.sell_Price2 && n.sell_Price2<c.PriceSellEnd)
				|| (c.PriceSellStart<n.sell_Price3 && n.sell_Price3<c.PriceSellEnd))
			{
				if(c.PriceSellStart<n.sell_Price1 && n.sell_Price1<c.PriceSellEnd) tmp=n.sell_Price1;
				if(c.PriceSellStart<n.sell_Price2 && n.sell_Price2<c.PriceSellEnd) tmp=n.sell_Price2;
				if(c.PriceSellStart<n.sell_Price3 && n.sell_Price3<c.PriceSellEnd) tmp=n.sell_Price3;
				rs+="\n-Gia ban hien tai: "+String.valueOf(tmp);
			}
		}
		if(selected.PriceBuy==1)
		{
			double tmp=0;
			if((c.PriceBuyStart<n.buy_Price1 && n.buy_Price1<c.PriceBuyEnd)
				|| (c.PriceBuyStart<n.buy_Price1 && n.buy_Price1<c.PriceBuyEnd)
				|| (c.PriceBuyStart<n.buy_Price3 && n.buy_Price3<c.PriceBuyEnd))
			{
				if(c.PriceBuyStart<n.buy_Price1 && n.buy_Price1<c.PriceBuyEnd)tmp=n.buy_Price1;
				if(c.PriceBuyStart<n.buy_Price2 && n.buy_Price2<c.PriceBuyEnd) tmp=n.buy_Price2;
				if (c.PriceBuyStart<n.buy_Price3 && n.buy_Price3<c.PriceBuyEnd) tmp=n.buy_Price3;
				rs+="\n-Gia mua hien tai: "+String.valueOf(tmp);
			}
		}
		if(selected.Amount==1) {
			if(c.AmountStart< n.order_Amount&& n.order_Amount<c.AmountEnd )
				rs+="\n-Khoi luong co phieu: "+n.upDownOrder;
		}
		if(selected.DTNN==1) {
			if(c.DTNNStart< n.total_buy&& n.total_buy<c.DTNNEnd )
				rs+="\n-Khoi luong dau tu nuoc ngoai: "+n.upDownOrder;
		}
		return rs;
	}
}
