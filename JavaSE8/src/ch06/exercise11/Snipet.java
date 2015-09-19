package ch06.exercise11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Snipet {

	public void perform() {
		CompletableFuture.supplyAsync(() -> readPage("http://www.horstmann.com/")).thenApply(this::getLinks).handle((l, e) -> {
			if (e != null) {
				System.out.println(e.getMessage());
				return new ArrayList<>();
			} else {
				return l;
			}
		}).thenAccept(l -> l.stream().forEach(System.out::println));
		ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
	}

	/**
	 *
	 * @param urlString
	 * @return
	 * @throws NullPointerException
	 *             引数がnullのとき
	 * @throws MalformedURLException
	 *             引数が不正なとき
	 */
	public String readPage(String urlString) {
		Objects.requireNonNull(urlString, "A parmeter is null.");
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			throw new RuntimeException("Malformed URL", e);
		}
		try {
			URLConnection conn = url.openConnection();
			StringBuilder content = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String inputLine;
				while ((inputLine = br.readLine()) != null) {
					content.append(inputLine);
				}
			}
			return content.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getLinks(String content) {
		List<String> links = new ArrayList<>();
		Pattern p = Pattern.compile("(?i)href=\"http://(.*?)\"");
		Matcher m = p.matcher(content);
		while (m.find()) {
			links.add(m.group(1));
		}
		return links;
	}
}
