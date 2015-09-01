package ch05.exercise10;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/*
 * 10. ロサンジェルスからフランクフルト行きの便は,
 * ローカル時刻の3時5分に出発し, 10時間50分の飛行です. 何時に到着しますか.
 * このような計算を処理できるプログラムを書きなさい.
 */
public class Difference {

	public static void main(String[] args) {

		ZoneId losAngeles = ZoneId.of("UTC-8");
		ZoneId frankfurt = ZoneId.of("UTC+1");
		LocalDateTime departureTime = LocalDateTime.of(2015, 9, 1, 3, 5);
		Duration travelTime = Duration.ofMinutes(10 * 60 + 50);

		System.out.println(getArrivalTime(frankfurt, losAngeles, departureTime, travelTime));
	}

	/**
	 * 到着時刻を現地時刻で返す.
	 * @param arrivalZone
	 * @param departureZone
	 * @param timeOfDeparture
	 * @param travelTime
	 * @return
	 * @throws NullPointerException - 引数がnullの場合
	 */
	public static LocalDateTime getArrivalTime(ZoneId arrivalZone, ZoneId departureZone, LocalDateTime timeOfDeparture, Duration travelTime) {
		Objects.requireNonNull(arrivalZone, "A parameter \"arrivalZone\" is null");
		Objects.requireNonNull(departureZone, "A parameter \"departureZone\" is null");
		Objects.requireNonNull(timeOfDeparture, "A parameter \"timeOfDeparture\" is null");
		Objects.requireNonNull(travelTime, "A parameter \"travelTime\" is null");
		return LocalDateTime.ofInstant(ZonedDateTime.of(timeOfDeparture, departureZone).plus(travelTime).toInstant(), arrivalZone);
	}
}
