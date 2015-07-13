//(C) 2015 Tsuguka Hatanaka
import java.util.Arrays;
import java.util.Comparator;

//練習問題1
//Arrays.sortメソッド内で呼び出されているコンパレータのコードは，sortメソッドを
//呼び出したスレッドで実行されるでしょうか.
//それとも，別のスレッドで実行されるでしょうか．
//答案
//同じスレッドで実行される.
//以下のコードで確認した.
public class ArraysSort {
	public static void main(String[] args) {
		Arrays.sort(new Object[10], new Comparator<Object>() {
			@Override
			public int compare(Object arg0, Object arg1) {
				System.out.println(Thread.currentThread().getName());
				return 0;
			}
		});
	}
}
