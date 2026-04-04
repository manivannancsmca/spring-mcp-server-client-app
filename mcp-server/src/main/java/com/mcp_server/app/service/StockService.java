package com.mcp_server.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class StockService {
	
	private static List<String> productList = new ArrayList<>(List.of("Phone", "Laptop"));
	

	@Tool(name = "Company_Stock_Tool", description = "Get the stock price for company. Here how stock price for the company like is attractive or expensive or cheaper ")
	public String getStockPrice(String companyName) {
		
		if (companyName.equalsIgnoreCase("infosys")) {
			return "The stock price of " + companyName + " is Rs. 100. Valuations are attractive.";
		}
		
		if (companyName.equalsIgnoreCase("TCS")) {
			return "The stock price of " + companyName + " is Rs. 200. Valuations are expensive.";
		}
		
		return "I don't have the information about " + companyName + "company.";
	}
	
	@Tool(name ="create_product_tool", description = "here we can add product or item into the list, and this list will determine the details of product")
	public void addProduct(String productName) {
		productList.add(productName);
	}
	
	@Tool(name ="view_product_list_tool", description = "here user can retrive or view all the product item")
	public List<String> viewProduct() {
		return productList;
	}
}
