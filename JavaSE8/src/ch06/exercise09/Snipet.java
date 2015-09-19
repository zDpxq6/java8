package ch06.exercise09;

import java.util.Arrays;
import java.util.stream.Stream;

/*9. フィボナッチ(Fibonacci)数の計算を並列化する前にparallelPrefixメソッドを使うことができます.
 * (中略)
 * 2 x 2 の行列で配列を埋めなさい. 乗算メソッドをもつMatrixクラスを定義し, 行列の配列を作成するためにparallelPrefixを使用しなさい.
 *
*/
public class Snipet {
	public static void main(String[] args) {
		int[][] array = { { 1, 1 }, { 1, 0 } };
		Matrix[] matrixes = new Matrix[5];
		Arrays.parallelSetAll(matrixes, (i) -> Matrix.of(array));
		Arrays.parallelPrefix(matrixes, (t, u) -> t.calculateInnerProduct(u));
		Stream.of(matrixes).map(t -> t.getElement(0, 0)).forEach(s -> System.out.print(s + ", "));
	}
}
