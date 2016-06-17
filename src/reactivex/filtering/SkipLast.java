package reactivex.filtering;

import rx.Observable;
import rx.Subscriber;

public class SkipLast
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// suppress the first n items emitted by an Observable
		Observable.range(0, 5)
		.skip(3) // 跳過前面三個
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
	}
}
