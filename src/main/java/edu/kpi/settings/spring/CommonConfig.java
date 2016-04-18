package edu.kpi.settings.spring;

import edu.kpi.settings.logger.factory.LoggerFactory;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import edu.kpi.settings.logger.mediator.LoggingMediatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean(name = "loggingMediator")
    public LoggingMediator loggingMediator() {

        LoggingMediator result = LoggingMediatorImpl.getInstance();
        result.addLogger(
                LoggerFactory.getLogger(LoggerFactory.LoggerType.CONSOLE_NO_BUFFER, LoggerFactory.LoggerAppender.LEVEL),
                LoggerFactory.getLogger(LoggerFactory.LoggerType.FILE, LoggerFactory.LoggerAppender.LEVEL)
        );
        return result;
    }

}
