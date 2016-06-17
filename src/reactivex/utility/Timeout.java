package reactivex.utility;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class Timeout
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// mirror the source Observable, but issue an error notification if a particular period of time elapses without any emitted items
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
		.timeout(1500, TimeUnit.MILLISECONDS) // 超過1500毫秒沒收到就會當作逾時觸發 onError
		.timeInterval() // 取代收到的事件，取得代之的是與上一次收到事件至今的間隔時間。
		.subscribe(new Subscriber<rx.schedulers.TimeInterval<Integer>>()
		{
			@Override
			public void onNext(rx.schedulers.TimeInterval<Integer> item)
			{
				System.out.println("Next: 與上一事件間隔時間相隔" + item.getIntervalInMilliseconds() + "豪秒");
			}

			@Override
			public void onError(Throwable error)
			{
				System.err.println("Error: " + error);
			}

			@Override
			public void onCompleted()
			{
				System.out.println("Sequence complete.");
			}
		});
	}
}
