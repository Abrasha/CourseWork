package edu.kpi.view;

import com.aquafx_project.AquaFx;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import edu.kpi.settings.spring.ViewControllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @Autowired
    private ViewControllers.View employeesView;
    @Autowired
    private ViewControllers.View taxesView;
    @Autowired
    private ViewControllers.View atmView;
    @Autowired
    private ViewControllers.View usersView;
    @Autowired
    private ViewControllers.View loginView;

    @Autowired
    private LoggingMediator LOGGER;

    @FXML
    private StackPane contentView;

    @FXML
    private VBox menuPane;

    @FXML
    private Label statusLabel;

    @FXML
    private Button btnEmployees;
    @FXML
    private Button btnTaxes;
    @FXML
    private Button btnATM;
    @FXML
    private Button btnUsers;

    @FXML
    private MenuItem miLogout;
    @FXML
    private MenuItem miExit;
    @FXML
    private MenuItem miEarth;
    @FXML
    private MenuItem miFire;
    @FXML
    private MenuItem miAir;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuPane.setVisible(false);
        btnEmployees.setOnAction(this::showEmployeesList);
        btnTaxes.setOnAction(this::showTaxesView);
        btnATM.setOnAction(this::showATM);
        btnUsers.setOnAction(this::showUsers);
        miLogout.setOnAction(this::miLogoutClicked);
        miExit.setOnAction(this::miExitClicked);
        miAir.setOnAction(e -> AquaFx.setAirStyle());
        miFire.setOnAction(e -> AquaFx.setFireStyle());
        miEarth.setOnAction(e -> AquaFx.setEarthStyle());
    }

    private void miLogoutClicked(ActionEvent event) {
        setAuthenticated(false);
        setContent(loginView.getView());
        LOGGER.log(Logger.Level.INFO, "Logging out...");
        this.setStatus("Logged out.");
    }

    private void miExitClicked(ActionEvent event) {
        LOGGER.log(Logger.Level.INFO, "Closing the app...");
        ((Stage) btnATM.getScene().getWindow()).close(); // Exit the application.
        this.setStatus("Exiting app.");
    }

    private void showEmployeesList(ActionEvent event) {
        LOGGER.log(Logger.Level.INFO, "Setting Employees view...");
        contentView.getChildren().setAll(
                employeesView.getView()
        );
    }

    private void showUsers(ActionEvent event) {
        LOGGER.log(Logger.Level.INFO, "Setting Users view...");
        contentView.getChildren().setAll(
                usersView.getView()
        );
        this.setStatus("Users view set.");
    }

    private void showTaxesView(ActionEvent event) {
        LOGGER.log(Logger.Level.INFO, "Setting Taxed view...");
        contentView.getChildren().setAll(
                taxesView.getView()
        );
        this.setStatus("Taxed view set.");
    }

    private void showATM(ActionEvent event) {
        LOGGER.log(Logger.Level.INFO, "Setting ATM view...");
        contentView.getChildren().setAll(
                atmView.getView()
        );
        this.setStatus("ATM view set.");
    }

    public void setContent(Node content) {
        LOGGER.log(Logger.Level.INFO, "Content changed");
        contentView.getChildren().setAll(content);
        this.setStatus("Content changed.");
    }

    protected void setAuthenticated(boolean isAuthenticated) {
        LOGGER.log(Logger.Level.INFO, "Menu visibility: " + isAuthenticated);
        menuPane.setVisible(isAuthenticated);
        this.setStatus("User is authenticated: " + isAuthenticated);
    }


    public void setStatus(String message) {
        LOGGER.log(Logger.Level.INFO, "New Status label content: " + message);
        this.statusLabel.setText(message);
    }

}
