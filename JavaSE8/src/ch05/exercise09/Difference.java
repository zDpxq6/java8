package ch05.exercise09;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * 9. 再度, ストリーム操作を使用して, オフセットに1時間未満の情報が含まれる全てのタイムゾーンを見つけなさい.
 */
public class Difference {
	private static List<String> result = new ArrayList<>();

	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.now(), Clock.systemUTC().getZone());
		ZoneId.getAvailableZoneIds().stream().filter(zoneId -> Duration.between(now.atZone(ZoneId.of(zoneId)), utc).abs().minusHours(1).isNegative()).forEach(System.out::println);
	}
}
