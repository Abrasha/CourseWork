package edu.kpi.settings.logger;

import java.io.IOException;
import java.io.PrintStream;

public interface Logger {

    void log(Level level, String message) throws IOException;
    PrintStream getPrintStream();

    enum Level {
        INFO, WARN, ERROR
    }
}
