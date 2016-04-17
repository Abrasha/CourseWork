package edu.kpi.settings.logger.decorator;

import edu.kpi.settings.logger.Logger;

import java.io.IOException;
import java.io.PrintStream;

// TODO Decorator Pattern
public class LeveledLogger implements Logger {

    private final Logger logger;

    public LeveledLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, String message) throws IOException {
        String s = String.format("%-7s ", "[" + level.toString() + "]");
        logger.getPrintStream().append(s);
        logger.log(level, message);
    }

    @Override
    public PrintStream getPrintStream() {
        return logger.getPrintStream();
    }
}
