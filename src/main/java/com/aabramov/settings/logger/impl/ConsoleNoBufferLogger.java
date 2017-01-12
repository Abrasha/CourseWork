package com.aabramov.settings.logger.impl;

import com.aabramov.settings.logger.Logger;

import java.io.PrintStream;

public class ConsoleNoBufferLogger implements Logger {

    private final PrintStream out;

    public ConsoleNoBufferLogger() {
        this.out = System.err;
    }

    @Override
    public void log(Level level, String message) {
        out.println(message);
    }

    @Override
    public PrintStream getPrintStream() {
        return out;
    }
}
