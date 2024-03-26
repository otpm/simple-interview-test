package hu.otpmobil.simple.interview.configuration.properties;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@Data
@ConfigurationProperties(prefix = "interview.activemq")
public class ActiveMqProperties {

	@NotNull
	private String brokerUrl;

	private String username;
	private String password;

	@NotNull
	private String commonServiceInQueueName;

	@NotNull
	private String commonServiceOutQueueName;

	public boolean hasAuthenticationData() {
		return !StringUtils.isBlank(username);
	}

}
