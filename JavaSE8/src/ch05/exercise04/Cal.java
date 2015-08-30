package ch05.exercise04;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;
//4. ある月のカレンダーを表示するUnixのcalプログラムと同じプログラムを書きなさい.
//   たとえば, java Cal 3 2013を実行すると次のように表示します.(中略)
//   3月1日が金曜日であることを示しています(土曜日と日曜が右端に表示されるようにしなさい).
//

public class Cal {
	private static final int INITIAL_DAY = 1;
	private static final int PARAMETER_LENGTH = 2;
	private static final String BLANK = "   ";
	private static final String DAY_SEPARATOR = " ";
	private static final String PADDING = " ";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter
			.ofPattern("d");

	/**
	 *
	 * @param args
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) {
		Objects.requireNonNull(args, "An argument is null.");
		if (args.length != PARAMETER_LENGTH) {
			throw new IllegalArgumentException(
					"An argument has illegal length.");
		}
		int year = Integer.parseInt(args[1]);
		int month = Integer.parseInt(args[0]);

		LocalDate initialDay = LocalDate.of(year, month, INITIAL_DAY);
		System.out.println(generateCalender(initialDay));

	}

	private static String generateCalender(LocalDate someDay) {
		LocalDate day = someDay.with(TemporalAdjusters.firstDayOfMonth());
		StringBuilder result = new StringBuilder();
		IntStream.range(1, day.getDayOfWeek().getValue()).forEach(
				i -> result.append(BLANK));
		return Stream.iterate(day, (d) -> d.plusDays(1))
				.limit(day.until(day.plusMonths(1), ChronoUnit.DAYS))
				.collect(() -> result, Cal::format, StringBuilder::append)
				.toString();
	}

	private static void format(StringBuilder d, LocalDate t) {
		d.append(DAY_SEPARATOR);// 日付の区切り文字を追加する
		String formatted = FORMATTER.format(t);
		if (formatted.length() == 1) {
			d.append(PADDING);// 日付が一桁なら日付の前にスペースを追加する
		}
		d.append(formatted);// 日付を追加する
		if (t.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {// 日曜日なら, 改行を追加する
			d.append(System.getProperty("line.separator"));
		}
	}
}
