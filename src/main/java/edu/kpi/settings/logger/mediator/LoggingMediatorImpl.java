package edu.kpi.settings.logger.mediator;

import edu.kpi.settings.logger.Logger;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class LoggingMediatorImpl implements LoggingMediator {

    private static LoggingMediator INSTANCE;

    private Set<Logger> loggers;

    private LoggingMediatorImpl() {
        loggers = new HashSet<>(3);
    }

    public static LoggingMediator getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LoggingMediatorImpl();
        return INSTANCE;
    }

    @Override
    public void log(Logger.Level level, String message) {
        loggers.forEach(logger -> {
            logger.log(level, message);
        });
    }

    @Override
    public void addLogger(Logger logger) {
        Objects.requireNonNull(logger);
        loggers.add(logger);
    }
}
