package com.mycompapp.myapp.API;

import java.net.HttpURLConnection;
import java.net.URL;


public class DRConnectClinet {

	private final String USER_AGENT = "Mozilla/5.0";

	
	// HTTP GET request
		public String sendGet() throws Exception {

	        System.out.println("##########################################in sendGet################################");
			
			String url="http://domain.indiacyberspace.com/";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
           System.out.println("################connection created="+con);
			// optional default is GET
			con.setRequestMethod("GET");
			System.out.println("################connection GET method set#######");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("################connection responseCode="+responseCode);
			System.out.println("\nSending 'GET' request to URL : " + url);


			//ParseResponse parse=new ParseResponse();
			
			//return parse.getProductList(con.getInputStream());
			
			return con.getInputStream().toString();

		}

		
	
}
