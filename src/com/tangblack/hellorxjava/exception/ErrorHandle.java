package com.tangblack.hellorxjava.exception;

import rx.Observable;
import rx.Subscriber;

public class ErrorHandle
{
	private static Observable<Integer> createObervale()
	{
		return Observable.create(new Observable.OnSubscribe<Integer>()
		{
			@Override
			public void call(Subscriber<? super Integer> observer)
			{
				try
				{
					if (!observer.isUnsubscribed())
					{
						for (int i = 1; i < 10; i++)
						{
							if (i%2 == 0) // 若雙數就會發生錯誤
							{
								throw new RuntimeException("i=" + i);
							}
							observer.onNext(i); // 從0呼叫到4
						}
						
						observer.onCompleted(); // 最後呼叫 onCompleted
					}
				}
				catch (Exception e)
				{
					if (!observer.isUnsubscribed())
					{
						observer.onError(e);
					}
				}
			}
		});
	}
	
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		Observable<Integer> myObservable = createObervale();
		myObservable.subscribe(new Subscriber<Integer>()
		{
			@Override
			public void onNext(Integer item)
			{
				System.out.println("Next: " + item);
			}

			@Override
			public void onError(Throwable error)
			{
				System.err.println("Error: " + error.getMessage());
			}

			@Override
			public void onCompleted()
			{
				System.out.println("Sequence complete.");
			}
		});
		
//		Output:
//		Next: 1
//		Error: i=2
	}
}
