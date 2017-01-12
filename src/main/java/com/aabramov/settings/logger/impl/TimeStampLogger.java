package com.aabramov.settings.logger.impl;

import com.aabramov.settings.logger.Logger;

import java.io.PrintStream;
import java.time.LocalDateTime;

public class TimeStampLogger implements Logger {
    
    private final Logger logger;
    
    public TimeStampLogger(Logger logger) {
        this.logger = logger;
    }
    
    @Override
    public void log(Level level, String message) {
        String s = String.format("%s: ", LocalDateTime.now());
        logger.getPrintStream().append(s);
        logger.log(level, message);
    }
    
    @Override
    public PrintStream getPrintStream() {
        return logger.getPrintStream();
    }
}
