package reactivex.mathematicalandaggregate;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class ToList
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// convert an Observable into another object or data structure
		Observable.just(1, 2, 3) // 原本會發射三次
		.toList() // 轉成一個串列，只發射一次。
		.subscribe(new Subscriber<List<Integer>>()
		{
			@Override
			public void onNext(List<Integer> item)
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
