package reactivex.utility;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class SubscribeOn
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// SubscribeOn: 	specify the Scheduler on which an Observable will operate / 改变Observable应该在哪个调度器上执行任务
		// OberveOn:    	specify the Scheduler on which an observer will observe this Observable / 改变Observable将在哪个调度器上发送通知
		Observable.range(0, 5)
//		.subscribeOn(Schedulers.newThread()) // Thread1
		.map(item -> {
			System.out.println(item + " - " + Thread.currentThread().getName());
			return item;
		})
		.subscribeOn(Schedulers.newThread()) // Still Thread1 把生產加工過程丟到背景去做
		.map(item -> {
			System.out.println(item + " - " + Thread.currentThread().getName());
			return item;
		})
		.subscribe(new Subscriber<Integer>() // 0, 1, 2, 3, 4
		{
			@Override
			public void onNext(Integer item)
			{
				System.out.println("Next: " + item + " - " + Thread.currentThread().getName());
			}

			@Override
			public void onError(Throwable error)
			{
				System.err.println("Error: " + error.getMessage());
			}

			@Override
			public void onCompleted()
			{
				System.out.println("Sequence complete.");
			}
		});
		
		
		System.out.println("I am here! - " + Thread.currentThread().getName());
		while (true)
		{

		}
	}
}
