package ch09.ex02;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Demo {
	public static void convertLinesToLowerCases(String sourcePath, String destinationPath) throws IOException {
		Objects.requireNonNull(sourcePath, "An argument is null.");
		Objects.requireNonNull(destinationPath, "An argument is null.");
		Scanner in = null;
		PrintWriter out = null;
		IOException exception = null;
		try {
			in = new Scanner(Paths.get(sourcePath));
			out = new PrintWriter(destinationPath);
			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
			throw new IOException("try節での例外");
		} catch (IOException e) {
			exception = e;
		} finally {
			addSuppressed(in, exception);
			addSuppressed(out, exception);
			if (exception != null) {
				throw exception;
			}
		}
	}

	private static void addSuppressed(Closeable resource, Throwable t) {
		if (resource != null) {
			try {
				resource.close();
				throw new IOException("finally節での例外");
			} catch (Exception e) {
				if (t != null) {
					t.addSuppressed(e);
				} else {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			convertLinesToLowerCases("./alice.txt", "./out.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
