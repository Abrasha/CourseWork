package com.aabramov;

import com.aabramov.settings.logger.mediator.LoggingMediator;
import com.aabramov.settings.spring.ViewControllers;
import com.aquafx_project.AquaFx;
import com.aabramov.boot.AbstractJavaFxApplicationSupport;
import com.aabramov.settings.logger.Logger;
import com.aabramov.view.RootController;
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
    
    public static void main(final String[] args) {
        launchApp(Application.class, args);
    }
    
    @Override
    public void start(final Stage stage) throws Exception {
        
        LOGGER.log(Logger.Level.INFO, "App started.");
        stage.setTitle(windowTitle);
        rootController.setContent(loginView.getView());
        
        stage.setScene(new Scene(rootView.getView()));
        rootController.setStatus("Login view set.");
        
        stage.setResizable(true);
        stage.centerOnScreen();
        
        AquaFx.setFireStyle();
        
        stage.show();
        LOGGER.log(Logger.Level.INFO, "Main window is shown.");
    }
    
}
