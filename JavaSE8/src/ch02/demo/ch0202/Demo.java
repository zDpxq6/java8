package ch02.demo.ch0202;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

//2. ある文字数以上の長い単語のうち, 最初の5つだけ求める処理において,
// 5番目の長い単語がいったん見つかったらfilterメソッドが呼び出されないことを検証しなさい.
//単純に, 各メソッドの呼び出しでログを出せば良いです.
public class Demo {
	private static final int LENGTH_THRESHORLD = 5;
	private static final int LIMIT = 5;

	public static void main(String[] args) throws IOException {
		String contents = new String(
				Files.readAllBytes(Paths.get("alice.txt")),
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		Stream<String> stm = words.stream().filter(w -> {
			System.out.println("filtering...");
			return LENGTH_THRESHORLD < w.length();
		}).limit(LIMIT);//実行順序がわからない
		stm.forEach(System.out::println);
	}
}
