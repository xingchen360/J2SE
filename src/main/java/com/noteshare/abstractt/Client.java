package com.noteshare.abstractt;

import java.util.ArrayList;
import java.util.List;

public class Client {
	
	private static List<AbstractHuman> list;
	
	static{
		Man man = new Man("M");
		Woman woman = new Woman("W");
		list = new ArrayList<AbstractHuman>();
		list.add(man);
		list.add(woman);
		//.......
	}
	
	public static AbstractHuman get(String type){
		for(int i = 0;i<list.size();i++){
			AbstractHuman human = list.get(i);
			if(human.isSupport(type)){
				return human;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		AbstractHuman human = get("M");
		human.办理银行业务();
	}
}
