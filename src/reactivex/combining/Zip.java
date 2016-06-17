package reactivex.combining;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

public class Zip
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// combine the emissions of multiple Observables together via a specified function and 
		// emit single items for each combination based on the results of this function
		Observable<Integer> odds = Observable.just(1, 3, 5);
		Observable<Integer> evens = Observable.just(2, 4, 6, 8, 10);

		Observable.zip(odds, evens, new Func2<Integer, Integer, String>() // 一對一配對，8和10沒人配，就直接結束了！
				{
					@Override
					public String call(Integer t1, Integer t2)
					{
						return t1 + " + " + t2;
					}
		
				}) 
		          .subscribe(new Subscriber<String>() {
		        @Override
		        public void onNext(String item) {
		            System.out.println("Next: " + item);
		        }

		        @Override
		        public void onError(Throwable error) {
		            System.err.println("Error: " + error.getMessage());
		        }

		        @Override
		        public void onCompleted() {
		            System.out.println("Sequence complete.");
		        }
		    });
		
//		output:
//			Next: 1 + 2
//			Next: 3 + 4
//			Next: 5 + 6
//			Sequence complete.
	}
}
