package com.tangblack.hellorxjava.thread;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ThreadSwitch
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 * @see <a href="http://blog.danlew.net/2015/12/08/error-handling-in-rxjava/">Error handling in RxJava</a>
	 */
	public static void main(String[] args)
	{
		// Main
		System.out.println("ThreadSwitch - " + Thread.currentThread().getName());
		
		
		Observable.range(0, 10)
			.subscribeOn(Schedulers.newThread())
			.map(new Func1<Integer, String>()
			{
				// Thread-1
				@Override
		        public String call(Integer integer) 
				{
					System.out.println("Integer integer=" + integer + " to String - " + Thread.currentThread().getName());
					return integer.toString();
		        }
			})
//			取决于更早的 .subscribeOn( )
//			因为thread1的逻辑将会覆盖thread2，所以Observable的创建和.subscribe( )的逻辑处理都将运行在thread1中。
//			因此，根本没有必要写多个.subscribeOn( )操作符。
			.subscribeOn(Schedulers.newThread()) 
			.map(new Func1<String, Integer>()
			{
				// Still Thread-1
				@Override
		        public Integer call(String string) 
				{
					System.out.println("String string=" + string + " to Integer - " + Thread.currentThread().getName());
					return Integer.valueOf(string);
		        }
			})
			.observeOn(Schedulers.newThread())
		  	.subscribe(new Observer<Integer>() 
			{
		  		// Thread-2
			    @Override
			    public void onNext(Integer integer) 
			    {
			    	System.out.println("onNext integer=" + integer + " - " + Thread.currentThread().getName());
			    }
			
			    @Override
			    public void onCompleted()
			    {
			    	System.out.println("onCompleted - " + Thread.currentThread().getName());
			    }
			
			    @Override
			    public void onError(Throwable error)
			    {
			    	System.err.println("發生錯誤！" + error.getMessage());
			    }
			});
		
		while (true)
		{
			
		}
	}

}
