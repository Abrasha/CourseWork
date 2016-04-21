package edu.kpi.view;

import edu.kpi.model.Employee;
import edu.kpi.service.entities.EmployeeService;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.mediator.LoggingMediator;
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

public class EmployeeController implements Initializable {

    @Autowired
    private LoggingMediator LOGGER;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RootController rootController;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;

    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtPassportId;

    @FXML
    private TableView<Employee> tableEmployees;

    private ObservableList<Employee> employees;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdd.setOnAction(this::btnAddClicked);
        btnRemove.setOnAction(this::btnRemoveClicked);
        initTable();
    }

    @SuppressWarnings("unchecked")
    private void initTable() {

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
    private void fetchData() {
        employees.addAll(
                employeeService.findAll()
        );
        LOGGER.log(Logger.Level.INFO, "EmployeeController @PostConstruct block");
        rootController.setStatus("Employees shown.");
    }

    private void btnAddClicked(ActionEvent event) {
        LOGGER.log(Logger.Level.INFO, "Button [Add Employee] clicked.");
        Employee added = new Employee.EmployeeBuilder()
                .setFName(txtFName.getText()) // TODO validation
                .setLName(txtLName.getText())
                .setPhone(txtPhone.getText())
                .setPassportId(txtPassportId.getText())
                .build();
        employeeService.save(added);
        employees.add(added);

        rootController.setStatus("New Employee added.");

        this.cleanFields();
    }

    private void btnRemoveClicked(ActionEvent event) {
        LOGGER.log(Logger.Level.INFO, "Button [Remove Employee] clicked.");
        Employee selected = tableEmployees.getSelectionModel().getSelectedItem();
        if (selected != null) {
            employeeService.remove(selected);
            employees.remove(selected);

            rootController.setStatus("Employee removed.");
        }
        rootController.setStatus("No selected Employee to remove.");
    }

    private void cleanFields() {
        txtFName.clear();
        txtLName.clear();
        txtPhone.clear();
        txtPassportId.clear();
    }


}
