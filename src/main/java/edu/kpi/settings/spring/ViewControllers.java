package edu.kpi.settings.spring;

import edu.kpi.Application;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import edu.kpi.view.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ViewControllers {

    @Autowired
    private LoggingMediator LOGGER;

    @Lazy
    @Bean(name = "employeesView")
    public View getEmployeesView() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [EmployeesView] created.");
        return loadView("fxml/employees.fxml");
    }

    @Lazy
    @Bean(name = "atmView")
    public View getATMView() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [ATMView] created.");
        return loadView("fxml/atm.fxml");
    }

    @Lazy
    @Bean(name = "loginView")
    public View getLoginView() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [LoginView] created.");
        return loadView("fxml/login.fxml");
    }

    @Lazy
    @Bean(name = "rootView")
    public View getRootView() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [RootView] created.");
        return loadView("fxml/root.fxml");
    }

    @Lazy
    @Bean(name = "taxesView")
    public View getTaxView() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [TaxView] created.");
        return loadView("fxml/tax-reports.fxml");
    }

    @Lazy
    @Bean(name = "usersView")
    public View getUsersView() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [UsersView] created.");
        return loadView("fxml/users.fxml");
    }

    @Bean
    public TaxController getTaxController() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [TaxController] created");
        return (TaxController) getTaxView().getController();
    }

    @Bean
    public UsersController getUsersController() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [UsersController] created");
        return (UsersController) getUsersView().getController();
    }

    @Bean
    public RootController getRootController() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [RootController] created");
        return (RootController) getRootView().getController();
    }

    @Bean
    public LoginController getLoginController() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [LoginController] created");
        return (LoginController) getLoginView().getController();
    }

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его сделать произвести все необходимые инъекции.
     */
    @Bean
    public EmployeeController getEmployeesController() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [EmployeesController] created");
        return (EmployeeController) getEmployeesView().getController();
    }

    @Bean
    public ATMController getATMController() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [ATMController] created");
        return (ATMController) getATMView().getController();
    }


    private View loadView(String url) throws IOException {
        LOGGER.log(Logger.Level.INFO, "View [" + url + "] is loading.");
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new View(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    /**
     * Класс - оболочка: контроллер мы обязаны указать в качестве бина,
     * а view - представление, нам предстоит использовать в точке входа {@link Application}.
     */
    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }

}
