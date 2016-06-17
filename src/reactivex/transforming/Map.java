package reactivex.transforming;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class Map
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// transform the items emitted by an Observable by applying a function to each item
		Observable.range(0, 20) // 0, 1, 2, 3, 4 ... 19
				.map(new Func1<Integer, String>() // 一個一個元素做處理
				{
					@Override
					public String call(Integer t)
					{
						return "*" + t + "*";
					}
				})
				.subscribe(new Subscriber<String>()
				{
					@Override
					public void onNext(String item)
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
