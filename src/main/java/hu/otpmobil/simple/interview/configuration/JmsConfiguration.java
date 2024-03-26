package hu.otpmobil.simple.interview.configuration;

import org.apache.activemq.pool.PooledConnectionFactory;
import hu.otpmobil.simple.interview.common.Consts;
import hu.otpmobil.simple.interview.configuration.properties.ActiveMqProperties;
import hu.otpmobil.simple.library.jms.core.JmsMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JmsConfiguration {

	private final ActiveMqProperties activeMqProperties;
	private final JmsMessageService jmsMessageService;

	@Bean
	public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = activeMqProperties.hasAuthenticationData()
                ? new ActiveMQConnectionFactory(activeMqProperties.getUsername(), activeMqProperties.getPassword(), activeMqProperties.getBrokerUrl())
                    : new ActiveMQConnectionFactory(activeMqProperties.getBrokerUrl());
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(connectionFactory);
		return pooledConnectionFactory;
	}

	@Bean
	public DefaultMessageListenerContainer commonServiceInMessageListenerContainer(
			ConnectionFactory connectionFactory,
			@Qualifier(Consts.COMMON_SERVICE_IN_JMS_REQUEST_HANDLER_LISTENER) MessageListener messageListener,
			ErrorHandler errorHandler) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		String messageSelector = buildSelectorForJmsTypes(Consts.COMMON_SERVICE_IN_LISTEN_JMSTYPES);
		String destinationName = activeMqProperties.getCommonServiceInQueueName();

		messageSelector += " and JMSCorrelationID like '" + Consts.JMS_DESTINATION_NAME + "-%'";
		container.setConnectionFactory(connectionFactory);
		container.setDestinationName(destinationName);
		container.setMessageSelector(messageSelector);
		container.setMessageListener(messageListener);
		container.setErrorHandler(errorHandler);
		jmsMessageService.registerListenerContainer(container);
		log.info("Message listener container created for {} destination. Selector={}", destinationName,
				messageSelector);

		return container;
	}

	private String buildSelectorForJmsTypes(List<String> jmsTypes) {
		String result = "JMSType in (";
		for (String jmsType : jmsTypes) {
			result += "'" + jmsType + "',";
		}
		return result.substring(0, result.length() - 1) + ")";
	}

}
