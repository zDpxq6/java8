package ch08.ex02;

//符号反転した結果, オーバーフローするとArithmeticExceptionが送出される.
//したがって, ArithmeticExceptionが送出されるのは引数がInteger.MIN_VALUE, Long.MIN_VALUEの場合
public class Demo {

	public static void main(String[] args) {
		try {
			System.out.println(Math.negateExact(Long.MIN_VALUE));
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
	}
}