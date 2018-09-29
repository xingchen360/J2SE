package com.noteshare.designPatterns.interpreter.demo2;

import java.util.HashMap;

/**
 *	环境-准备计算环境，准备相关数据
 */
public class Context {

	private HashMap<String, Double> variableAndValue;

	public Context(HashMap<String, Double> variableAndValue) {
		this.variableAndValue = variableAndValue;
	}

	public HashMap<String, Double> getVariableAndValue() {
		return variableAndValue;
	}

	public void setVariableAndValue(HashMap<String, Double> variableAndValue) {
		this.variableAndValue = variableAndValue;
	}

	public double getValue(String key) {
		return variableAndValue.get(key);
	}
}
