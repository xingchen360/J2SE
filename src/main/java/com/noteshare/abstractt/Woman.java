package com.noteshare.abstractt;

public class Woman extends AbstractHuman {

    public Woman(String type) {
        super(type);
    }

    @Override
    public String toString() {
        return super.toString() + "女人";
    }

    @Override
    public void 具体业务() {
        System.out.println("我是来取钱的");
    }
}
