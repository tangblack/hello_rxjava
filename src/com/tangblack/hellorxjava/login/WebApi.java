package com.tangblack.hellorxjava.login;

import java.util.Arrays;
import java.util.List;

public class WebApi
{
	public void getToken(String name, String password, Handler handler)
	{
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace(); // Auto-generated catch block
		}
		
		handler.onSuccess("i_am_token");
	}
	
	public String getToken(String name, String password) 
	{
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace(); // Auto-generated catch block
		}
		
		return "i_am_token";
	}
	
	
	
	public void getMessage(String token, Handler handler)
	{
//		handler.onSuccess("Hello World!");
		handler.onFailure(new Exception("getMessage"));
	}

	public String getMessage(String token) throws Exception
	{
//		throw new Exception("getMessage");
		return "Hello World!";
	}
	
	
	
	public void getMessageList(String token, Handler handler)
	{
		handler.onSuccess(Arrays.asList(new String[]{"foo", "bar", "..."}));
	}

	public List<String> getMessageList(String token) throws Exception
	{
//		throw new Exception("getMessageList");
		return Arrays.asList(new String[]{"foo", "bar", "..."});
	}
}
