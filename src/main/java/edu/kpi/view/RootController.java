package edu.kpi.view;

import edu.kpi.settings.spring.ViewControllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @Autowired
    private ViewControllers.View employeesView;

    @FXML
    private StackPane contentView;

    @FXML
    private Label statusLabel;

    @FXML
    private Button btnShowEmployees;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnShowEmployees.setOnAction(this::showEmployeesList);
    }

    private void showEmployeesList(ActionEvent event) {
        contentView.getChildren().setAll(
                employeesView.getView()
        );
    }

    public void setStatus(String message) {
        this.statusLabel.setText(message);
    }

}
