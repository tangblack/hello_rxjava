package reactivex.utility;

import rx.Observable;
import rx.Subscriber;

public class TimeStamp
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// convert an Observable that emits items into one that emits indications of the amount of time elapsed between those emissions
		Observable.create(new Observable.OnSubscribe<Integer>()
		{
			@Override
			public void call(Subscriber<? super Integer> observer)
			{
				try
				{
					if (!observer.isUnsubscribed())
					{
						for (int i = 1; i < 5; i++)
						{
							Thread.sleep(i * 1000);
							observer.onNext(i); // 從0呼叫到4
						}
						observer.onCompleted(); // 最後呼叫 onCompleted
					}
				}
				catch (Exception e)
				{
					observer.onError(e);
				}
			}
		})
		.timestamp() // // 除了收到的事件，也加上了與收到事件的時間。
		.subscribe(new Subscriber<rx.schedulers.Timestamped<Integer>>()
		{
			@Override
			public void onNext(rx.schedulers.Timestamped<Integer> item)
			{
				System.out.println("Next: " + item.getValue() + ",此事件發生時間為" + item.getTimestampMillis());
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
