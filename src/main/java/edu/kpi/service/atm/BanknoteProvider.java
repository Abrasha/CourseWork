package edu.kpi.service.atm;

import java.util.Map;

public interface BanknoteProvider extends ChainMember<BanknoteProvider> {

    Map<Banknote, Integer> process(int currency, Map<Banknote, Integer> helper);

    enum Banknote {
        _500, _200, _100, _50, _20, _10, _5, _2, _1;


        public static Banknote valueOf(final int amount) {
            switch (amount) {
                case 500:
                    return _500;
                case 200:
                    return _200;
                case 100:
                    return _100;
                case 50:
                    return _50;
                case 20:
                    return _20;
                case 10:
                    return _10;
                case 5:
                    return _5;
                case 2:
                    return _2;
                case 1:
                    return _1;
                default:
                    throw new IllegalArgumentException("No banknote for " + amount + " amount.");
            }
        }


    }

}
