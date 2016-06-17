package reactivex.filtering;

import rx.Observable;
import rx.Subscriber;

public class IgnoreElements
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// do not emit any items from an Observable but mirror its termination notification
		Observable.just(1, 2, 3)
		.ignoreElements() // 不會發射任何事件，但會呼叫 onError 或 onCompleted。
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
