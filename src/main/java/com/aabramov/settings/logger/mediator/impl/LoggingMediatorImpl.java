package com.aabramov.settings.logger.mediator.impl;

import com.aabramov.settings.logger.mediator.LoggingMediator;
import com.aabramov.settings.logger.Logger;

import java.util.*;

public final class LoggingMediatorImpl implements LoggingMediator {
    
    private static LoggingMediator INSTANCE;
    
    private final Set<Logger> loggers;
    
    private LoggingMediatorImpl() {
        loggers = Collections.synchronizedSet(new HashSet<>(3));
    }
    
    public static LoggingMediator getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LoggingMediatorImpl();
        return INSTANCE;
    }
    
    @Override
    public void log(Logger.Level level, String message) {
        loggers.forEach(logger -> logger.log(level, message));
    }
    
    @Override
    public void addLogger(Logger... logger) {
        Objects.requireNonNull(logger);
        loggers.addAll(Arrays.asList(logger));
    }
}
