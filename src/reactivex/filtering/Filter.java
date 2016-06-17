package reactivex.filtering;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class Filter
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// emit only those items from an Observable that pass a predicate test
		Observable.just(1, 2, 3, 4, 5)
			.filter(new Func1<Integer, Boolean>() // 小於4的事件才會通過
			{
				@Override
				public Boolean call(Integer item)
				{
					return (item < 4);
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
