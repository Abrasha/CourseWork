package edu.kpi;

import edu.kpi.boot.AbstractJavaFxApplicationSupport;
import edu.kpi.settings.ConfigurationControllers;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.factory.LoggerFactory;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    @Autowired
    @Qualifier("loggingMediator")
    private LoggingMediator LOGGER;

    @Value("${ui.title}")
    private String windowTitle;
    @Autowired
    @Qualifier("employeesView")
    private ConfigurationControllers.View view;

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        LOGGER.addLogger(
                LoggerFactory.getLogger(LoggerFactory.LoggerType.CONSOLE_NO_BUFFER, LoggerFactory.LoggerAppender.LEVEL)
        );

        LOGGER.log(Logger.Level.INFO, "App started.");
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
        LOGGER.log(Logger.Level.INFO, "Main window is shown.");
    }

}
