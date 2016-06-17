package reactivex.conditionalandboolean;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class Amb
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// given two or more source Observables, 
		// emit all of the items from only the first of these Observables to emit an item or notification
        Observable<Integer> delay1 = Observable.just(1, 2, 3).delay(1000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay2 = Observable.just(4, 5, 6).delay(2000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay3 = Observable.just(7, 8, 9).delay(3000, TimeUnit.MILLISECONDS);
        Observable.amb(delay1, delay2, delay3) // 只會接收第一個到達的 Observable
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
