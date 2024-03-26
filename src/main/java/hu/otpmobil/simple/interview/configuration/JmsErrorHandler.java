package hu.otpmobil.simple.interview.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Slf4j
@Component
public class JmsErrorHandler implements ErrorHandler {

	@Override
	public void handleError(Throwable t) {
		log.error("", t);
	}

}
