package com.tangblack.hellorxjava.exception;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

public class Main
{
	/**
	 * Main.
	 *
	 * @param args N/A.
	 * @see <a href="http://blog.danlew.net/2015/12/08/error-handling-in-rxjava/">Error handling in RxJava</a>
	 */
	public static void main(String[] args)
	{
		Observable.just("far", "bar", "...")  
			.map(new Func1<String, String>()
				{
					@Override
			        public String call(String string) 
					{
						try 
					    {
					    	isBarThenThrowsException(string);
					    	return string;
					    } 
					    catch (Throwable t) 
					    {
					    	return null;
//					    	throw Exceptions.propagate(t);
					    }
			        }
				})
//			.onErrorReturn(error -> "Empty result") // 停在 bar 就結束執行。
		  	.subscribe(new Observer<String>() 
			{
			    @Override
			    public void onNext(String message) 
			    {
			    	if (message != null)
			    	{
			    		System.out.println("RxJava：" + message); // Print!!
			    	}
			    }
			
			    @Override
			    public void onCompleted()
			    {
			    	System.out.println("onCompleted");
			    }
			
			    @Override
			    public void onError(Throwable error)
			    {
			    	System.err.println("發生錯誤！" + error.getMessage());
			    }
			});
	}

	private static void isBarThenThrowsException(String input) throws Exception
	{
		if ("bar".equals(input))
		{
			throw new Exception("Find bar!!!");
		}
	}
}
