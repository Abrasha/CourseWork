package edu.kpi.settings.logger.mediator;

import edu.kpi.settings.logger.Logger;

public interface LoggingMediator {
    void log(Logger.Level level, String message);

    void addLogger(Logger... logger);
}
