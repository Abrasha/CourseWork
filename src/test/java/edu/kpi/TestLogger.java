package edu.kpi;

import edu.kpi.settings.logger.*;
import edu.kpi.settings.logger.decorator.LeveledLogger;
import edu.kpi.settings.logger.decorator.TimeStampLogger;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestLogger {

    @Test
    public void testFileLogger() throws IOException {
        final Logger fileLogger = new FileLogger(new File("plain_file_logger.log"));
        fileLogger.log(Logger.Level.INFO, "File logger created - INFO");
        fileLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        fileLogger.log(Logger.Level.WARN, "It warns - WARN");
        fileLogger.getPrintStream().close(); // TODO
    }

    @Test
    public void testConsoleLogger() throws IOException {
        final Logger fileLogger = new ConsoleLogger();
        fileLogger.log(Logger.Level.INFO, "Consolelogger created - INFO");
        fileLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        fileLogger.log(Logger.Level.WARN, "It warns - WARN");
    }

    @Test
    public void testTimeStampLogger() throws IOException {
        final Logger timeStampFileLogger = new TimeStampLogger(
                new FileLogger(
                        new File("timestamp_file_logger.log")
                )
        );
        timeStampFileLogger.log(Logger.Level.INFO, "Timestamp File logger created - INFO");
        timeStampFileLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        timeStampFileLogger.log(Logger.Level.WARN, "It warns - WARN");
        timeStampFileLogger.getPrintStream().close(); // TODO

        final Logger timeStampConsoleLogger = new TimeStampLogger(
                new ConsoleLogger()
        );
        timeStampConsoleLogger.log(Logger.Level.INFO, "Timestamp Console logger created - INFO");
        timeStampConsoleLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        timeStampConsoleLogger.log(Logger.Level.WARN, "It warns - WARN");

    }

    @Test
    public void leveledLogger() throws IOException {
        final Logger leveledFileLogger = new LeveledLogger(
                new FileLogger(
                        new File("leveled_file_logger.log")
                )
        );
        leveledFileLogger.log(Logger.Level.INFO, "Leveled File logger created - INFO");
        leveledFileLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        leveledFileLogger.log(Logger.Level.WARN, "It warns - WARN");
        leveledFileLogger.getPrintStream().close(); // TODO

        final Logger leveledConsoleLogger = new LeveledLogger(
                new ConsoleLogger()
        );
        leveledConsoleLogger.log(Logger.Level.INFO, "Leveled Console logger created - INFO");
        leveledConsoleLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        leveledConsoleLogger.log(Logger.Level.WARN, "It warns - WARN");

    }

    @Test
    public void leveledTimeStampLogger() throws IOException {
        final Logger leveledTimeStampFileLogger = new TimeStampLogger(
                new LeveledLogger(
                        new FileLogger(
                                new File("leveled_timestamp_file_logger.log")
                        )
                )
        );
        leveledTimeStampFileLogger.log(Logger.Level.INFO, "Leveled TimeStamp File logger created - INFO");
        leveledTimeStampFileLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        leveledTimeStampFileLogger.log(Logger.Level.WARN, "It warns - WARN");
        leveledTimeStampFileLogger.getPrintStream().close(); // TODO

        final Logger leveledTimeStampConsoleLogger = new TimeStampLogger(new LeveledLogger(
                new ConsoleLogger()
        )
        );
        leveledTimeStampConsoleLogger.log(Logger.Level.INFO, "Leveled TimeStamp Console logger created - INFO");
        leveledTimeStampConsoleLogger.log(Logger.Level.ERROR, "It errors - ERROR");
        leveledTimeStampConsoleLogger.log(Logger.Level.WARN, "It warns - WARN");

    }

}
