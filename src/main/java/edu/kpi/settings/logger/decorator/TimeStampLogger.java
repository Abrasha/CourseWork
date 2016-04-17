package edu.kpi.settings.logger.decorator;

import edu.kpi.settings.logger.Logger;

import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;

// TODO Decorator Pattern
public class TimeStampLogger implements Logger {

    private final Logger logger;

    public TimeStampLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, String message) throws IOException {
        String s = String.format("%s: ", LocalDateTime.now());
        logger.getPrintStream().append(s);
        logger.log(level, message);
    }

    @Override
    public PrintStream getPrintStream() {
        return logger.getPrintStream();
    }
}
