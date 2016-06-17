package reactivex.mathematicalandaggregate;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class Concat
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// emit the emissions from two or more Observables without interleaving them
		Observable<Long> obser1 = // 每一秒發射一次，共發射五次。
				Observable.interval(1, TimeUnit.SECONDS).take(5).map(i -> Long.valueOf(i + 11));
		Observable<Long> obser2 = 
				Observable.interval(1, TimeUnit.SECONDS).take(5).map(i -> Long.valueOf(i + 21));
		Observable<Long> obser3 = 
				Observable.interval(1, TimeUnit.SECONDS).take(5).map(i -> Long.valueOf(i + 31));
        
//        有两个操作符跟它类似，但是有区别，分别是
//        1.startWith：仅仅是在前面插上一个数据。
//        2.merge:其发射的数据是无序的。
		Observable
		.concat(obser1, obser2, obser3) // 将多个Observable结合成一个Observable并发射数据，并且严格按照先后顺序发射数据，前一个Observable的数据没有发射完，是不能发射后面Observable的数据的。
//		.merge(obser1, obser2, obser3) // merge:其发射的数据是无序的。
		.subscribe(new Subscriber<Long>()
		{
			@Override
			public void onNext(Long item)
			{
				System.out.println("Next: " + item);
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
