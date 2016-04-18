package edu.kpi.view;

import edu.kpi.model.Employee;
import edu.kpi.model.TaxReport;
import edu.kpi.service.EmployeeService;
import edu.kpi.service.tax.SingleTax;
import edu.kpi.service.tax.TaxCalculator;
import edu.kpi.service.tax.TaxOnProfits;
import edu.kpi.service.tax.TaxProcessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TaxController implements Initializable {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RootController rootController;

    @FXML
    private TableView<TaxReport> reportsTable;

    @FXML
    private ChoiceBox<TaxProcessor.TaxType> choiceStrategy;

    @FXML
    private Button btnProcessTaxes;

    @FXML
    private Button btnSaveReports;

    private ObservableList<TaxReport> items;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initTaxStrategy();
        initButtons();
    }

    @SuppressWarnings("unchecked")
    private void initTable() {
        items = FXCollections.observableArrayList();

        reportsTable.setEditable(true); // important

        final TableColumn<TaxReport, String> employeeCol = new TableColumn<>("Employee");
        employeeCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        final TableColumn<TaxReport, String> passportIdCol = new TableColumn<>("Passport ID");
        passportIdCol.setCellValueFactory(new PropertyValueFactory<>("passportId"));

        final TableColumn<TaxReport, Integer> incomeCol = new TableColumn<>("Income");
        incomeCol.setCellValueFactory(new PropertyValueFactory<>("income"));
        incomeCol.setEditable(true);
        incomeCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        final TableColumn<TaxReport, Integer> taxCol = new TableColumn<>("Total tax");
        taxCol.setCellValueFactory(new PropertyValueFactory<>("tax"));

        final TableColumn<TaxReport, Integer> profitCol = new TableColumn<>("Passport ID");
        profitCol.setCellValueFactory(new PropertyValueFactory<>("profit"));


        incomeCol.setOnEditCommit(event -> {

            final TaxReport selected = reportsTable.getSelectionModel().getSelectedItem();

            selected.setIncome(event.getNewValue());
            selected.setTax(0);
            selected.setProfit(0);
            btnProcessTaxesClicked(null); // FIXME
        });

        reportsTable.getColumns().addAll(
                employeeCol, passportIdCol, incomeCol, taxCol, profitCol
        );

        reportsTable.setItems(items);

    }

    private void initTaxStrategy() {
        choiceStrategy.getItems().setAll(TaxProcessor.TaxType.values());
        choiceStrategy.getSelectionModel().selectFirst();
    }

    private void initButtons() {
        this.btnProcessTaxes.setOnAction(this::btnProcessTaxesClicked);
        this.btnSaveReports.setOnAction(this::btnSaveReportsClicked);
    }

    private void btnProcessTaxesClicked(ActionEvent event) {
        final TaxCalculator calculator = choiceStrategy.getValue() == TaxProcessor.TaxType.PROFIT_TASK ?
                new TaxOnProfits() : new SingleTax();

        items.setAll(
                TaxProcessor.calculateTax(items, calculator)
        );

        rootController.setStatus("Taxes were recalculated with: " + choiceStrategy.getValue());

    }

    private void btnSaveReportsClicked(ActionEvent event) {
        // TODO
        // FIXME
    }

    @PostConstruct
    private void fetchData() {
        final List<Employee> employees = employeeService.findAll();
        items.addAll(
                employees.stream()
                        .map(TaxReport::new)
                        .collect(Collectors.toList())
        );

        rootController.setStatus("Data was initialized to Taxes Table");
    }

}
