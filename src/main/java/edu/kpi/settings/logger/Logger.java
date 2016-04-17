package edu.kpi.settings.logger;

import java.io.PrintStream;

public interface Logger {

    void log(Level level, String message);
    PrintStream getPrintStream();

    enum Level {
        INFO, WARN, ERROR
    }
}
