package ch06.exercise07;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 7. ConcurrentHashMap<String, Long>内で, 最大値を持つキーを見つけなさい(同じ最大値を持つキーがあれば,
 * どちらのキーでもかまいません).ヒント: reduceEntries.
 */
public class FindingMaxValue {
	private static final long THRESHOLD = 5;

	public static void main(String[] args) {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String, Long>() {
			private static final long serialVersionUID = 6708692783631927891L;
			{
				put("6", Long.valueOf(6L));
				put("5", Long.valueOf(5L));
				put("4", Long.valueOf(4L));
				put("3", Long.valueOf(3L));
				put("2", Long.valueOf(2L));
				put("1", Long.valueOf(1L));
			}
		};
		Map.Entry<String, Long> entry = map.reduceEntries(THRESHOLD, (t, u) -> {
			return t.getValue() <= u.getValue() ? u : t;
		});
		System.out.println(entry.getKey());
	}
}
