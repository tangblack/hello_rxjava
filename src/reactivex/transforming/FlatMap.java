package reactivex.transforming;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class FlatMap
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		List<Integer> list = Arrays.asList(0, 1, 2, 3, 4);
		
		// transform the items emitted by an Observable into Observables, 
		// then flatten the emissions from those into a single Observable
		Observable.just(list) // 傳入是一個 Integer 串列
			.flatMap(new Func1<List<Integer>, Observable<Integer>>() // flatMap 要把串列打散成一個一個 Observable<Integer>
				{
					@Override
					public Observable<Integer> call(List<Integer> list)
					{
						return Observable.from(list);
					}
				}).subscribe(new Subscriber<Integer>()
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
	}
}
