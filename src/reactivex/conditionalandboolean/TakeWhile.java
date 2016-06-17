package reactivex.conditionalandboolean;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class TakeWhile
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// discard items emitted by an Observable until a specified condition becomes false
		// takeWhile 和 skipWhile 剛好相反
		Observable.range(0, 10)
		.takeWhile(new Func1<Integer, Boolean>() // TakeWhile则是根据一个函数来判断是否发射数据，当函数返回值为true的时候正常发射数据；当函数返回false的时候丢弃所有后面的数据。
		{
			@Override
			public Boolean call(Integer t)
			{
				return (t != 3); // 當值不等於3之前都會接收
			}
		})
		.subscribe(new Action1<Integer>()
		{
			@Override
			public void call(Integer item)
			{
				System.out.println(item);
			}
		}, new Action1<Throwable>()
		{
			@Override
			public void call(Throwable error)
			{
				System.out.println("Error encountered: " + error.getMessage());
			}
		}, new Action0()
		{
			@Override
			public void call()
			{
				System.out.println("Sequence complete");
			}
		});
		
		while (true)
        {
        	try
			{
				Thread.sleep(15000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
        	
        	return;
        }
	}
}
