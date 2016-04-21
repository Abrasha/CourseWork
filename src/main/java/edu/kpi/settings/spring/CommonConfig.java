package edu.kpi.settings.spring;

import edu.kpi.service.atm.AutomatedTellerMachine;
import edu.kpi.service.atm.CashProvider;
import edu.kpi.service.security.PasswordService;
import edu.kpi.service.security.impl.MD5PasswordService;
import edu.kpi.settings.logger.factory.LoggerFactory;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import edu.kpi.settings.logger.mediator.impl.LoggingMediatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public CashProvider ATM() {
        return new AutomatedTellerMachine();
    }

    @Bean
    public LoggingMediator loggingMediator() {

        LoggingMediator result = LoggingMediatorImpl.getInstance();
        result.addLogger(
                LoggerFactory.getLogger(LoggerFactory.LoggerType.CONSOLE_NO_BUFFER, LoggerFactory.LoggerAppender.LEVEL),
                LoggerFactory.getLogger(LoggerFactory.LoggerType.FILE, LoggerFactory.LoggerAppender.LEVEL)
        );
        return result;
    }

    @Bean
    public PasswordService passwordService() {
        return new MD5PasswordService();
    }

}
