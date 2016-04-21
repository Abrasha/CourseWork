package edu.kpi.service.atm;

import java.util.Map;
import java.util.Objects;

public class BanknoteAmountProvider implements BanknoteProvider {

    private final int forAmount;
    private BanknoteProvider nextMember;

    public BanknoteAmountProvider(int forAmount) {
        this.forAmount = forAmount;
    }

    @Override
    public Map<Banknote, Integer> process(int currency, Map<Banknote, Integer> helper) {

        if (currency >= forAmount) {
            final int numOfBanknotes = currency / forAmount;
            final int reminder = currency % forAmount;

            helper.put(Banknote.valueOf(forAmount), numOfBanknotes);

            if (reminder != 0 && !Objects.isNull(nextMember)) {
                return nextMember.process(reminder, helper);
            } else {
                return helper;
            }

        } else {
            if (!Objects.isNull(nextMember))
                return nextMember.process(currency, helper);
        }

        return helper;

    }

    @Override
    public void setNextMember(BanknoteProvider next) {
        Objects.requireNonNull(next);
        this.nextMember = next;
    }
}
