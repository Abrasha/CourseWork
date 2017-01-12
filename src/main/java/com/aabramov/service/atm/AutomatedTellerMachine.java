package com.aabramov.service.atm;

import java.util.Map;
import java.util.TreeMap;

public class AutomatedTellerMachine implements CashProvider {
    
    private final BanknoteProvider provider500;
    
    public AutomatedTellerMachine() {
        this.provider500 = new BanknoteAmountProvider(500);
        final BanknoteProvider provider200 = new BanknoteAmountProvider(200);
        final BanknoteProvider provider100 = new BanknoteAmountProvider(100);
        final BanknoteProvider provider50 = new BanknoteAmountProvider(50);
        final BanknoteProvider provider20 = new BanknoteAmountProvider(20);
        final BanknoteProvider provider10 = new BanknoteAmountProvider(10);
        final BanknoteProvider provider5 = new BanknoteAmountProvider(5);
        final BanknoteProvider provider2 = new BanknoteAmountProvider(2);
        final BanknoteProvider provider1 = new BanknoteAmountProvider(1);
        
        provider500.setNextMember(provider200);
        provider200.setNextMember(provider100);
        provider100.setNextMember(provider50);
        provider50.setNextMember(provider20);
        provider20.setNextMember(provider10);
        provider10.setNextMember(provider5);
        provider5.setNextMember(provider2);
        provider2.setNextMember(provider1);
        
        
    }
    
    @Override
    public Map<BanknoteProvider.Banknote, Integer> getCash(int amount) {
        return this.provider500.process(amount, new TreeMap<>());
    }
}
