package com.aabramov.settings.logger.impl;

import com.aabramov.settings.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileLogger implements Logger {
    
    private PrintStream out;
    
    public FileLogger(File destination) {
        
        if (!destination.exists())
            try {
                destination.createNewFile();
                // true if we want to append to File
                out = new PrintStream(new FileOutputStream(destination, false));
            } catch (IOException e) {
                throw new IllegalStateException(destination.getAbsolutePath(), e);
            }
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
