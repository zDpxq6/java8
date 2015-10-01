package ch07.ex02;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class FindingLongWords {

	/**
	 * 指定された長さ以上の長さの単語をソートして取得する
	 * @param file 単語が入ってるファイル nullは許されない
	 * @param thresholdInclude 閾値 この長さ以上の単語が選ばれる 0以下は許されない
	 * @return 指定した閾値以上の文字のセット
	 * @throws IOException ファイルが存在しない場合
	 * @throws NullPointerException パラメータがnullの場合
	 * @throws IllegalArgumentException fileがファイルでない場合 閾値が0以下の場合
	 */
	public static Set<String> findLondWords(File file, int thresholdInclude) throws IOException {
		Objects.requireNonNull(file, "A parameter \"file\" is null");
		if (!file.isFile()) {
			throw new IllegalArgumentException("A parameter \"file\" is not file");
		}
		if (thresholdInclude < 1) {
			throw new IllegalArgumentException("A parameter \"threshold\" is 0 or negative");
		}
		String contents = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		Set<String> result = words.stream().filter(w -> thresholdInclude <= w.length()).collect(TreeSet::new, Set::add, Set::addAll);
		return result;
	}

	public static void main(String[] args) throws IOException{
		Set<String> set = findLondWords(new File("alice.txt"), 12);
		Stream.of(set).forEach(System.out::println);
	}
}
