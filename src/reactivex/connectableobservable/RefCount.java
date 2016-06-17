package reactivex.connectableobservable;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

public class RefCount
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// make a Connectable Observable behave like an ordinary Observable
		ConnectableObservable<Long> connectObservable = 
				Observable.interval(1000, TimeUnit.MILLISECONDS).take(20) // 每1秒回傳一個 Long，從0開始！觸發20次！
				.publish();
		Action1<Long> observer2 = 
				new Action1<Long>()
				{
					@Override
					public void call(Long item)
					{
						System.out.println("第二個訂閱者, item=" + item + " - " + Thread.currentThread().getName());
					}
				};
		Action1<Long> observer1 = 
				new Action1<Long>()
				{
					@Override
					public void call(Long item)
					{
						System.out.println("第一個訂閱者, item=" + item + " - " + Thread.currentThread().getName());
						
						if (item == 10)
						{
							connectObservable.subscribe(observer2); // 之後的訂閱者只要一訂閱就會收到發射的數據，而不用再呼叫 Connect
						}
					}
				};
		
		connectObservable.refCount() // 將 ConnectableObservable 轉回 Observable
			.subscribe(observer1); // Observable 只要一被訂閱就開始發送數據
		

		while (true)
        {
        	try
			{
				Thread.sleep(30000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
        	
        	return;
        }
	}
}
