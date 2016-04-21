package edu.kpi.view;

import edu.kpi.model.TaxReport;
import edu.kpi.service.entities.EmployeeService;
import edu.kpi.service.entities.TaxReportsService;
import edu.kpi.service.tax.TaxCalculator;
import edu.kpi.service.tax.impl.SingleTax;
import edu.kpi.service.tax.impl.TaxOnProfits;
import edu.kpi.service.tax.impl.TaxProcessor;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.mediator.LoggingMediator;
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
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TaxController implements Initializable {

    @Autowired
    private LoggingMediator LOGGER;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaxReportsService reportsService;

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

    @FXML
    private Button btnNewReport;

    @FXML
    private Button btnRemoveReports;

    @FXML
    private ChoiceBox<Month> choiceMonth;

    private ObservableList<TaxReport> items;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initChoiceBoxes();
        initButtons();
    }

    @PostConstruct
    private void fetchData() {
        loadDataWithMonth();
        rootController.setStatus("Data was initialized to Taxes Table");
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
        incomeCol.setOnEditCommit(event -> {
            final TaxReport selected = reportsTable.getSelectionModel().getSelectedItem();

            selected.setIncome(event.getNewValue());
            selected.setTax(0);
            selected.setProfit(0);
            this.recalculateTaxes();
        });

        final TableColumn<TaxReport, Integer> taxCol = new TableColumn<>("Total tax");
        taxCol.setCellValueFactory(new PropertyValueFactory<>("tax"));

        final TableColumn<TaxReport, Integer> profitCol = new TableColumn<>("Profit");
        profitCol.setCellValueFactory(new PropertyValueFactory<>("profit"));


        reportsTable.getColumns().addAll(
                employeeCol, passportIdCol, incomeCol, taxCol, profitCol
        );

        reportsTable.setItems(items);

    }

    private void initChoiceBoxes() {
        choiceStrategy.getItems().setAll(TaxProcessor.TaxType.values());
        choiceStrategy.getSelectionModel().selectFirst();

        choiceStrategy.getSelectionModel().selectedIndexProperty().addListener(e -> {
            this.recalculateTaxes();
        });

        choiceMonth.getItems().setAll(
                Month.values()
        );

        choiceMonth.getItems().setAll(Month.values());
        choiceMonth.getSelectionModel().selectFirst();

        choiceMonth.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.loadDataWithMonth();
        });

    }

    private void initButtons() {
        this.btnProcessTaxes.setOnAction(this::btnProcessTaxesClicked);
        this.btnSaveReports.setOnAction(this::btnSaveReportsClicked);
        this.btnNewReport.setOnAction(this::btnNewClicked);
        this.btnRemoveReports.setOnAction(this::btnRemoveClicked);
    }

    private void loadDataWithMonth() {
        LOGGER.log(Logger.Level.INFO, "Fetching data...");
        final Month month = getSelectedMonth();

        final List<TaxReport> reportsForCurrentMonth = reportsService.getForMonth(month);

        if (reportsForCurrentMonth.size() == 0) { // no taxes found in database
            items.setAll(generateNewReports());
        } else {
            items.setAll(reportsForCurrentMonth);
        }

        LOGGER.log(Logger.Level.INFO, "Table data fetched.");

    }

    private void recalculateTaxes() {
        LOGGER.log(Logger.Level.INFO, "Recalculating taxes.");

        final TaxCalculator calculator = choiceStrategy.getValue() == TaxProcessor.TaxType.PROFIT_TASK ?
                new TaxOnProfits() : new SingleTax();

        items.setAll(
                TaxProcessor.calculateTax(items, calculator)
        );

        rootController.setStatus("Taxes were recalculated with: " + choiceStrategy.getValue());
    }

    private List<TaxReport> generateNewReports() {
        LOGGER.log(Logger.Level.INFO, "Generating new reports...");
        return employeeService.findAll()
                .stream()
                .map(TaxReport::new)
                .collect(Collectors.toList());
    }

    private Month getSelectedMonth() {
        return choiceMonth.getSelectionModel().getSelectedItem();
    }

    private void btnSaveReportsClicked(ActionEvent event) {
        LOGGER.log(Logger.Level.INFO, "Button save reports clicked.");

        final Month month = getSelectedMonth();
        reportsService.removeAllWithinMonth(month);

        final List<TaxReport> reportsToSave = items;
        reportsToSave.forEach(e -> e.setMonth(month));
        reportsService.update(reportsToSave);

        LOGGER.log(Logger.Level.INFO, "Tax reports saved.");
    }

    private void btnNewClicked(ActionEvent e) {
        LOGGER.log(Logger.Level.INFO, "Button generate new reports clicked.");
        items.setAll(generateNewReports());
    }

    private void btnRemoveClicked(ActionEvent e) {
        LOGGER.log(Logger.Level.INFO, "Button remove reports clicked.");
        final Month month = choiceMonth.getSelectionModel().getSelectedItem();
        reportsService.removeAllWithinMonth(month);
        this.items.setAll(generateNewReports());
    }

    private void btnProcessTaxesClicked(ActionEvent event) {
        LOGGER.log(Logger.Level.INFO, "Button process taxes clicked.");
        this.recalculateTaxes();
    }

}