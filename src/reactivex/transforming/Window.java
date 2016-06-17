package reactivex.transforming;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class Window
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// periodically gather items emitted by an Observable into bundles and
		// emit these bundles rather than emitting the items one at a time
		Observable.range(0, 20) // 0, 1, 2, 3, 4 ... 19
				.window(7) // 每收集七個數字才發射一次。 RxJava的window()函数和buffer()很像，但是它发射的是Observable而不是列表
				.subscribe(new Action1<Observable<Integer>>()
				{
					@Override
					public void call(Observable<Integer> observable)
					{
						observable
						.doOnSubscribe(new Action0() // doOnSubscribe在订阅时（最开始）回调，與 subscribe 同一個線程
						{
							@Override
							public void call()
							{
								System.out.println("doOnSubscribe");
							}
						})
						.doOnCompleted(new Action0() // doOnCompleted在onComplete前回调，與 subscribe 同一個線程
						{
							@Override
							public void call()
							{
								System.out.println("doOnCompleted");
								
							}
						})
						.subscribe(new Action1<Integer>()
						{
							@Override
							public void call(Integer item)
							{
								 System.out.println("Next:" + item);
							}
						});
					}
				});
	}
}
