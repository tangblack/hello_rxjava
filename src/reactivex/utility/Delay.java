package reactivex.utility;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class Delay
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// shift the emissions from an Observable forward in time by a particular amount
		Observable.range(0, 5)
		.delay(3, TimeUnit.SECONDS) // 延遲三秒後才開始發送
		.subscribe(new Subscriber<Integer>() // 0, 1, 2, 3, 4
		{
			@Override
			public void onNext(Integer item)
			{
				System.out.println("Next: " + item + " - " + Thread.currentThread().getName());
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
		
		
		System.out.println("I am here! - " + Thread.currentThread().getName());
		while (true)
		{

		}
	}
}
