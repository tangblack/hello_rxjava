package com.tangblack.hellorxjava.thread;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class CallMethod1
{
	private static String sayHi(String string)
	{
		System.out.println("sayHi" + " - " + Thread.currentThread().getName());
		
		List<String> result = new ArrayList<String>();
		
		rx.Observable.just("Jack")
//				.observeOn(Schedulers.newThread()) // 開啟新 Thread 觀察結果。無這行就會在 main 等待。
				.subscribe(new Action1<String>()
						{
							@Override
						    public void call(String string) 
							{
								try
								{
									Thread.sleep(10000);
								}
								catch (InterruptedException e)
								{
									e.printStackTrace();
								}
								
								System.out.println("Action1" + " - " + Thread.currentThread().getName());
								
								result.add("Hi " + string);
						    }
						});
		
		return result.get(0);
	}
	
	
	
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		System.out.println("main" + " - " + Thread.currentThread().getName());
		
		System.out.println("main " + sayHi("Mack") + " - " + Thread.currentThread().getName()); // 同步！！
		
		
//		Print result:
//		main - main
//		sayHi - main
//		Action1 - main
//		main Hi Jack - main
	}

}
