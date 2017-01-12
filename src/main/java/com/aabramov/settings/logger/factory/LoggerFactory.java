package com.aabramov.settings.logger.factory;

import com.aabramov.settings.logger.impl.*;
import com.aabramov.settings.logger.Logger;

import java.io.File;

public class LoggerFactory {
    
    static final String LOG_FILE = "application.log";
    
    public static Logger getLogger(LoggerType type, LoggerAppender... appenders) {
        
        Logger result = parseType(type);
        result = decorate(result, appenders);
        
        return result;
    }
    
    private static Logger decorate(final Logger logger, LoggerAppender[] appenders) {
        
        Logger result = logger;
        
        for (LoggerAppender appender : appenders) {
            switch (appender) {
                case TIMESTAMP:
                    result = new TimeStampLogger(result);
                    break;
                case LEVEL:
                    result = new LeveledLogger(result);
                    break;
                default:
                    throw new IllegalArgumentException("No such Logger appender: " + appender);
            }
        }
        
        return result;
    }
    
    private static Logger parseType(LoggerType type) {
        switch (type) {
            case CONSOLE:
                return LazyConsoleLoggerKeeper.INSTANCE;
            case CONSOLE_NO_BUFFER:
                return LazyConsoleNoBufferLoggerKeeper.INSTANCE;
            case FILE:
                return LazyFileLoggerKeeper.INSTANCE;
            default:
                throw new IllegalArgumentException("No such Logger type: " + type);
        }
    }
    
    public static void closeLoggers() {
        LazyFileLoggerKeeper.INSTANCE.getPrintStream().close();
    }
    
    public enum LoggerType {
        FILE, CONSOLE, CONSOLE_NO_BUFFER
    }
    
    public enum LoggerAppender {
        TIMESTAMP, LEVEL
    }
    
    private static class LazyFileLoggerKeeper {
        private static final Logger INSTANCE = new FileLogger(new File(LOG_FILE));
    }
    
    private static class LazyConsoleLoggerKeeper {
        private static final Logger INSTANCE = new ConsoleLogger();
    }
    
    private static class LazyConsoleNoBufferLoggerKeeper {
        private static final Logger INSTANCE = new ConsoleNoBufferLogger();
    }
    
}
