package ch05.exercise03;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

/*
 * 3. Predicate<LocalDate>を受け取り, TemporalAdjusterを返すnextメソッドを実装しなさい.
 * 返されたTemporalAdjusterは, nextメソッドに渡された述語を満足する翌日の日付を生成します.
 * 例えば, 次のコードを見てください.
 * today.with(next(w -> w.getDayOfWeek().getValue() < 6))
 * このコードは今日より後にあって, 平日となる最初の日付を返します.
 */
public final class NextDemo {
	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		System.out.println(today);
		System.out.println(today.with(next(w -> w.getDayOfWeek().getValue() < 6)));
	}


	public static TemporalAdjuster next(Predicate<? super LocalDate> predicate) {
		return TemporalAdjusters.ofDateAdjuster(date -> {
			LocalDate result = date;
			do {
				result = result.plus(1, ChronoUnit.DAYS);
			} while (!predicate.test(result));

			return result;
		});
	}
}
