package hu.otpmobil.simple.interview.common.repository;

import java.util.Optional;

public class RepositoryHelper {

	public static <T> T getOrThrow(Optional<T> optional, String message) {
		return optional.orElseThrow(() -> new IllegalArgumentException(message));
	}

	public static void nonZeroResultOrThrow(int result, String message) {
		if (result == 0) {
			throw new IllegalArgumentException(message);
		}
	}

	private RepositoryHelper() {
	}

}
