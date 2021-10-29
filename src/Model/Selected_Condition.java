package Model;

public class Selected_Condition {
	public String Exchange;
	public String IdStock;
	public int PriceKL;
	public int PriceSell;
	public int PriceBuy;
	public int Amount;
	public int DTNN;
	public Selected_Condition(String exchange, String idStock,int priceKL,int priceSell,int priceBuy
			,int amount,int dtnn)
	{
		Exchange=exchange;
		IdStock=idStock;
		PriceKL=priceKL;
		PriceSell=priceSell;
		PriceBuy=priceBuy;
		Amount=amount;
		DTNN=dtnn;
	}
	public Selected_Condition()
	{
		
	}
}
