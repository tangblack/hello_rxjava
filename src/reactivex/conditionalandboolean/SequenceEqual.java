package reactivex.conditionalandboolean;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class SequenceEqual
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// determine whether two Observables emit the same sequence of items
        Observable<Integer> delay1 = Observable.just(1, 2, 3).delay(1000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay2 = Observable.just(1, 2, 3).delay(2000, TimeUnit.MILLISECONDS);
        Observable.sequenceEqual(delay1, delay2) // 判断两个Observable发射的数据序列是否相同（发射的数据相同，数据的序列相同，结束的状态相同），如果相同返回true，否则返回false
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
        
        while (true)
        {
        	try
			{
				Thread.sleep(15000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
        	
        	return;
        }
	}
}
