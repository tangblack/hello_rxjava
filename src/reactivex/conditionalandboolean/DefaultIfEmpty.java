package reactivex.conditionalandboolean;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class DefaultIfEmpty
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// emit items from the source Observable, or a default item if the source Observable emits nothing
		Observable.from(new Integer[]{})
		.defaultIfEmpty(99) // 如果沒有數據就傳送99
		.subscribe(new Action1<Integer>()
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
