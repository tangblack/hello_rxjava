package com.tangblack.hellorxjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
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
 * @see <a href="http://www.cnblogs.com/dongweiq/p/5092165.html">RxJava使用场景小结</a>
 */
public class HelloRxJava
{
	
	

	public static void main(String[] args)
	{
		List<String> list = new ArrayList<String>(Arrays.asList("boy_4", "boy_1", "girl_1", "boy_2", "boy_3", "girl_2"));
		
		
		// RxJava 有 lamda 的寫法：
		Observable.from(list)
			.filter(string -> isBoy(string) == true)
			.map(string -> getNumber(string))
			.map(string -> numberToEnglishNumber(string))
			.take(2)
			.subscribe(string -> System.out.println(string));
		
		
		// RxJava 沒有 lamda 的寫法：
		Observable.from(list)
			.filter(new Func1<String, Boolean>()
			{
				@Override
		        public Boolean call(String string) 
				{
		            return isBoy(string);
		        }
			})
			.map(new Func1<String, String>()
			{
				@Override
		        public String call(String string) 
				{
		            return getNumber(string);
		        }
			})
			.map(new Func1<String, String>()
			{
				@Override
		        public String call(String string) 
				{
		            return numberToEnglishNumber(string);
		        }
			})
			.take(2)
			.subscribe(new Action1<String>()
			{
				@Override
			    public void call(String string) 
				{
					System.out.println(string);
			    }
			});
		
		
		// 傳統寫法：
		int take = 2;
		int i = 1;
		for (String string : list)
		{
			if (isBoy(string))
			{
				if (take < i)
				{
					return; // Jump!
				}
				
				System.out.println(numberToEnglishNumber(getNumber(string)));
				i++;
			}
		}
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

	/**
	 * 取得字串裡的數字
	 */
	private static String getNumber(String string)
	{
		return string.substring(string.indexOf("_") + 1);
	}

	/**
	 * 字串是否包含 boy
	 */
	private static boolean isBoy(String string)
	{
		return string.startsWith("boy");
	}

	
}
