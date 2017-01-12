package com.aabramov.service.atm;

public interface ChainMember<T extends ChainMember> {

    void setNextMember(T next);

}
