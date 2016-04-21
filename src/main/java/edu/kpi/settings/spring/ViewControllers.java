package edu.kpi.settings.spring;

import edu.kpi.Application;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import edu.kpi.view.ATMController;
import edu.kpi.view.EmployeeController;
import edu.kpi.view.RootController;
import edu.kpi.view.TaxController;
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

    @Bean(name = "employeesView")
    @Lazy
    public View getEmployeesView() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [EmployeesView] created.");
        return loadView("fxml/employees.fxml");
    }

    @Bean(name = "atmView")
    public View getATMView() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [ATMView] created.");
        return loadView("fxml/atm.fxml");
    }

    @Bean(name = "rootView")
    public View getRootView() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [RootView] created.");
        return loadView("fxml/root.fxml");
    }

    @Bean(name = "taxesView")
    @Lazy
    public View getTaxView() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [TaxView] created.");
        return loadView("fxml/tax-reports.fxml");
    }

    @Bean
    public TaxController getTaxController() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [TaxController] created");
        return (TaxController) getTaxView().getController();
    }

    @Bean
    public RootController getRootController() throws IOException {
        LOGGER.log(Logger.Level.INFO, "Bean [RootController] created");
        return (RootController) getRootView().getController();
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


    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
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
