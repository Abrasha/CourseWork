package edu.kpi.view;

import edu.kpi.model.User;
import edu.kpi.service.entities.UserService;
import edu.kpi.service.security.PasswordService;
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
        users.setAll(
                userService.getAll()
        );
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

        final String username = txtUsername.getText();

        if (userService.userExists(username)) {
            lblStatus.setText("Error. User with such login exists.");
            txtUsername.requestFocus();
        } else {
            final String password = txtPassword.getText();
            final User added = new User(username, passwordService.encrypt(password));
            userService.addNewUser(added);
            users.add(added);
        }
    }

    private void removeUser() {
//        LOGGER.log(Logger.Level.INFO, "Button [Remove Employee] clicked.");

        if (users.size() < 2) {
            lblStatus.setText("Error. You cannot delete the last user");
        }

        final User selected = tableUsers.getSelectionModel().getSelectedItem();

        if (selected == null) {
            rootController.setStatus("No selected Employee to remove.");
            return;
        }

        if (selected.getUsername().equalsIgnoreCase("admin")) {
            lblStatus.setText("Error. You cannot delete admin user");
        } else {
            userService.deleteUser(selected);
            users.remove(selected);

            rootController.setStatus("User removed.");
        }
    }
}
