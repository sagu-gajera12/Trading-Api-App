package com.Trade.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.angelbroking.smartapi.models.Gtt;
import com.angelbroking.smartapi.models.GttParams;
import com.angelbroking.smartapi.models.Order;
import com.angelbroking.smartapi.models.OrderParams;
import com.angelbroking.smartapi.models.TokenSet;
import com.angelbroking.smartapi.models.User;
import com.angelbroking.smartapi.utils.Constants;
import com.google.gson.JsonObject;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException, SmartAPIException {
		SpringApplication.run(Application.class, args);
		
System.out.println("this is running");
		
//		SmartConnect smartConnect = new SmartConnect();
//		smartConnect.setApiKey("XaMs0U1P");
//		smartConnect.setUserId("s692577");
//		User user = smartConnect.generateSession("", "");
//		System.out.println(user.toString());
//		smartConnect.setAccessToken(user.getAccessToken());
//		
//		
//		TokenSet tokenSet = smartConnect.renewAccessToken(user.getAccessToken(),
//				user.getRefreshToken());
//				smartConnect.setAccessToken(tokenSet.getAccessToken());
//				System.out.println(smartConnect.getLoginURL());
//			//	placeOrder(smartConnect);
//				//getOrder(smartConnect);
//				//getLTP(smartConnect);
//				//getTrades(smartConnect);
//			//	getRMS(smartConnect);
//				//createRule(smartConnect);
//				//getHolding(smartConnect);
//			//	modifyRule(smartConnect);
//				//cancelRule(smartConnect);
//				//ruleDetails(smartConnect);
//				//ruleList(smartConnect);
//						getCandleData(smartConnect);
//						//logout(smartConnect);
//						//System.out.println(smartConnect.getUserId());
//						//System.out.println(smartConnect.getAccessToken());
//						//getOrder(smartConnect);
	}
	public static void placeOrder(SmartConnect smartConnect) throws SmartAPIException, IOException {

		OrderParams orderParams = new OrderParams();
		orderParams.variety = "NORMAL";
		orderParams.quantity = 1;
		orderParams.symboltoken = "3045";
		orderParams.exchange = Constants.EXCHANGE_NSE;
		orderParams.ordertype = Constants.ORDER_TYPE_LIMIT;
		orderParams.tradingsymbol = "SBIN-EQ";
		orderParams.producttype = Constants.PRODUCT_DELIVERY;
		orderParams.duration = Constants.DURATION_DAY;
		orderParams.transactiontype = Constants.TRANSACTION_TYPE_BUY;
		orderParams.price = 438.2;
		orderParams.squareoff = "0";
		orderParams.stoploss = "0";

		Order order = smartConnect.placeOrder(orderParams, Constants.VARIETY_NORMAL);
		System.out.println(order.orderId);
	}
	
	public void modifyOrder(SmartConnect smartConnect) throws SmartAPIException, IOException {
		// Order modify request will return order model which will contain only

		OrderParams orderParams = new OrderParams();
		orderParams.quantity = 1;
		orderParams.ordertype = Constants.ORDER_TYPE_LIMIT;
		orderParams.tradingsymbol = "ASHOKLEY";
		orderParams.symboltoken = "3045";
		orderParams.producttype = Constants.PRODUCT_DELIVERY;
		orderParams.exchange = Constants.EXCHANGE_NSE;
	//	orderParams.duration = Constants.VALIDITY_DAY;
		orderParams.price = 122.2;

		String orderId = "201216000755110";
		//Order order = smartConnect.modifyOrder(orderId, orderParams, Constants.VARIETY_REGULAR);
	}
	public static void getOrder(SmartConnect smartConnect) throws SmartAPIException, IOException {
		System.out.println(smartConnect.getUserId());
		JSONObject orders =  smartConnect.getOrderHistory(smartConnect.getUserId());
		System.out.println(orders.length());
		 JSONArray jsonArray = orders.getJSONArray("data");  
		 System.out.println(jsonArray.toString());
		for (int i = 0; i < jsonArray.length(); i++) {
			System.out.println(jsonArray.get(i));
			
		}
	}
      static 	public void getLTP(SmartConnect smartConnect) throws SmartAPIException, IOException {
		String exchange = "NSE";
		String tradingSymbol = "SBIN-EQ";
		String symboltoken = "3045";
		JSONObject ltpData = smartConnect.getLTP(exchange, tradingSymbol, symboltoken);
		System.out.println(ltpData);
      }

      static public void getTrades(SmartConnect smartConnect) throws SmartAPIException, IOException {
	// Returns tradebook.
                    JSONObject trades = smartConnect.getTrades();
	System.out.println(trades);
      }
         static public void getRMS(SmartConnect smartConnect) throws SmartAPIException, IOException {
	// Returns RMS.
	JSONObject response = smartConnect.getRMS();
	System.out.println(response);
         }
         static public void getHolding(SmartConnect smartConnect) throws SmartAPIException, IOException {
     		// Returns Holding.
     		JSONObject response = smartConnect.getHolding();
     		System.out.println(response);
         }
		static public void createRule(SmartConnect smartConnect)throws SmartAPIException,IOException{
			GttParams gttParams= new GttParams();
			
			gttParams.tradingsymbol="SBIN-EQ";
			gttParams.symboltoken="3045";
			gttParams.exchange="NSE";
			gttParams.producttype=Constants.PRODUCT_DELIVERY;
			gttParams.transactiontype="BUY";
			gttParams.price= 100000.01;
			gttParams.qty=1;
			gttParams.disclosedqty=0;
			gttParams.triggerprice=(double) 400;
			gttParams.timeperiod=300;
			
			Gtt gtt = smartConnect.gttCreateRule(gttParams);
		}
	static 	public void modifyRule(SmartConnect smartConnect)throws SmartAPIException,IOException{
			GttParams gttParams= new GttParams();
			
			gttParams.tradingsymbol="SBIN-EQ";
			gttParams.symboltoken="3045";
			gttParams.exchange="NSE";
			gttParams.producttype=Constants.PRODUCT_DELIVERY;
			gttParams.transactiontype="BUY";
			gttParams.price= 400.0;
			gttParams.qty=1;
			gttParams.disclosedqty=0;
			gttParams.triggerprice=400.0;
			gttParams.timeperiod=300;
			
			Integer id= 3897145;
			
			Gtt gtt = smartConnect.gttModifyRule(id,gttParams);
			System.out.print(gtt.toString());
		}
	static public void cancelRule(SmartConnect smartConnect)throws SmartAPIException, IOException{
		Integer id=3897145;
		String symboltoken="3045";
		String exchange="NSE";
		
		Gtt gtt = smartConnect.gttCancelRule(id,symboltoken,exchange);
	}
	static public void ruleDetails(SmartConnect smartConnect)throws SmartAPIException, IOException{
		Integer id=3897145;
	
		JSONObject gtt = smartConnect.gttRuleDetails(id);
		System.out.println(gtt);
	}
   static public void ruleList(SmartConnect smartConnect)throws SmartAPIException, IOException{
	
	List<String> status=new ArrayList<String>(){{
		add("NEW");
		add("CANCELLED");
		add("ACTIVE");
		add("SENTTOEXCHANGE");
		add("FORALL");
		}};
	Integer page=1;
	Integer count=10;

	JSONArray gtt = smartConnect.gttRuleList(status,page,count);
	for(int i=0;i<gtt.length();i++)
	{
		System.out.println(gtt.get(i));
	}

}
   
   static public void getCandleData(SmartConnect smartConnect) throws SmartAPIException, IOException {

		JSONObject requestObejct = new JSONObject();
		requestObejct.put("exchange", "NSE");
		requestObejct.put("symboltoken", "3045");
		requestObejct.put("interval", "ONE_MINUTE");
		requestObejct.put("fromdate", "2021-09-22 09:45");
		requestObejct.put("todate", "2021-09-22 09:46");

	 String candleData = smartConnect.candleData(requestObejct);
	 System.out.println(candleData);
	}
	
	/** Logout user. */
	static public void logout(SmartConnect smartConnect) throws SmartAPIException, IOException {
		/** Logout user and kill session. */
		JSONObject jsonObject = smartConnect.logout();
		
		System.out.println(jsonObject);
		
	}
   

}
