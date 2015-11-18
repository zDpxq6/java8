package ch09.ex02;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public class TryWithoutResource {
	public static void convertLinesToLowerCases(String sourcePath, String destinationPath) throws IOException {
		Objects.requireNonNull(sourcePath, "An argument is null.");
		Objects.requireNonNull(destinationPath, "An argument is null.");
		Scanner scanner = null;
		final PrintWriter out = new PrintWriter(destinationPath);
		IOException exception = null;
		try {
			scanner = new Scanner(Paths.get(sourcePath));
			StreamSupport.stream(Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED), false).forEach(e -> out.println(e.toLowerCase()));
			out.close();
		} catch (IOException e) {
			exception = e;
		} finally {
			if(out != null){
				out.close();
			}
			if (exception != null) {
				addSuppressed(scanner, exception);
				addSuppressed(out, exception);
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
