package reactivex.filtering;

import rx.Observable;
import rx.Subscriber;

public class Distinct
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// suppress duplicate items emitted by an Observable
		Observable.just(1, 2, 1, 1, 2, 3)
		.distinct() // 排除重複的值
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
