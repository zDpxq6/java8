

//練習問題6
//Runnable内でチェックされる例外を処理しなければならないことが, いつも面倒だと思っていませんか．
//チェックされるすべての例外をキャッチし, それをチェックされない例外へ変えるuncheckメソッドを書きなさい．
//例えば, 次のように使用します.
//new Thread(uncheck(() ->
//	{System.out.println("Zzz"); Thread.sleep(1000); })).
//	start();
//		//catch (InterruptedException)が必要ありません!
public interface UncheckedRunnable {
	void run() throws Exception;

	static Runnable uncheck(UncheckedRunnable runner) {
		Runnable result = () -> {
			try {
				runner.run();
			} catch (RuntimeException re) {
				throw re;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
		return result;
	}
}
