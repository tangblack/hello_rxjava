package reactivex.utility;

import rx.Observable;
import rx.Subscriber;

public class TimeInterval
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
		.timeInterval() // 除了收到的事件，也加上了與上一次收到事件至今的間隔時間。
		.subscribe(new Subscriber<rx.schedulers.TimeInterval<Integer>>()
		{
			@Override
			public void onNext(rx.schedulers.TimeInterval<Integer> item)
			{
				System.out.println("Next: " + item.getValue() + ",與上一事件間隔時間相隔" + item.getIntervalInMilliseconds() + "豪秒");
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
