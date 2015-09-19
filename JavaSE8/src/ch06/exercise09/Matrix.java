package ch06.exercise09;

import java.util.Objects;
import java.util.stream.Stream;

public final class Matrix {
	private final int[][] array;
	private final int row;
	private final int column;

	/**
	 * A private constructor.
	 *
	 * @param array
	 *            matrix
	 */
	private Matrix(int[][] array) {
		this.row = array.length;
		this.column = array[0].length;
		this.array = array;
	}

	/**
	 * コンストラクタ
	 *
	 * @param array
	 *            配列の要素を表す2次元配列
	 * @return 配列 @ throws NullPointerException 引数がnullの場合 @ throws
	 * IllegalArgumentException 配列が矩形でない場合
	 */
	public static Matrix of(int[][] array) {
		Objects.requireNonNull(array, "A Parameter is null");
		int[][] copiedArray = deepCopy(array);
		int result = Stream.of(copiedArray).mapToInt(e -> e.length).reduce((t, u) -> t == u ? u : -1).getAsInt();
		if (0 < result) {
			return new Matrix(copiedArray);
		} else {
			throw new IllegalArgumentException("Each row must have same column number.");
		}
	}

	/**
	 * 内積を計算する
	 *
	 * @param matrix
	 * @return 計算された内積
	 * @throws IllegalArgumentException
	 *             この行列の列数と引数の行列の行数が合わない場合
	 */
	public final Matrix calculateInnerProduct(Matrix matrix) {
		if (this.column != matrix.row) {
			throw new IllegalArgumentException("cannot define inner product");
		}
		int[][] result = new int[this.row][matrix.column];
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < matrix.column; j++) {
				for (int k = 0; k < this.column; k++) {
					result[i][j] += this.array[i][k] * matrix.array[k][j];
				}
			}
		}
		return of(result);
	}

	private static int[][] deepCopy(int[][] array) {
		int row = array.length;
		int column = array[0].length;
		int[][] result = new int[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				result[i][j] = array[i][j];
			}
		}
		return result;
	}

	/**
	 * コピーコンストラクタ. 行列をディープコピーして返す.
	 *
	 * @param matrix
	 *            コピーする行列
	 * @return コピーした行列.
	 */
	public static Matrix of(Matrix matrix) {
		Objects.requireNonNull(matrix, "A Parameter is null");
		int[][] copiedArray = deepCopy(matrix.array);
		return new Matrix(copiedArray);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.column; j++) {
				sb.append(this.array[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.substring(0, sb.length() - 1);
	}

	public int getElement(int row, int column) {
		return this.array[row][column];
	}

}
