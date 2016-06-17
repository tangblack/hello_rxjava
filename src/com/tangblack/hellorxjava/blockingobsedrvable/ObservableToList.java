package com.tangblack.hellorxjava.blockingobsedrvable;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class ObservableToList
{
	private static Observable<Integer> createObservableFoo()
	{
		return Observable.just(1, 2, 3);
	}
	
	private static List<Integer> createFoo()
	{
		// https://mcxiaoke.gitbooks.io/rxdocs/content/operators/Blocking-Observable-Operators.html
		return Observable.just(1, 2, 3)
				.toList() // Observable<Integer> -> Observable<List<Integer>>
				.toBlocking() // 一个阻塞的Observable 继承普通的Observable类，增加了一些可用于阻塞Observable发射的数据的操作符。
				.single(); // 返回串列，如果Observable终止时只发射了一个值，返回那个值，否则抛出异常
	}
	
	
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// 使用舊風格：取得串列
		List<Integer> foo = createFoo();
		Observable.from(foo)
			.subscribe(item -> System.out.println("Next: " + item));
		
		
		// 使用 Observable 風格：
		createObservableFoo()
			.subscribe(new Subscriber<Integer>()
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
	}
}
