package edu.kpi.view;

import edu.kpi.model.Employee;
import edu.kpi.service.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Abrasha on 17-Apr-16.
 */
public class EmployeeController implements Initializable {

    @Autowired
    EmployeeService employeeService;

    @FXML
    Button btnAdd;
    @FXML
    Button btnRemove;

    @FXML
    TextField txtFName;
    @FXML
    TextField txtLName;
    @FXML
    TextField txtPhone;
    @FXML
    TextField txtPassportId;

    @FXML
    TableView<Employee> tableEmployees;

    ObservableList<Employee> employees;

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL location, ResourceBundle resources) {
        btnAdd.setOnAction(this::btnAddClicked);
        btnRemove.setOnAction(this::btnRemoveClicked);

        employees = FXCollections.observableArrayList();

        TableColumn<Employee, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee, String> fNameCol = new TableColumn<>("First name");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Employee, String> lNameCol = new TableColumn<>("Last name");
        lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Employee, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Employee, String> passportIdCol = new TableColumn<>("Passport ID");
        passportIdCol.setCellValueFactory(new PropertyValueFactory<>("passportId"));

        tableEmployees.getColumns().setAll(idCol, fNameCol, lNameCol, phoneCol, passportIdCol);

        tableEmployees.setItems(employees);
    }

    @PostConstruct
    private void init() {
        employees.setAll(
                employeeService.findAll()
        );
    }

    private void btnAddClicked(ActionEvent event) {
        Employee added = new Employee.EmployeeBuilder()
                .setFName(txtFName.getText())
                .setLName(txtLName.getText())
                .setPhone(txtPhone.getText())
                .setPassportId(txtPassportId.getText())
                .build();
        employeeService.save(added);
        employees.add(added);

        txtFName.clear();
        txtLName.clear();
        txtPassportId.clear();
        txtPhone.clear();
    }

    private void btnRemoveClicked(ActionEvent event) {
        Employee selected = tableEmployees.getSelectionModel().getSelectedItem();
        if (selected != null) {
            employeeService.remove(selected);
            employees.remove(selected);
        }
    }


}
