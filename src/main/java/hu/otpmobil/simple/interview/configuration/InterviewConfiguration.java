package hu.otpmobil.simple.interview.configuration;

import hu.otpmobil.simple.eh.jwtauth.EnableJwtAuth;
import hu.otpmobil.simple.interview.InterviewSpringApplication;
import hu.otpmobil.simple.interview.configuration.properties.ActiveMqProperties;
import hu.otpmobil.simple.library.jms.EnableLegacyJms;
import hu.otpmobil.simple.nextgen.stats.EnableStats;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

@ComponentScan(basePackageClasses = {InterviewSpringApplication.class})
@ConfigurationPropertiesScan(basePackageClasses = ActiveMqProperties.class)
@Component
@EnableJwtAuth
@EnableStats
@EnableJms
@EnableLegacyJms
public class InterviewConfiguration {

}
