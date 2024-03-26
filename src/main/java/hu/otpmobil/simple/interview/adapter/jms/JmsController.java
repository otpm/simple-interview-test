package hu.otpmobil.simple.interview.adapter.jms;

import hu.otpmobil.simple.interview.common.Consts;
import hu.otpmobil.simple.library.jms.core.message.annotation.JmsRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@RequiredArgsConstructor
public class JmsController {

	private final BuildProperties buildProperties;

	@JmsRequestHandler(requestHandlerListener = Consts.COMMON_SERVICE_IN_JMS_REQUEST_HANDLER_LISTENER, jmsType = {
			Consts.GET_VERSION_RQ })
	public GetVersionRsDTO handleCommonServiceInRequest() throws JMSException {
		return new GetVersionRsDTO(buildProperties.getVersion(), buildProperties.getTime().getEpochSecond());
	}

}
