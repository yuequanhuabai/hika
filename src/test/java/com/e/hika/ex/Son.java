package com.e.hika.ex;

public class Son extends Father {

    @Override
    public Integer getMoney() {
        return super.earnMoney();
    }


    public Integer get() {
        return super.getMoney();
    }


}
