package reactivex.mathematicalandaggregate;

import rx.Observable;
import rx.Subscriber;

public class Count
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// emits the item from the source Observable that had the maximum value
		Observable.just(1, 2, 3)
		.count() // 計算出共發射三次
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
