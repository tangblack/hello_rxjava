package reactivex.transforming;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

public class Scan
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// apply a function to each item emitted by an Observable, sequentially, and emit each successive value
		Observable.just(1, 2, 3, 4, 5).scan(new Func2<Integer, Integer, Integer>()
		{
			@Override
			public Integer call(Integer sum, Integer item) // 第一個參數是上次結果， 第二個參數是接收到的輸入值
			{
				return sum + item; // 回傳這次結果，下一個 call 會取得此結果。
			}
		}).subscribe(new Subscriber<Integer>()
		{
			@Override
			public void onNext(Integer item)
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
	}
}
