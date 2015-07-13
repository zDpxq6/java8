import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void getSubDirectories_引数がnullのとき_空の配列が返る() {
		List<File> actual = FileUtil.getSubDirectories(null);
		List<File> expected = new ArrayList<>();
		assertThat(actual, is(expected));
	}

	@Test
	public void getSubDirectories_引数がファイルのとき_空の配列が返る() {
		List<File> actual = FileUtil.getSubDirectories(new File("root.txt"));
		List<File> expected = new ArrayList<>();
		assertThat(actual, is(expected));
	}

	@Test
	public void getSubDirectories_引数がディレクトリのとき_サブディレクトリのリストが返る() {
		List<File> actual = FileUtil.getSubDirectories(new File("root"));
		actual.forEach(System.out::println);
		List<File> expected = new ArrayList<>();
		expected.add(new File("root/node11/"));
		expected.add(new File("root/node12/"));
		expected.add(new File("root/node12/node21/"));
		expected.add(new File("root/node12/node21/node31/"));
		assertThat(actual, is(expected));
	}

	@Test
	public void getSpecificFiles_引数がnullのとき_空のリストが返る() {
		List<File> actual = FileUtil.getSpecificFiles(null, null);
		actual.forEach(System.out::println);
		List<File> expected = new ArrayList<>();
		assertThat(actual, is(expected));
	}

	@Test
	public void getSpecificFiles_引数がファイルのとき_空のリストが返る() {
		List<File> actual = FileUtil.getSpecificFiles(new File("toor.txt"), null);
		actual.forEach(System.out::println);
		List<File> expected = new ArrayList<>();
		assertThat(actual, is(expected));
	}

	@Test
	public void getSpecificFiles_引数がファイルのとき_空のリストが返る2() {
		List<File> actual = FileUtil.getSpecificFiles(new File("root.txt"), "txt");
		actual.forEach(System.out::println);
		List<File> expected = new ArrayList<>();
		assertThat(actual, is(expected));
	}

	@Test
	public void getSpecificFiles_引数がファイルのとき_空のリストが返る3() {
		List<File> actual = FileUtil.getSpecificFiles(null, "txt");
		actual.forEach(System.out::println);
		List<File> expected = new ArrayList<>();
		assertThat(actual, is(expected));
	}

	@Test
	public void getSpecificFiles_() {
		List<File> actual = FileUtil.getSpecificFiles(new File("root"), "txt");
		actual.forEach(System.out::println);
		List<File> expected = new ArrayList<>();
		expected.add(new File("leaf10.txt"));
		expected.add(new File("leaf21.txt"));
		expected.add(new File("leaf22.txt"));
		expected.add(new File("leaf31.txt"));
		assertThat(actual, is(expected));
	}
}
