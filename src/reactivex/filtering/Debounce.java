package reactivex.filtering;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class Debounce
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// only emit an item from an Observable if a particular timespan has passed without it emitting another item
		Observable.interval(100, TimeUnit.MILLISECONDS).take(20) // 每0.1秒回傳一個 Long，從0開始！觸發20次！
		.debounce(200, TimeUnit.MILLISECONDS) // 收到事件甲後0.2秒內沒有下一個事件，事件甲才會被發射！常用來防抖動。
		.subscribe(new Action1<Long>() // 執行在新的 Thread 上
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
