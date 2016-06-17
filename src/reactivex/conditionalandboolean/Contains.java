package reactivex.conditionalandboolean;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class Contains
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// determine whether an Observable emits a particular item or not
		Integer[] items = { 0, 1, 2, 3, 4, 5 };
		Observable<Integer> myObservable = Observable.from(items); // 把一個 Integer 陣列轉成一個 Observable

		myObservable.contains(4) // 有沒有收到過4 
		.subscribe(new Action1<Boolean>()
		{
			@Override
			public void call(Boolean item)
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
