package edu.kpi.service.atm;

/**
 * Created by Abrasha on 20-Apr-16.
 */
public interface ChainMember<T extends ChainMember> {

    void setNextMember(T next);

}
