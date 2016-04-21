package edu.kpi.service.atm;

import java.util.Map;

public interface CashProvider {

    Map<BanknoteProvider.Banknote, Integer> getCash(int amount);

}
