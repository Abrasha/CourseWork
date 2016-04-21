package edu.kpi.service.atm;

public interface ChainMember<T extends ChainMember> {

    void setNextMember(T next);

}
