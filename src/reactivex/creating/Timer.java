package reactivex.creating;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class Timer
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// create an Observable that emits a particular item after a given delay
		Observable<Long> myObservable = Observable.timer(2, TimeUnit.SECONDS); // 兩秒後回傳一個 Long，從0開始！

		myObservable.subscribe(new Action1<Long>() // 執行在新的 Thread 上
				{
					@Override
					public void call(Long item)
					{
						System.out.println(item + " - " + Thread.currentThread().getName());
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

		}
	}
}
