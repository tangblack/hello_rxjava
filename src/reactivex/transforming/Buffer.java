package reactivex.transforming;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class Buffer
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// periodically gather items emitted by an Observable into bundles and
		// emit these bundles rather than emitting the items one at a time
		Observable.range(0, 20) // 0, 1, 2, 3, 4 ... 19
				.buffer(7) // 每收集七個數字才發射一次。
				.subscribe(new Subscriber<List<Integer>>()
				{
					@Override
					public void onNext(List<Integer> item)
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
