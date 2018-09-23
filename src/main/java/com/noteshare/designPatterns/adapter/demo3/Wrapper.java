package com.noteshare.designPatterns.adapter.demo3;

public abstract class Wrapper implements Port {
	@Override
	public void SSH() {
	};

	@Override
	public void NET() {
	};

	@Override
	public void Tomcat() {
	};

	@Override
	public void Mysql() {
	};

	@Override
	public void Oracle() {
	};

	@Override
	public void FTP() {
	};
}
