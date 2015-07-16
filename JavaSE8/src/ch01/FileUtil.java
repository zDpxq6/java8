package ch01;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

//(C) 2015 Tsuguka Hatanaka

public final class FileUtil {
	private FileUtil() {}

	//練習問題2
	//java.io.FileクラスのlistFiles(FileFilter)メソッドと
	//isDirectoryメソッドを使用して, 指定されたディレクトリの下の全てのサブディレクトリを返すメソッドを書きなさい.
	//FileFilterオブジェクトではなく, ラムダ式を使用しなさい.
	//同じことを, メソッド参照を用いて行いなさい.
	/**
	 * 指定されたディレクトリの下の全てのサブディレクトリを返す. 指定されたディレクトリがサブディレクトリを持たない場合や,
	 * 指定されたのがディレクトリでなくファイルだった場合には, 空のリストを返す
	 *
	 * @param parentDirectory
	 * @return
	 */
	public static final List<File> getSubDirectories(File parentDirectory) {
		if (parentDirectory == null) {
			return new ArrayList<>();
		} else if (parentDirectory.isFile()) {
			return new ArrayList<>();
		}

		File[] fileArray = parentDirectory.listFiles(x -> x.isDirectory());
		// File[] fileArray =
		// parentDirectory.listFiles((FileFilter)File::isDirectory);
		List<File> subResult = null;
		if (fileArray == null) {
			subResult = new ArrayList<>();
		} else if (fileArray.length <= 0) {
			subResult = new ArrayList<>();
		} else {
			subResult = Arrays.asList(fileArray);
		}
		List<File> result = new ArrayList<File>();
		result.addAll(subResult);
		for (File e : fileArray) {
			result.addAll(getSubDirectories(e));
		}
		return result;
	}

	//練習問題3
	//java.io.Fileクラスのlist(FilenameFilter)を使用して,
	//指定されたディレクトリの下にあって, 指定された拡張子を持つ, すべてのファイルを返すメソッドを書きなさい.
	//FilenameFilterではなく, ラムダ式を使用しなさい.エンクロージングスコープからキャプチャされる変数はどれですか.
	//同じことを, メソッド参照を用いて行いなさい.
	/**
	 * 指定されたディレクトリの下にあって, 指定された拡張子を持つ, 全てのファイルを返す. 指定されたディレクトリがサブディレクトリを持たない場合や,
	 * 指定されたのがディレクトリでなくファイルだった場合には, 空のリストを返す.
	 *
	 * @param parentDirectory
	 * @param expression
	 * @return
	 */
	public static final List<File> getSpecificFiles(File parentDirectory, final String expression) {

		if (parentDirectory == null || parentDirectory.isFile()) {
			return new ArrayList<>();
		}
		if (expression == null) {
			return new ArrayList<>();
		}

		Pattern p = Pattern.compile("^*." + expression + "$");

		String[] fileNameArray = parentDirectory.list((dir, name) -> {
			Matcher m = p.matcher(name);
			return m.find();
		});

		Stream<File> str = Stream.of(fileNameArray).map(File::new);
		File[] fileArray = str.toArray(File[]::new);
		List<File> result = new ArrayList<File>(Arrays.asList(fileArray));
		File[] directriesArray = parentDirectory.listFiles(File::isDirectory);
		List<File> subResult = null;
		if (directriesArray == null || directriesArray.length <= 0) {
			subResult = new ArrayList<>();
		} else {
			subResult = Arrays.asList(directriesArray);
		}
		for (File e : subResult) {
			result.addAll(getSpecificFiles(e, expression));
		}
		return result;
	}

	//練習問題4
	//Fileオブジェクトの配列が渡されたとします．
	//その配列をソートして, ファイルの前にディレクトリが来るようにし,
	//ファイルとディレクトリのそれぞれのグループではパス名で
	//ソートされるようにしなさい．
	//Comparatorでなく, ラムダ式を使用しなさい.
	/**
	 * 指定された配列を, ディレクトリ, ファイルの順でソートする. ディレクトリとファイルのそれぞれのグループでは, パス名でソートされる.
	 * 指定された配列がnullの場合, 空の配列が返る.
	 *
	 * @param target
	 * @return
	 */
	public static final File[] sort(File[] target) {
		if (target == null) {
			return new File[0];
		}
		List<File> fl = Arrays.asList(target);
		Collections.sort(fl, (t1, t2) -> {
			if (t1.isDirectory() && t2.isFile()) {
				return -1;
			} else if (t1.isFile() && t2.isDirectory()) {
				return 1;
			} else {
				return t1.toPath().compareTo(t2.toPath());
			}
		});
		return fl.toArray(new File[0]);
	}
}
