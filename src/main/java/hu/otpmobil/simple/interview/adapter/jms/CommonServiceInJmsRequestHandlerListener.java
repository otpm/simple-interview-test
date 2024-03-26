package hu.otpmobil.simple.interview.adapter.jms;

import hu.otpmobil.simple.interview.common.Consts;
import hu.otpmobil.simple.interview.configuration.properties.ActiveMqProperties;
import hu.otpmobil.simple.library.jms.core.message.annotation.JmsRequestHandlerListener;
import hu.otpmobil.simple.library.jms.core.message.legacy.AbstractLegacyRequestHandlerListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
@JmsRequestHandlerListener(value = Consts.COMMON_SERVICE_IN_JMS_REQUEST_HANDLER_LISTENER)
@RequiredArgsConstructor
public class CommonServiceInJmsRequestHandlerListener extends AbstractLegacyRequestHandlerListener {

	private final ActiveMqProperties activeMqProperties;

	@Override
	protected String getDefaultReplyToQueueName() {
		return activeMqProperties.getCommonServiceOutQueueName();
	}

	@Override
	protected Logger getPrgLogger() {
		return log;
	};

}
