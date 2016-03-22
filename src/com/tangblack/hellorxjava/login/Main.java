package com.tangblack.hellorxjava.login;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func1;

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
		WebApi webApi = new WebApi();
		
		
		// 傳統寫法
		System.out.println("傳統寫法");
		webApi.getToken("user", "password", new DefaultHandler()
		{
			@Override
			public void onSuccess(String response)
			{
				webApi.getMessageList(response, new DefaultHandler()
				{
					// Callback Hell!!
					
					@Override
					public void onSuccess(List<String> response)
					{
						for (String message : response)
						{
							System.out.println("傳統寫法：" + message); // Print!!
						}
					}
					
					@Override
					public void onFailure(Exception exception)
					{
						System.err.println("無法取得 message！");
					}
				});
			}
			
			@Override
			public void onFailure(Exception exception)
			{
				System.err.println("無法取得  token！");
			}
		});
		System.out.println("");
		
		
		
		// RxJava
		System.out.println("RxJava");
		Observable.just(webApi.getToken("user", "password"))
			.flatMap(new Func1<String, Observable<String>>()
			{
				@Override
				public Observable<String> call(String token)
				{
					try
					{
						return Observable.from(webApi.getMessageList(token));
					}
					catch (Exception e)
					{
						throw Exceptions.propagate(e); // convert them to unchecked exceptions.
					}
				}
			})
			.doOnNext(new Action1<String>()
			{
				@Override
				public void call(String s)
				{
					System.out.println("doOnNext：" + s);
				}
			})
			.subscribe(new Observer<String>() 
			{
		        @Override
		        public void onNext(String message) 
		        {
		        	System.out.println("RxJava：" + message); // Print!!
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
		
		
		System.out.println("THE END");
	}
}
