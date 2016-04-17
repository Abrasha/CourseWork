package edu.kpi.settings.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileLogger implements Logger {

    private final File destination;
    private final PrintStream out;

    public FileLogger(File destination) throws IOException {
        this.destination = destination;

        if (!destination.exists())
            destination.createNewFile();

        out = new PrintStream(new FileOutputStream(destination, true)); // true if we want to append to File
    }

    @Override
    public void log(Level level, String message) {
        out.append(message);
        out.println();
    }

    @Override
    public PrintStream getPrintStream() {
        return out;
    }
}
