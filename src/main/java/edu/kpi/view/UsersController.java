package edu.kpi.view;

import edu.kpi.model.User;
import edu.kpi.service.entities.UserService;
import edu.kpi.service.security.PasswordService;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private RootController rootController;

    @Autowired
    private LoggingMediator LOGGER;

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private Label lblStatus;

    private ObservableList<User> users;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        users = FXCollections.observableArrayList();

        initTable();

        btnAdd.setOnAction(this::btnAddUserClicked);
        btnRemove.setOnAction(this::btnRemoveUser);
    }

    @PostConstruct
    public void fetchData() {
        LOGGER.log(Logger.Level.INFO, "Trying to fetch User data...");
        users.setAll(
                userService.getAll()
        );
        LOGGER.log(Logger.Level.INFO, "User data fetched.");
        rootController.setStatus("Data fetched.");
    }

    @SuppressWarnings("unchecked")
    private void initTable() {
        users = FXCollections.observableArrayList();

        tableUsers.setEditable(true);

        final TableColumn<User, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setEditable(false);

        final TableColumn<User, String> loginCol = new TableColumn<>("Login");
        loginCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        loginCol.setCellFactory(TextFieldTableCell.forTableColumn());
        loginCol.setEditable(true);

        loginCol.setOnEditCommit(event -> {
            final User selected = tableUsers.getSelectionModel().getSelectedItem();
            selected.setUsername(event.getNewValue());
            userService.updateUser(selected);
        });

        tableUsers.getColumns().setAll(idCol, loginCol);

        tableUsers.setItems(users);
    }

    private void btnAddUserClicked(ActionEvent event) {
        addUser();
    }

    private void btnRemoveUser(ActionEvent event) {
        removeUser();
    }

    private void addUser() {

        LOGGER.log(Logger.Level.INFO, "Trying to add new User...");
        final String username = txtUsername.getText();

        if (userService.userExists(username)) {
            lblStatus.setText("Error. User with such login exists.");
            txtUsername.requestFocus();
            LOGGER.log(Logger.Level.ERROR, "Such User exists: " + username);
            rootController.setStatus("Error adding User.");
        } else {
            final String password = txtPassword.getText();
            final User added = new User(username, passwordService.encrypt(password));
            userService.addNewUser(added);
            users.add(added);
            LOGGER.log(Logger.Level.INFO, "New User added: " + username);
            rootController.setStatus("New User added.");
        }
    }

    private void removeUser() {
        LOGGER.log(Logger.Level.INFO, "Trying to remove User...");

        if (users.size() < 2) {
            lblStatus.setText("Error. You cannot delete the last User");
            LOGGER.log(Logger.Level.INFO, "You cannot delete the last User.");
            rootController.setStatus("Error removing User.");
        }

        final User selected = tableUsers.getSelectionModel().getSelectedItem();

        if (selected == null) {
            rootController.setStatus("No selected User to remove.");
            LOGGER.log(Logger.Level.WARN, "No selected User to remove");
            rootController.setStatus("Error removing User.");
            return;
        }

        if (selected.getUsername().equalsIgnoreCase("admin")) {
            lblStatus.setText("Error. You cannot delete admin user");
            LOGGER.log(Logger.Level.ERROR, "You cannot delete admin user.");
            rootController.setStatus("Error removing admin User.");
        } else {
            userService.deleteUser(selected);
            users.remove(selected);

            rootController.setStatus("User removed.");
            LOGGER.log(Logger.Level.INFO, "User removed: " + selected.getUsername());
            rootController.setStatus("User removed.");
        }
    }
}
