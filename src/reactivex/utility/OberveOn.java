package reactivex.utility;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class OberveOn
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// specify the Scheduler on which an observer will observe this Observable
		Observable.range(0, 5)
		.map(item -> {
			System.out.println(item + " - " + Thread.currentThread().getName());
			return item;
		})
		.map(item -> {
			System.out.println(item + " - " + Thread.currentThread().getName());
			return item;
		})
		.observeOn(Schedulers.newThread()) 
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
