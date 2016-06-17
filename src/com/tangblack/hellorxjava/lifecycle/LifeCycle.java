package com.tangblack.hellorxjava.lifecycle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 
 * The class <code>HelloRxJava</code> 
 *
 * @author <a href="mailto:tangblack@gmail.com">tangblack</a>
 * @version 2016年5月26日
 * @since 2016年5月26日
 * 
 * @see <a href="http://www.jianshu.com/p/6a6f7a4be38d">RxJava快速入门</a>
 */
public class LifeCycle
{
	
	

	public static void main(String[] args)
	{
		List<String> list = new ArrayList<String>(Arrays.asList("boy_4", "boy_1", "girl_1", "boy_2", "boy_3", "girl_2"));
		
		
		Observable.from(list)
			.doOnSubscribe(new Action0() // doOnSubscribe在订阅时（最开始）回调，與 subscribe 同一個線程
			{
				@Override
				public void call()
				{
					System.out.println("doOnSubscribe");
				}
			})
			.doOnCompleted(new Action0() // doOnCompleted在onComplete前回调，與 subscribe 同一個線程
			{
				@Override
				public void call()
				{
					System.out.println("doOnCompleted");
					
				}
			})
			.map(new Func1<String, String>()
			{
				@Override
		        public String call(String string) 
				{
					System.out.println("map");
		            return numberToEnglishNumber(string);
		        }
			})
			.subscribe(new Subscriber<String>()
			{
				@Override
				public void onCompleted()
				{
					System.out.println("onCompleted");
				}

				@Override
				public void onError(Throwable e)
				{
					System.out.println("onError");
				}

				@Override
				public void onNext(String t)
				{
					System.out.println("onNext");
				}
			});
		
//		doOnSubscribe
//		map
//		onNext
//		map
//		onNext
//		map
//		onNext
//		map
//		onNext
//		map
//		onNext
//		map
//		onNext
//		doOnCompleted
//		onCompleted
	}

	
	
	
	/**
	 * 將阿拉伯數字轉為英文字
	 */
	private static String numberToEnglishNumber(String string)
	{
		if (string.equals("1")) return "one";
		
		if (string.equals("2")) return "two";
		
		if (string.equals("3")) return "three";
		
		return "oops!";
	}
	
}
