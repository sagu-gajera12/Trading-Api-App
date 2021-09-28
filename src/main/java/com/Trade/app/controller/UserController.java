package com.Trade.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Trade.app.Model.Order;
import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.angelbroking.smartapi.models.OrderParams;
import com.angelbroking.smartapi.models.User;
import com.angelbroking.smartapi.utils.Constants;
import com.google.gson.Gson;

@Controller
public class UserController {
	
	SmartConnect smartConnect; 
	@GetMapping("/buysell")
	public String buysell()
	{
	return "main";
	}
	
	@GetMapping("/")
	public String login()
	{
	return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("User") com.Trade.app.Model.User user1,Model m)
	{

		System.out.println(user1.getPassword()+" "+user1.getUsername());
		final String username=user1.getUsername();
		final String password=user1.getPassword();
		 this.smartConnect = new SmartConnect();
		smartConnect.setApiKey("XaMs0U1P");
		smartConnect.setUserId(user1.getUsername());
		User user = smartConnect.generateSession(username, password);
		//System.out.println(user.toString());
		try {
	smartConnect.setAccessToken(user.getAccessToken());
	
	Order order=new Order();
	m.addAttribute("order", order);
	
	 return "main";
		}catch (Exception e) {
		
			
		
		}
		return "/";
	}
	//
	@PostMapping("/placeorder")
	public String PlaceOrder(@ModelAttribute("OrderParams") Order order,Model model)
	{
	  try {	
   
		OrderParams orderParams = new OrderParams();
		orderParams.variety = "NORMAL";
		orderParams.quantity = 1;
		orderParams.symboltoken = "3045";
		orderParams.exchange ="NSE";
		orderParams.ordertype = Constants.ORDER_TYPE_MARKET;
		orderParams.tradingsymbol = "SBIN-EQ";
		orderParams.producttype = Constants.PRODUCT_MARGIN;
		orderParams.duration = "DAY";
		orderParams.transactiontype ="BUY";
		orderParams.price = 440.0;
		orderParams.squareoff = "0";
		orderParams.stoploss = "0";
//		OrderParams orderParams = new OrderParams();
//		orderParams.variety = order.getVariety();
//		orderParams.quantity = order.getQuantity();
//		orderParams.symboltoken = order.getSymboltoken();
//		orderParams.exchange = order.getExchange();
//		orderParams.ordertype = order.getOrdertype();
//		orderParams.tradingsymbol = order.getTradingsymbol();
//		orderParams.producttype = order.getProducttype();
//		orderParams.duration = order.getDuration();
//		orderParams.transactiontype =order.getTrade() ;
//		orderParams.price=order.getPrice();
//		orderParams.squareoff = "0";
//		orderParams.stoploss = "0";
		//com.angelbroking.smartapi.models.Order order1 = smartConnect.placeOrder(orderParams, Constants.VARIETY_NORMAL);
		System.out.println(order);
        System.out.println(smartConnect.getUserId());
        
        model.addAttribute("order",order);
        
		return"redirect:/orders";
	  }catch (Exception e) {
		  
		  return "main";
	}
	}
	@RequestMapping("/orders")
	public String getOrder(Model model) throws SmartAPIException, IOException {
		System.out.println(smartConnect.getUserId());
		com.angelbroking.smartapi.models.Order order=new com.angelbroking.smartapi.models.Order();
		JSONObject orders =  smartConnect.getOrderHistory(smartConnect.getUserId());
		System.out.println(orders.length());
		try {
		 JSONArray jsonArray = orders.getJSONArray("data"); 
		 List<com.angelbroking.smartapi.models.Order> ls=new ArrayList<>();
		 for(int i=0;i<10;i++) {
			 Gson gson=new Gson();
		 
		 order=gson.fromJson(jsonArray.get(0).toString(), com.angelbroking.smartapi.models.Order.class);
		 ls.add(order);
		 System.out.println(order);
		 }
		 model.addAttribute("ls", ls);
		}catch (Exception e) {
			
		}
		return "orders";
		
	}
	
	
	@RequestMapping("/logout")
	 public String logout(){
		/** Logout user and kill session. */
		JSONObject jsonObject = smartConnect.logout();
		smartConnect=null;
		System.out.println(jsonObject);
		return "redirect:/";
		
	}
}
