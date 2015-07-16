package ch02.demo.ch0210;

import java.util.stream.Stream;

//Stream<Double>の平均値を計算するために使用できるreduceの呼び出しを書きなさい.
//単純に合計を計算して, count()で割ることができないのはなぜですか.

public class Demo {

	public static void main(String[] args) {
		Stream<Data> dStream = Stream.of(new Data(1.0), new Data(2.0),new Data(3.0),new Data(4.0),new Data(5.0) );
		Data result = dStream.reduce(new Data(0,0), (t, u) -> {
			return t.add(u);
		});
		System.out.println(result.calcurateAverage());

	}

	private static class Data {
		private final double value;
		private int counter = 1;

		public Data(double value){
			this.value = value;
		}

		public Data(double value, int counter){
			this.value = value;
			this.counter = counter;
		}

		public double calcurateAverage() {
			return this.value / this.counter;
		}

		public Data add(Data data) {
			return new Data(this.value + data.value, this.counter + data.counter);
		}
	}
}
