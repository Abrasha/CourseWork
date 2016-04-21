package edu.kpi;

import edu.kpi.boot.AbstractJavaFxApplicationSupport;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import edu.kpi.settings.spring.ViewControllers;
import edu.kpi.view.RootController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    @Autowired
    private LoggingMediator LOGGER;

    @Value("${ui.title}")
    private String windowTitle;

    @Autowired
    private ViewControllers.View rootView;

    @Autowired
    private ViewControllers.View loginView;

    @Autowired
    private RootController rootController;

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        LOGGER.log(Logger.Level.INFO, "App started.");
        stage.setTitle(windowTitle);
        rootController.setContent(loginView.getView());

        stage.setScene(new Scene(rootView.getView()));

        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
        LOGGER.log(Logger.Level.INFO, "Main window is shown.");
    }

}
