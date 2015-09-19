package ch06.exercise08;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * 8. みなさんのコンピュータでは, Arrays.parallelSortは,
 * 配列がどのくらいの大きさであればArrays.sortより速くなりますか.F
 */
public class SortDemo {
	/**
	 * 指定したファイルパスが示すファイルに含まれる文字を返す.
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static final List<String> fileToStrings(String filePath) throws IOException {
		Objects.requireNonNull(filePath, "A parameter filePaht is null.");
		byte[] bytes = Files.readAllBytes(new File(filePath).toPath());
		String content = new String(bytes, StandardCharsets.UTF_8);

		return Arrays.asList(content.split("[\\P{L}]+"));
	}

	public static void main(String[] args) {
		try {
			List<String> ls = fileToStrings("alice.txt");
			List<String[]> arrayList = new ArrayList<>();
			IntStream.range(1, 30).forEachOrdered(i -> {
				arrayList.add( ls.stream().limit(i * 1000).toArray(String[]::new));
			});
			System.out.println(arrayList.size());
			arrayList.stream().forEachOrdered(array -> {
				String[] array1 = array.clone();
				String[] array2 = array.clone();
				Instant start = Instant.now();
				Arrays.sort(array1);
				Duration normal = Duration.between(start, Instant.now());
				start = Instant.now();
				Arrays.parallelSort(array2);
				Duration parallel= Duration.between(start, Instant.now());
				if(parallel.minus(normal).isNegative()){
					System.out.println(array.length);
					System.out.println("para: " + parallel.getNano());
					System.out.println("norm: " + normal.getNano());
					System.out.println();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("?");

	}

}
