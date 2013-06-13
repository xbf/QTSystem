package com.xubf.qt.algorithm.multifactor;

import java.util.HashMap;

import com.xubf.qt.algorithm.multifactor.factor.AbstractFactor;

public class StockInfo {

	public StockInfo() {
		// TODO Auto-generated constructor stub
		factorMap = new HashMap<String, AbstractFactor>();
	}

	/**
	 * 公司代码
	 */
	private int comCode;
	
	/**
	 * 交易所证券代码
	 */
	private String stockCode;
	
	/**
	 * 证券内码
	 */
	private String interCode;
	
	/**
	 * 证券名称
	 */
	private String stockName;
	
	/**
	 * 交易类别
	 * 1：上海 2：深圳
	 */
	private char exchangeType;

	/**
	 * 以散列表形式存储该只证券各因子值
	 */
	private HashMap<String, AbstractFactor> factorMap; 
	
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getInterCode() {
		return interCode;
	}

	public void setInterCode(String interCode) {
		this.interCode = interCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public HashMap<String, AbstractFactor> getFactorMap() {
		return factorMap;
	}

	public void setFactorMap(HashMap<String, AbstractFactor> factorMap) {
		this.factorMap = factorMap;
	}
	
	public char getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(char exchangeType) {
		this.exchangeType = exchangeType;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getComCode() {
		return comCode;
	}

	public void setComCode(int comCode) {
		this.comCode = comCode;
	}

}
