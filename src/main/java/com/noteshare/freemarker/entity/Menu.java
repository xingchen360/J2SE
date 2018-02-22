package com.noteshare.freemarker.entity;

import java.util.List;

public class Menu {
	private String cdxh;
	private String fcdxh;
	private List<Menu> subMenu;
	
	public String getCdxh() {
		return cdxh;
	}
	public Menu(String cdxh, String fcdxh, List<Menu> subMenu) {
		this.cdxh = cdxh;
		this.fcdxh = fcdxh;
		this.subMenu = subMenu;
	}
	public void setCdxh(String cdxh) {
		this.cdxh = cdxh;
	}
	public String getFcdxh() {
		return fcdxh;
	}
	public void setFcdxh(String fcdxh) {
		this.fcdxh = fcdxh;
	}
	public List<Menu> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}
}
