package com.aabramov.settings.spring;

import com.aabramov.service.security.PasswordService;
import com.aabramov.service.atm.AutomatedTellerMachine;
import com.aabramov.service.atm.CashProvider;
import com.aabramov.service.security.impl.MD5PasswordService;
import com.aabramov.settings.logger.factory.LoggerFactory;
import com.aabramov.settings.logger.mediator.LoggingMediator;
import com.aabramov.settings.logger.mediator.impl.LoggingMediatorImpl;
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
                LoggerFactory.getLogger(LoggerFactory.LoggerType.CONSOLE_NO_BUFFER, LoggerFactory.LoggerAppender.LEVEL)
//                , LoggerFactory.getLogger(LoggerFactory.LoggerType.FILE, LoggerFactory.LoggerAppender.LEVEL)
        );
        return result;
    }

    @Bean
    public PasswordService passwordService() {
        return new MD5PasswordService();
    }

}
