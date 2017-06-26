package com.noteshare.abstractt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractHuman {
	
	private final Set<String> types;
	
	public AbstractHuman(String type){
		System.out.println("AbstractHuman is invoked");
		this.types = new HashSet<String>(Arrays.asList(type));
	}
	
	protected boolean isSupport(String type){
		return types.contains(type);
	}
	
	protected void 办理银行业务(){
		取号();
		具体业务();
		评价();
	}
	
	public void 取号(){
		System.out.println("人来取号");
	}
	public abstract void 具体业务();
	public  void 评价(){
		System.out.println("人来评价");
	}
}
