package ch09.ex11;

import java.io.IOException;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public final class CreditNumberCollector {

	private static final String CREDIT = "\\d\\d\\d\\d[\\s|-]\\d\\d\\d\\d[\\s|-]\\d\\d\\d\\d[\\s|-]\\d\\d\\d\\d";

	public static void main(String[] args) throws IOException {
		System.out.println(test());
	}

	private static final String test() throws IOException {
		ProcessBuilder builder = new ProcessBuilder("grep", "-r", CREDIT, ".");
		Process process = builder.start();
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try (Scanner scanner = new Scanner(process.getInputStream())) {
			return StreamSupport.stream(Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED), false).reduce("", (t, u) -> t + u + System.getProperty("line.separator"));
		}
	}

}
