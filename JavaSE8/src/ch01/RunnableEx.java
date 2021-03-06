package ch01;

//練習問題6
//Runnable内でチェックされる例外を処理しなければならないことが, いつも面倒だと思っていませんか．
//チェックされるすべての例外をキャッチし, それをチェックされない例外へ変えるuncheckメソッドを書きなさい．
//例えば, 次のように使用します.
//new Thread(uncheck(() ->
//	{System.out.println("Zzz"); Thread.sleep(1000); })).
//	start();
//		//catch (InterruptedException)が必要ありません!
//ヒント: どのような例外でもスローできるrunメソッドを持つRunnableExインターフェースを定義します．
//そして, public static Runnable uncheck(RunnableEx runner)を実装します．
//uncheckメソッド内でラムダ式を使用します.
//なぜ1，RunnableExの代わりにCallable<Void>を使用できないのでしょうか.
public interface RunnableEx {
	abstract void run() throws Exception;
	public static Runnable uncheck(RunnableEx runner) {
		if (runner == null) {
			runner = () -> {
			};
		}
		RunnableEx r = runner;
		return () -> {
			try {
				r.run();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}
}

interface Runnableex2 {// これでできないのはなぜ?
	public static Runnable uncheck(Runnable runner) {
		if (runner == null) {
			runner = () -> {
			};
		}
		Runnable r = runner;
		return () -> {
			try {
				r.run();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}
}
