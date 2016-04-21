package edu.kpi.view;

import edu.kpi.model.User;
import edu.kpi.service.entities.UserService;
import edu.kpi.settings.spring.ViewControllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @Autowired
    private UserService userService;

    @Autowired
    private RootController rootController;

    @Autowired
    private ViewControllers.View employeesView;

    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblStatus;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnClear;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
    }

    private void initButtons() {
        btnLogin.setOnAction(this::btnLoginClicked);
        btnLogin.requestFocus();

        btnClear.setOnAction(this::btnClearClicked);
    }

    private void btnLoginClicked(ActionEvent event) {
        this.authenticate();
    }

    private void authenticate() {
        final String username = txtLogin.getText();
        final String password = txtPassword.getText();

        final User user = userService.authenticate(username, password);
        if (Objects.isNull(user)) {
            onLoginFail();
        } else {
            rootController.setContent(employeesView.getView());
            rootController.setAuthenticated(true);
        }
    }

    private void onLoginFail() {
        lblStatus.setText("Authentication failed. Check your credentials.");
        txtPassword.clear();
    }

    private void btnClearClicked(ActionEvent event) {
        txtLogin.clear();
        txtPassword.clear();
    }


}
