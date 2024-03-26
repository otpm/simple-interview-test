package hu.otpmobil.simple.interview.adapter.jms;

import hu.otpmobil.simple.interview.common.Consts;
import hu.otpmobil.simple.library.loggerprovider.AbstractLoggerProviderImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Slf4j(topic = Consts.MESSAGE_LOG_TOPIC)
@Service
public class InterviewLoggerProviderImpl extends AbstractLoggerProviderImpl {

	@Override
	public Logger getMsgLogger() {
		return log;
	}

}
