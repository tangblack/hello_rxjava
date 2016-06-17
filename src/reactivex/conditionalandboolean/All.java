package reactivex.conditionalandboolean;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class All
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// determine whether all items emitted by an Observable meet some criteria
		Integer[] items = { 0, 1, 2, 3, 4, 5 };
		Observable<Integer> myObservable = Observable.from(items); // 把一個 Integer 陣列轉成一個 Observable

		myObservable.all(new Func1<Integer, Boolean>() // 判斷所有事件都符合條件
		{
			@Override
			public Boolean call(Integer t)
			{
				if (t.intValue() < 3)
				{
					return true;
				}
				return false;
			}
		})
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
