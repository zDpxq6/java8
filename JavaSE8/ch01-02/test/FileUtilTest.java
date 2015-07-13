import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void 引数がnullのとき_空の配列が返る() {
		List<File> actual = FileUtil.getSubDirectories(null);
		List<File> expected = new ArrayList<>();
		assertThat(actual, is(expected));
	}

	@Test
	public void 引数がファイルのとき_空の配列が返る() {
		List<File> actual = FileUtil.getSubDirectories(new File("root.txt"));
		List<File> expected = new ArrayList<>();
		assertThat(actual, is(expected));
	}

	@Test
	public void 引数がファイルのとき_空の配列が返る2() {
		List<File> actual = FileUtil.getSubDirectories(new File("root"));
		actual.forEach(System.out::println);
		List<File> expected = new ArrayList<>();
		assertThat(actual, is(expected));
	}

}
