package ch02.demo.ch0205;

//5. Stream.iterateを使用して乱数の無限ストリームを生成しなさい．
//このとき, Math.randomを呼び出すのではなく, 線形合同生成機を直接実装すること.
//このような生成器では, x0 = seed で始めて, a, c, m, seedをの適切な値に対して
//x_n+1 = (ax_n+c)%mを生成します.
//パラメータa, c, m, seed受け取り, Stream<Long>を生成するメソッドを実装しなさい.
//a = 25214903917, c=11, m = 2^48を試してみなさい.
import java.io.IOException;
import java.util.stream.Stream;

public class Demo {
	private static final long SEED = 9l;
	private static final long A = 25214903917l;
	private static final long C = 11l;
	private static final long M = (long) Math.pow(2, 48);

	public static void main(String[] args) throws IOException {
		Stream<Long> stm = Stream.iterate(SEED, (v) -> ((A * v + C) % M));
		stm.forEach(System.out::println);
	}
}
