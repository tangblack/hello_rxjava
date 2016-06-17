package reactivex.conditionalandboolean;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class TakeUntil
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// discard any items emitted by an Observable after a second Observable emits an item or terminates
		// takeUntil 和 skipUntil 剛好相反
		Observable.interval(2, TimeUnit.SECONDS) // 每兩秒回傳一個 Long，從0開始！
		.take(5) // 觸發5次！
		.takeUntil(Observable.timer(5, TimeUnit.SECONDS)) // 第二個 Observable 在5秒後回傳一個 Long！第一個 Observable 就會被忽略。
		.subscribe(new Action1<Long>()
		{
			@Override
			public void call(Long item)
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
