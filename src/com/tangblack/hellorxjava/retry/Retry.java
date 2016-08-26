package com.tangblack.hellorxjava.retry;

import rx.Observable;

public class Retry
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		Observable.range(0, 100)
//			.retry(10) // 寫這邊不會讓下方的步驟 retry
			.doOnNext(i -> 
				{
					System.out.println("1st doOnNext:" + i);
					throw new RuntimeException("always fails");
				}
			)
			.doOnNext(i -> System.out.println("2rd doOnNext:" + i))
			.retry(10) // 以上的步驟都會 retry
			.subscribe();
	}
}
