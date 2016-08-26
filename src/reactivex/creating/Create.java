package reactivex.creating;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class Create
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// create an Observable from scratch by calling observer methods programmatically
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
		.map(new Func1<Integer, Integer>()
				{
					@Override
					public Integer call(Integer i)
					{
						System.out.println("map: " + i);

						return i;
					}
				})
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
