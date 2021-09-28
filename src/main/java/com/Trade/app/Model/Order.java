package com.Trade.app.Model;

public class Order {

	private String producttype;
	private String ordertype;
	private String duration;
	private String variety;
	
	private String tradingsymbol;
	private String squareoff;
	private String triggerprice;
	private String stoploss;
	
	private String symboltoken;
	private String trade;
	private double price;
	private int quantity;
	private String exchange;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getVariety() {
		return variety;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public String getTradingsymbol() {
		return tradingsymbol;
	}
	public void setTradingsymbol(String tradingsymbol) {
		this.tradingsymbol = tradingsymbol;
	}
	public String getSquareoff() {
		return squareoff;
	}
	public void setSquareoff(String squareoff) {
		this.squareoff = squareoff;
	}
	public String getTriggerprice() {
		return triggerprice;
	}
	public void setTriggerprice(String triggerprice) {
		this.triggerprice = triggerprice;
	}
	public String getStoploss() {
		return stoploss;
	}
	public void setStoploss(String stoploss) {
		this.stoploss = stoploss;
	}
	public String getSymboltoken() {
		return symboltoken;
	}
	public void setSymboltoken(String symboltoken) {
		this.symboltoken = symboltoken;
	}
	
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	@Override
	public String toString() {
		return "Order [producttype=" + producttype + ", ordertype=" + ordertype + ", duration=" + duration
				+ ", variety=" + variety + ", tradingsymbol=" + tradingsymbol + ", squareoff=" + squareoff
				+ ", triggerprice=" + triggerprice + ", stoploss=" + stoploss + ", symboltoken=" + symboltoken
				+ ", trade=" + trade + ", price=" + price + ", quantity=" + quantity + ", exchange=" + exchange + "]";
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
}
