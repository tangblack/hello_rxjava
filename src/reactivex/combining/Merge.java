package reactivex.combining;

import rx.Observable;
import rx.Subscriber;

public class Merge
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// combine multiple Observables into one by merging their emissions
//		Observable<Integer> odds = Observable.just(1, 3, 5).subscribeOn(someScheduler);
		Observable<Integer> odds = Observable.just(1, 3, 5);
		Observable<Integer> evens = Observable.just(2, 4, 6);

		Observable.merge(odds, evens) // merge() 與 concatWith() 最大的差異是， merge() 是併發同時進貨 ，所以會交錯，而 concatWith 則是排隊等到前面進貨完才換下一位
		          .subscribe(new Subscriber<Integer>() {
		        @Override
		        public void onNext(Integer item) {
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
		
		
		// 實際測試兩者效果一樣
		odds.concatWith(evens) 
		          .subscribe(new Subscriber<Integer>() {
		        @Override
		        public void onNext(Integer item) {
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
	}
}
