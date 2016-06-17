package reactivex.mathematicalandaggregate;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

public class Reduce
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// apply a function to each item emitted by an Observable, sequentially, and emit the final value
		Observable.just(2,2,2,2,2)
		.reduce(new Func2<Integer, Integer, Integer>() // 只會發射最後一次計算的結果
//		.scan(new Func2<Integer, Integer, Integer>() // scan会输出每次计算的结果
		{
			@Override
			public Integer call(Integer t1, Integer t2)
			{
				System.out.println("t1=" + t1 + ", t2=" + t2);
				return t1 + t2;
			}
		})
		.subscribe(new Subscriber<Integer>()
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
