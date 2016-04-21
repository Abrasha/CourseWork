package edu.kpi.view;

import edu.kpi.service.atm.BanknoteProvider;
import edu.kpi.service.atm.CashProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ATMController implements Initializable {

    @FXML
    private TextField tfAmount;

    @FXML
    private Button btnCashOut;

    @FXML
    private Label txtStatus;

    @FXML
    private Label txt500s;

    @FXML
    private Label txt200s;

    @FXML
    private Label txt100s;

    @FXML
    private Label txt50s;

    @FXML
    private Label txt20s;

    @FXML
    private Label txt10s;

    @FXML
    private Label txt5s;

    @FXML
    private Label txt2s;

    @FXML
    private Label txt1s;

    @Autowired
    private CashProvider ATM;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCashOut.setOnAction(this::btnCashOutClicked);
    }

    private void btnCashOutClicked(ActionEvent event) {


        try {
            final int amount = Integer.valueOf(tfAmount.getText());

            final Map<BanknoteProvider.Banknote, Integer> result = ATM.getCash(amount);
            populateResult(result);
            txtStatus.setText("Done.");
        } catch (NumberFormatException e) {
            txtStatus.setText("Illegal input. Only Integer is OK.");
            return;
        }


    }

    private void populateResult(Map<BanknoteProvider.Banknote, Integer> data) {
        final Integer numOf500s = data.get(BanknoteProvider.Banknote._500);
        final Integer numOf200s = data.get(BanknoteProvider.Banknote._200);
        final Integer numOf100s = data.get(BanknoteProvider.Banknote._100);
        final Integer numOf50s = data.get(BanknoteProvider.Banknote._50);
        final Integer numOf20s = data.get(BanknoteProvider.Banknote._20);
        final Integer numOf10s = data.get(BanknoteProvider.Banknote._10);
        final Integer numOf5s = data.get(BanknoteProvider.Banknote._5);
        final Integer numOf2s = data.get(BanknoteProvider.Banknote._2);
        final Integer numOf1s = data.get(BanknoteProvider.Banknote._1);

        txt500s.setText(numOf500s == null ? "0" : numOf500s.toString());
        txt200s.setText(numOf200s == null ? "0" : numOf200s.toString());
        txt100s.setText(numOf100s == null ? "0" : numOf100s.toString());
        txt50s.setText(numOf50s == null ? "0" : numOf50s.toString());
        txt20s.setText(numOf20s == null ? "0" : numOf20s.toString());
        txt10s.setText(numOf10s == null ? "0" : numOf10s.toString());
        txt5s.setText(numOf5s == null ? "0" : numOf5s.toString());
        txt2s.setText(numOf2s == null ? "0" : numOf2s.toString());
        txt1s.setText(numOf1s == null ? "0" : numOf1s.toString());
    }

}
