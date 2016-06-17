package reactivex.errorhandling;

import rx.Observable;
import rx.Subscriber;

public class Catch
{
	private static Observable<String> createObervale()
	{
		return Observable.create(new Observable.OnSubscribe<String>()
		{
			@Override
			public void call(Subscriber<? super String> observer)
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
							observer.onNext("" + i); // 從0呼叫到4
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
	
	private static Observable<String> onErrorReturnObserver() {
	    return createObervale().onErrorReturn(throwable -> "onErrorReturn"); // 当发生错误的时候，让Observable发射一个预先定义好的数据并正常地终止
	}

	private static Observable<String> onErrorResumeNextObserver() {
	    return createObervale().onErrorResumeNext( // 当发生错误的时候，由另外一个Observable来代替当前的Observable并继续发射数据
	    		Observable.just("onErrorResumeNext 7", 
	    				"onErrorResumeNext 8", 
	    				"onErrorResumeNext 9")); 

	}
	
	
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		onErrorReturnObserver().subscribe(new Subscriber<String>()
				{
					@Override
					public void onNext(String item)
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
		
		onErrorResumeNextObserver().subscribe(new Subscriber<String>()
				{
					@Override
					public void onNext(String item)
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
		
//		output:
//			Next: 1
//			Next: onErrorReturn
//			Sequence complete.
//			Next: 1
//			Next: onErrorResumeNext 7
//			Next: onErrorResumeNext 8
//			Next: onErrorResumeNext 9
//			Sequence complete.

	}
}
