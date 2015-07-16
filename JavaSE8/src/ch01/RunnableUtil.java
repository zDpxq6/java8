package ch01;


//(C) 2015 Tsuguka Hatanaka

//練習問題7
// staticメソッドandThenを書きなさい.
// andThenメソッドは, 2つのRunnableインスタンスをパラメータとして受け取るようにし,
// 最初のRunnableを実行したあと2つ目のRunnableを実行するRunnableを返すようにします.
// mainメソッドではandThenへの呼び出しについて2つのラムダ式を渡して, 返されたインスタンスを実行するようにしなさい．
public final class RunnableUtil {
	private RunnableUtil(){}
	/**
	 * 2つのRunnableインスタンスをパラメータとして受け取るようにし, 最初のRunnableを実行したあと2つ目のRunnableを返す.
	 * 引数にnullが渡ってきた場合, IllegalArgumentExceptionを投げる.
	 * @param r1
	 * @param r2
	 * @throws IllegalArgumentException 引数のいずれかがnullのとき
	 * @return r2を実行するr2
	 */
	static Runnable andThen(Runnable r1, Runnable r2) {
		if(r1 == null || r2 == null){
			throw new IllegalArgumentException("無効な引数");
		}
		new Thread(r1).start();
		return r2;
	}

}
