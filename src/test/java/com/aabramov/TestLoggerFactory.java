package com.aabramov;

import com.aabramov.settings.logger.factory.LoggerFactory;
import com.aabramov.settings.logger.Logger;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.IOException;

public class TestLoggerFactory {

    @Test
    public void testFileLogger() throws IOException {
        final Logger fileLogger = LoggerFactory.getLogger(LoggerFactory.LoggerType.FILE);
        fileLogger.log(Logger.Level.INFO, "File logger created - INFO");
        fileLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        fileLogger.log(Logger.Level.WARN, "It warns - WARN");
    }

    @Test
    public void testConsoleLogger() throws IOException {
        final Logger fileLogger = LoggerFactory.getLogger(LoggerFactory.LoggerType.CONSOLE);
        fileLogger.log(Logger.Level.INFO, "Consolelogger created - INFO");
        fileLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        fileLogger.log(Logger.Level.WARN, "It warns - WARN");
    }

    @Test
    public void testTimeStampLogger() throws IOException {
        final Logger timeStampFileLogger = LoggerFactory.getLogger(LoggerFactory.LoggerType.FILE, LoggerFactory.LoggerAppender.TIMESTAMP);
        timeStampFileLogger.log(Logger.Level.INFO, "Timestamp File logger created - INFO");
        timeStampFileLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        timeStampFileLogger.log(Logger.Level.WARN, "It warns - WARN");

        final Logger timeStampConsoleLogger = LoggerFactory.getLogger(LoggerFactory.LoggerType.CONSOLE, LoggerFactory.LoggerAppender.TIMESTAMP);
        timeStampConsoleLogger.log(Logger.Level.INFO, "Timestamp Console logger created - INFO");
        timeStampConsoleLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        timeStampConsoleLogger.log(Logger.Level.WARN, "It warns - WARN");

    }

    @Test
    public void leveledLogger() throws IOException {
        final Logger leveledFileLogger = LoggerFactory.getLogger(LoggerFactory.LoggerType.FILE, LoggerFactory.LoggerAppender.LEVEL);
        leveledFileLogger.log(Logger.Level.INFO, "Leveled File logger created - INFO");
        leveledFileLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        leveledFileLogger.log(Logger.Level.WARN, "It warns - WARN");

        final Logger leveledConsoleLogger = LoggerFactory.getLogger(LoggerFactory.LoggerType.CONSOLE, LoggerFactory.LoggerAppender.LEVEL);
        leveledConsoleLogger.log(Logger.Level.INFO, "Leveled Console logger created - INFO");
        leveledConsoleLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        leveledConsoleLogger.log(Logger.Level.WARN, "It warns - WARN");

    }

    @Test
    public void leveledTimeStampLogger() throws IOException {
        final Logger leveledTimeStampFileLogger = LoggerFactory.getLogger(LoggerFactory.LoggerType.FILE, LoggerFactory.LoggerAppender.TIMESTAMP, LoggerFactory.LoggerAppender.LEVEL);
        leveledTimeStampFileLogger.log(Logger.Level.INFO, "Leveled TimeStamp File logger created - INFO");
        leveledTimeStampFileLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        leveledTimeStampFileLogger.log(Logger.Level.WARN, "It warns - WARN");

        final Logger leveledTimeStampConsoleLogger = LoggerFactory.getLogger(LoggerFactory.LoggerType.CONSOLE, LoggerFactory.LoggerAppender.TIMESTAMP, LoggerFactory.LoggerAppender.LEVEL);
        leveledTimeStampConsoleLogger.log(Logger.Level.INFO, "Leveled TimeStamp Console logger created - INFO");
        leveledTimeStampConsoleLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        leveledTimeStampConsoleLogger.log(Logger.Level.WARN, "It warns - WARN");

    }

    @AfterClass
    public static void closeResources() {
        LoggerFactory.closeLoggers();
    }


}
