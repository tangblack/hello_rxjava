package reactivex.filtering;

import rx.Observable;
import rx.Subscriber;

public class ElementAt
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// emit only item n emitted by an Observable
		Observable.just(1, 2, 1, 1, 2, 3)
		.elementAt(0) // 只發射 index 為0的那一個事件
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
