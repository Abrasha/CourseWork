package edu.kpi.settings.logger;

import java.io.PrintStream;

/**
 * Created by Abrasha on 17-Apr-16.
 */
public class ConsoleLogger implements Logger {

    private final PrintStream out;

    public ConsoleLogger() {
        this.out = System.out;
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
