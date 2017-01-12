package com.aabramov.settings.logger.mediator;

import com.aabramov.settings.logger.Logger;

public interface LoggingMediator {
    void log(Logger.Level level, String message);
    
    void addLogger(Logger... logger);
}
