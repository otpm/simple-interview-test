package hu.otpmobil.simple.interview.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtil {

	public static LocalDateTime now() {
		return LocalDateTime.now();
	}

	public static LocalDateTime startOfThisMonth() {
		LocalDateTime now = now();
		return LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0);
	}

}
