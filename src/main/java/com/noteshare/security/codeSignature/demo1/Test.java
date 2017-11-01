package com.noteshare.security.codeSignature.demo1;

public class Test {
	public static void main(String[] args) {
		TextFileDisplayer tfd = new TextFileDisplayer("E:\\gitWork\\J2SE\\src\\main\\java\\com\\noteshare\\security\\codeSignature\\demo1\\question.txt");
		Friend friend = new Friend(tfd, true);
		Stranger stranger = new Stranger(friend, true);
		stranger.doYourThing();
	}
}
