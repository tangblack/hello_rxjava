package com.tangblack.hellorxjava.timer;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;


public class Timer
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		System.out.println("Timer - " + Thread.currentThread().getName());
		
		// 執行在新的 Thread 上
//		rx.Observable.timer(5, TimeUnit.SECONDS) // 五秒後執行
		rx.Observable.interval(5, TimeUnit.SECONDS) // 每五秒執行
			.subscribe(new Subscriber<Long>()
			{
				@Override
				public void onCompleted()
				{
					System.out.println("onCompleted - " + Thread.currentThread().getName());
				}

				@Override
				public void onError(Throwable e)
				{
					System.out.println("onError");
				}

				@Override
				public void onNext(Long longObject)
				{
					System.out.println("onNext longObject=" + longObject + " -" + Thread.currentThread().getName());
				}
			});
		
		while (true)
		{
			
		}
	}
}
