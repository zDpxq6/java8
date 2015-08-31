package ch05.exercise08;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.TimeZone;

/*
 * 8. 現在の時刻インスタントに対してサポートされる全てのタイムゾーンにおいて,
 * 今日の日付のオフセット(UTCとの差)を取得しなさい.
 * その際, ZoneId.getAvailableIdsをストリームに変換してから, ストリーム操作を使用することによって取得しなさい.
 */
public class Difference {
	public static void main(String[] args) {
		Instant now = Instant.now();
		OffsetDateTime od = Instant.now().atOffset(ZoneOffset.UTC);
		Arrays.stream(TimeZone.getAvailableIDs()).map(z -> od.until(now.atZone(ZoneId.of(z)), ChronoUnit.HOURS)).forEach(System.out::println);
	}
}
