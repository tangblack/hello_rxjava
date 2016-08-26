package reactivex.errorhandling;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class RetryWhen
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// if a source Observable sends an onError notification, resubscribe to it in the hopes that it will complete without error
		Observable.create((Subscriber<? super String> s) -> {
			System.out.println("subscribing");
			s.onError(new RuntimeException("always fails"));
		}).retryWhen(attempts -> {
			return attempts.zipWith(Observable.range(1, 3), (n, i) -> i).flatMap(i -> {
				System.out.println("delay retry by " + i + " second(s)");
				return Observable.timer(i, TimeUnit.SECONDS);
			});
		}).toBlocking().forEach(System.out::println);
	}
}
