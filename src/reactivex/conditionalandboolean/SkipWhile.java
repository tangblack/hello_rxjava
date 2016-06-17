package reactivex.conditionalandboolean;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class SkipWhile
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 */
	public static void main(String[] args)
	{
		// discard items emitted by an Observable until a specified condition becomes false
		Observable.range(0, 10)
		.skipWhile(new Func1<Integer, Boolean>() // SkipWhile则是根据一个函数来判断是否跳过数据，当函数返回值为true的时候则一直跳过源Observable发射的数据；当函数返回false的时候则开始正常发射数据。
		{
			@Override
			public Boolean call(Integer t)
			{
				return (t != 3); // 當值不等於3之前都會忽略
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
