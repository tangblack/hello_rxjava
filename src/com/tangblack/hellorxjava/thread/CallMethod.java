package com.tangblack.hellorxjava.thread;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CallMethod
{
	private static Observable<String> sayHi(String string)
	{
		System.out.println("sayHi" + " - " + Thread.currentThread().getName());
		
		Observable<String> observable = rx.Observable.just("Jack")
			.map(new Func1<String, String>()
			{
				@Override
				public String call(String t)
				{
					try
					{
						Thread.sleep(3000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					
					System.out.println("Func1" + " - " + Thread.currentThread().getName());
					
					return "Hi " + t;
				}
			});
		
		return observable;
	}
	
	
	
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		System.out.println("main" + " - " + Thread.currentThread().getName());
		
		Observable<String> observable = sayHi("Mack"); // 會等三秒鐘！
		observable.observeOn(Schedulers.newThread())
			.subscribe(new Action1<String>()
			{
				@Override
				public void call(String t)
				{
					System.out.println("call " + t + " - " + Thread.currentThread().getName());
				}
			});
		
		
//		Print result:
//		main - main
//		sayHi - main
//		Func1 - main
//		call Hi Jack - RxNewThreadScheduler-1
	}

}
