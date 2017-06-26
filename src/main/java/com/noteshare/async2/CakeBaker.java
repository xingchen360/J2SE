package com.noteshare.async2;

public class CakeBaker{
	private Cake cake;

	public CakeBaker(int count, char c) {
		System.out.println("making cake(" + count + ", " + c + ") BEGIN");
		this.cake = new Cake(count,c);
		System.out.println("making cake(" + count + ", " + c + ") END");
	}

	public Cake getCake() {
		return cake;
	}

}
