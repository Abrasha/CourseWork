package edu.kpi.settings.logger.impl;

import edu.kpi.settings.logger.Logger;

import java.io.PrintStream;

public class LeveledLogger implements Logger {

    private final Logger logger;

    public LeveledLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, String message) {
        String s = String.format("%-7s ", "[" + level.toString() + "]");
        logger.getPrintStream().append(s);
        logger.log(level, message);
    }

    @Override
    public PrintStream getPrintStream() {
        return logger.getPrintStream();
    }
}
