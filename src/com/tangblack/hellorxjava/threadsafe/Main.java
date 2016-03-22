package com.tangblack.hellorxjava.threadsafe;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

public class Main
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 * @see <a href="http://blog.danlew.net/2015/12/08/error-handling-in-rxjava/">Error handling in RxJava</a>
	 */
	public static void main(String[] args)
	{
		List<String> list = new ArrayList<String>();
		
		Observable.range(0, 1000000)
//			.observeOn(Schedulers.newThread())
		  	.subscribe(new Observer<Integer>() 
			{
			    @Override
			    public void onNext(Integer integer) 
			    {
			    	list.add("A_" + integer);
			    }
			
			    @Override
			    public void onCompleted()
			    {
			    	System.out.println("onCompleted");
			    }
			
			    @Override
			    public void onError(Throwable error)
			    {
			    	System.err.println("發生錯誤！" + error.getMessage());
			    }
			});
		
		Observable.range(1000000, 1000000) // Start, Count
//			.observeOn(Schedulers.newThread())
		  	.subscribe(new Observer<Integer>() 
			{
			    @Override
			    public void onNext(Integer integer) 
			    {
			    	list.add("B_" + integer);
			    }
			
			    @Override
			    public void onCompleted()
			    {
			    	System.out.println("onCompleted");
			    }
			
			    @Override
			    public void onError(Throwable error)
			    {
			    	System.err.println("發生錯誤！" + error.getMessage());
			    }
			});
		
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace(); // Auto-generated catch block
		}
		
		try
		{
			PrintWriter out = new PrintWriter(System.getProperty("user.home") + "/Desktop/output.txt");
			for (String string : list)
			{
				out.println(string);
				System.out.println(string); // Print!!
			}
			out.flush();
			out.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}
