package ch05.exercise07;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/*
 * 7. (指定された日付の午前10時から午前11時といった) カレンダーイベントに適した, 時刻のインターバルを表すTimeIntervalクラスを実装しなさい.
 * 2つのインターバルが重なっているかを検査するメソッドを提供しなさい.
 */
public class TimeInterval {
	private final LocalDateTime start;
	private final LocalDateTime endExclusive;

	public TimeInterval(LocalDateTime start, LocalDateTime endExclusive) {
		Objects.requireNonNull(start, "A parameter \"start\" is null.");
		Objects.requireNonNull(endExclusive, "A parameter \"endExclusive\" is null.");
		if (start.isBefore(endExclusive)) {
			this.start = start;
			this.endExclusive = endExclusive;
		} else {
			throw new IllegalArgumentException("endExclusive is ");
		}
	}

	public TimeInterval(int startYear, int startMonth, int startDayOfMonth, int startHour, int startMinute, int endYear, int endMonth, int endDayOfMonth, int endHour, int endMinute) {
		checkParameter(startYear, startMonth, startDayOfMonth, startHour, startMinute, endYear, endMonth, endDayOfMonth, endHour, endMinute);
		this.start = LocalDateTime.of(startYear, startMonth, startDayOfMonth, startHour, startMinute);
		this.start.plus(startHour / 24, ChronoUnit.HOURS);
		this.endExclusive = LocalDateTime.of(endYear, endMonth, endDayOfMonth, endHour, endMinute);
		this.endExclusive.plus(endHour / 24, ChronoUnit.HOURS);
	}

	public TimeInterval(int startAndEndYear, int startAndEndMonth, int startDayOfMonth, int startHour, int startMinute, int endDayOfMonth, int endHour, int endMinute) {
		this(startAndEndYear, startAndEndMonth, startDayOfMonth, startHour, startMinute, startAndEndYear, startAndEndMonth, endDayOfMonth, endHour, endMinute);
	}

	public TimeInterval(int startAndEndYear, int startAndEndMonth, int startAndEndDayOfMonth, int startHour, int startMinute, int endHour, int endMinute) {
		this(startAndEndYear, startAndEndMonth, startAndEndDayOfMonth, startHour, startMinute, startAndEndYear, startAndEndMonth, startAndEndDayOfMonth, endHour, endMinute);
	}

	/**
	 * パラメータチェック. hourは24単位で繰り上がる.
	 *
	 * @param startYear
	 * @param startMonth
	 * @param startDayOfMonth
	 * @param startHour
	 * @param startMinute
	 * @param endYear
	 * @param endMonth
	 * @param endDayOfMonth
	 * @param endHour
	 * @param endMinute
	 */
	private void checkParameter(int startYear, int startMonth, int startDayOfMonth, int startHour, int startMinute, int endYear, int endMonth, int endDayOfMonth, int endHour, int endMinute) {
		checkRange(-1000000000, 1000000000, startYear);
		checkRange(1, 13, startMonth);
		checkRange(1, 32, startDayOfMonth);
		startHour = startHour % 24;
		checkRange(0, 60, startMinute);
		checkRange(-1000000000, 1000000000, endYear);
		checkRange(1, 13, endMonth);
		checkRange(1, 32, endDayOfMonth);
		endHour = endHour % 24;
		checkRange(0, 60, endMinute);
	}

	/**
	 * 値のチェック
	 *
	 * @param startInclusive
	 * @param endExclusive
	 * @param target
	 * @return
	 */
	private int checkRange(int startInclusive, int endExclusive, int target) {
		if (target < startInclusive || endExclusive <= target) {
			throw new IllegalArgumentException("A parameter target is out of range");
		}
		return target;
	}

	/**
	 *
	 * @param counterPart
	 * @return
	 */
	public boolean isOverlappe(TimeInterval counterPart) {
		return !(this.start.isBefore(counterPart.start) && this.endExclusive.isBefore(counterPart.start) || counterPart.start.isBefore(this.start) && counterPart.endExclusive.isBefore(this.start));
	}
}
