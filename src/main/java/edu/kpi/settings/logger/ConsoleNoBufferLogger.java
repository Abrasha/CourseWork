package edu.kpi.settings.logger;

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
