package reactivex.creating;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class From
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// convert various other objects and data types into Observables
		Integer[] items = { 0, 1, 2, 3, 4, 5 };
		Observable<Integer> myObservable = Observable.from(items); // 把一個 Integer 陣列轉成一個 Observable

		myObservable.subscribe(new Action1<Integer>()
		{
			@Override
			public void call(Integer item)
			{
				System.out.println(item);
			}
		}, new Action1<Throwable>()
		{
			@Override
			public void call(Throwable error)
			{
				System.out.println("Error encountered: " + error.getMessage());
			}
		}, new Action0()
		{
			@Override
			public void call()
			{
				System.out.println("Sequence complete");
			}
		});
	}
}
