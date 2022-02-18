package com.automation.practice;

import org.json.JSONArray;
import org.json.JSONObject;

import com.automation.practice.HandlingJson;
import com.automation.tests.BaseTest;

public class GetRequest extends BaseTest {
	
	static String response = null;
	
	public static String parseGetRequest()
	{
		try
		{
			String url = "/utilities/weather/city";
			//String authorization = "Basic XXXXXXXXXXXXX";
			String authorization = "No auth";
			
			response = HandlingJson.HttpGetResponseAsString(url, authorization);
			
			if(response == null)
			{
				System.exit(0);
			}
			else
			{
				final JSONArray json = new JSONArray(response);
				final int count = json.length();
				
				for(int i=0;i<count;i++)
				{
					final JSONObject fields = json.getJSONObject(i);
					String city = fields.getString("City");
					
					System.out.println(city);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
}
