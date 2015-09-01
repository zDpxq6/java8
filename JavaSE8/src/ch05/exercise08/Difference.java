package ch05.exercise08;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/*
 * 8. 現在の時刻インスタントに対してサポートされる全てのタイムゾーンにおいて,
 * 今日の日付のオフセット(UTCとの差)を取得しなさい.
 * その際, ZoneId.getAvailableIdsをストリームに変換してから, ストリーム操作を使用することによって取得しなさい.
 */
public class Difference {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.now(), Clock.systemUTC().getZone());
		ZoneId.getAvailableZoneIds().stream().map(zoneId -> Duration.between(now.atZone(ZoneId.of(zoneId)), utc)).forEach(System.out::println);
	}
}
