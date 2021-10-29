package Model;

public class ConditionMail {
	public String Exchange;
	public String IdStock;
	public double PriceKLStart;
	public double PriceKLEnd;
	public double PriceSellStart;
	public double PriceSellEnd;
	public double PriceBuyStart;
	public double PriceBuyEnd;
	public double AmountStart;
	public double AmountEnd;
	public double DTNNStart;
	public double DTNNEnd; 
	 
	public ConditionMail
	(String exchange,String idstock,double priceklstart,double priceklend,double pricesellstart,double pricesellend,
			double pricebuystart,double pricebuyend, double amountstart,double amountend,double dtnnstart,double dtnnend)
	{
		Exchange=exchange;
		IdStock=idstock;
		PriceKLStart=priceklstart;
		PriceKLEnd=priceklend;
		PriceSellStart=pricesellstart;
		PriceSellEnd=pricesellend;
		PriceBuyStart=pricebuystart;
		PriceBuyEnd=pricebuyend;
		AmountStart=amountstart;
		AmountEnd=amountend;
		DTNNStart=dtnnstart;
		DTNNEnd=dtnnend;
	}
	public ConditionMail()
	{
		
	}
}
