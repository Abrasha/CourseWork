package edu.kpi.view;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuPane.setVisible(false);
        btnEmployees.setOnAction(this::showEmployeesList);
        btnTaxes.setOnAction(this::showTaxesView);
        btnATM.setOnAction(this::showATM);
        btnUsers.setOnAction(this::showUsers);
        miLogout.setOnAction(this::miLogoutClicked);
        miExit.setOnAction(this::miExitClicked);
    }

    private void miLogoutClicked(ActionEvent event) {
        setAuthenticated(false);
        setContent(loginView.getView());
    }

    private void miExitClicked(ActionEvent event) {
        ((Stage) btnATM.getScene().getWindow()).close(); // Exit the application.
    }

    private void showEmployeesList(ActionEvent event) {
        contentView.getChildren().setAll(
                employeesView.getView()
        );
    }

    private void showUsers(ActionEvent event) {
        contentView.getChildren().setAll(
                usersView.getView()
        );
    }

    private void showTaxesView(ActionEvent event) {
        contentView.getChildren().setAll(
                taxesView.getView()
        );
    }

    private void showATM(ActionEvent event) {
        contentView.getChildren().setAll(
                atmView.getView()
        );
    }

    public void setContent(Node content) {
        contentView.getChildren().setAll(content);
    }

    protected void setAuthenticated(boolean isAuthenticated) {
        menuPane.setVisible(isAuthenticated);
    }


    public void setStatus(String message) {
        this.statusLabel.setText(message);
    }

}
