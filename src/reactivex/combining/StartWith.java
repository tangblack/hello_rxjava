package reactivex.combining;

import rx.Observable;
import rx.Subscriber;

public class StartWith
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// combine multiple Observables into one by merging their emissions
		Observable<String> odds = Observable.just("1", "3", "5");

		odds.startWith("3", "2", "1", "GO") // 最前面多添加一個 "3", "2", "1", "GO"
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
	}
}
