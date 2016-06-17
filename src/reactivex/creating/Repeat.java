package reactivex.creating;

import rx.Observable;
import rx.Subscriber;

public class Repeat
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// create an Observable that emits a particular item multiple times
		Observable.just(1, 2, 3).repeat(10).subscribe(new Subscriber<Integer>() // 重複印10次1/2/3
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
		
		//TODO Study repeatWhen > http://www.jianshu.com/p/023a5f60e6d0
	}
}
