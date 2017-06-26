package com.noteshare.abstractt;

public class Man extends AbstractHuman {

	public Man(String type) {
		super(type);
	}
	
	@Override
	public String toString() {
		return super.toString()+"男人";
	}

	@Override
	public void 具体业务() {
		System.out.println("我是来存钱的");
	}
	
	public void 取号(){
	    System.out.println("男人来取号了");
	}
	
	public static void main(String[] args) {
        Man man = new Man("测试");
        man.取号();
        man.办理银行业务();
        man.评价();
        System.out.println(man.isSupport("测试1"));
    }
}
