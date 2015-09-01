package ch05.exercise11;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/*
 * 11. 帰りの便は, フランクフルトを14時5分に出発し, ロサンジェルスに16時40分に到着します.
 * 飛行時間は何時間何分ですか.このような計算をできるプログラムを書きなさい.
 */
public class Difference {

	public static void main(String[] args) {

		ZoneId losAngeles = ZoneId.of("UTC-8");
		ZoneId frankfurt = ZoneId.of("UTC+1");
		LocalDateTime departureTime = LocalDateTime.of(2015, 9, 1, 3, 5);
		Duration travelTime = Duration.ofMinutes(10 * 60 + 50);
		LocalDateTime arraivalTime = getArrivalTime(frankfurt, losAngeles, departureTime, travelTime);
		System.out.println(getArrivalTime(frankfurt, losAngeles, departureTime, travelTime));

		ZonedDateTime departure = ZonedDateTime.of(departureTime, losAngeles);
		ZonedDateTime arraival = ZonedDateTime.of(arraivalTime, frankfurt);
		System.out.println(travelTime.equals(getTravelTime(departure, arraival)));
	}

	/**
	 * 到着時刻を現地時刻で返す.
	 *
	 * @param arrivalZone
	 * @param departureZone
	 * @param timeOfDeparture
	 * @param travelTime
	 * @return
	 * @throws NullPointerException
	 *             - 引数がnullの場合
	 */
	public static LocalDateTime getArrivalTime(ZoneId arrivalZone, ZoneId departureZone, LocalDateTime timeOfDeparture, Duration travelTime) {
		Objects.requireNonNull(arrivalZone, "A parameter \"arrivalZone\" is null");
		Objects.requireNonNull(departureZone, "A parameter \"departureZone\" is null");
		Objects.requireNonNull(timeOfDeparture, "A parameter \"timeOfDeparture\" is null");
		Objects.requireNonNull(travelTime, "A parameter \"travelTime\" is null");
		return LocalDateTime.ofInstant(ZonedDateTime.of(timeOfDeparture, departureZone).plus(travelTime).toInstant(), arrivalZone);
	}

	/**
	 *
	 * @param departureTime
	 * @param arrivalTime
	 * @return
	 */
	public static Duration getTravelTime(ZonedDateTime departureTime, ZonedDateTime arrivalTime) {
		Objects.requireNonNull(departureTime, "A parameter \"departureTime\" is null");
		Objects.requireNonNull(arrivalTime, "A parameter \"arrivalTime\" is null");
		return Duration.between(departureTime, arrivalTime);
	}
}
