package com.aabramov;

import com.aabramov.service.atm.BanknoteProvider;
import com.aabramov.service.atm.AutomatedTellerMachine;
import com.aabramov.service.atm.BanknoteAmountProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ATMTests {

    @Test
    public void testChain() {

        final BanknoteProvider provider500 = new BanknoteAmountProvider(500);
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

        System.out.println(
                provider500.process(888, new HashMap<>())
        );

        final Map<BanknoteProvider.Banknote, Integer> answer = new HashMap<>();
        answer.put(BanknoteProvider.Banknote._500, 1);
        answer.put(BanknoteProvider.Banknote._200, 1);
        answer.put(BanknoteProvider.Banknote._100, 1);
        answer.put(BanknoteProvider.Banknote._50, 1);
        answer.put(BanknoteProvider.Banknote._20, 1);
        answer.put(BanknoteProvider.Banknote._10, 1);
        answer.put(BanknoteProvider.Banknote._5, 1);
        answer.put(BanknoteProvider.Banknote._2, 1);
        answer.put(BanknoteProvider.Banknote._1, 1);


        Assert.assertEquals(
                answer, new AutomatedTellerMachine().getCash(888)
        );

    }

    @Test
    public void testATM(){
        final Map<BanknoteProvider.Banknote, Integer> answer = new HashMap<>();
        answer.put(BanknoteProvider.Banknote._500, 1);
        answer.put(BanknoteProvider.Banknote._200, 1);
        answer.put(BanknoteProvider.Banknote._100, 1);
        answer.put(BanknoteProvider.Banknote._50, 1);
        answer.put(BanknoteProvider.Banknote._20, 1);
        answer.put(BanknoteProvider.Banknote._10, 1);
        answer.put(BanknoteProvider.Banknote._5, 1);
        answer.put(BanknoteProvider.Banknote._2, 1);
        answer.put(BanknoteProvider.Banknote._1, 1);


        Assert.assertEquals(
                answer, new AutomatedTellerMachine().getCash(888)
        );
    }

}
