package Controller;

import DAO.ConditionMail_DAO;
import DAO.Hose_DAO;
import Model.ConditionMail;
import Model.Hose;
import Model.Selected_Condition;
import Model.ConditionComponent;
import javafx.collections.ObservableList;

public class ConditionMail_BLL {
	public static void InsertConditionMail(ConditionMail c)
	{
		 ConditionMail_DAO.InsertConditionMail(c);
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
		
		
		return c;
	}
	public static String NotifyStock(String idStock)
	{
		String rs="\n"+idStock;
		Hose n= Hose_DAO.GetStock(idStock);
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
