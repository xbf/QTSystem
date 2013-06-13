package com.xubf.qt.quotation.inter.gazx;

public class VO100001 {
	private int secuCode; //证券编码
	private int comCode; //公司编码
	private String tradingCode;  //交易代码
	private String secuAbbr; //证券简称
	private int exchangeCode; //交易市场代码
	private String exchangeName; //交易市场

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getSecuCode() {
		return secuCode;
	}

	public void setSecuCode(int secuCode) {
		this.secuCode = secuCode;
	}

	public int getComCode() {
		return comCode;
	}

	public void setComCode(int comCode) {
		this.comCode = comCode;
	}

	public String getTradingCode() {
		return tradingCode;
	}

	public void setTradingCode(String tradingCode) {
		this.tradingCode = tradingCode;
	}

	public String getSecuAbbr() {
		return secuAbbr;
	}

	public void setSecuAbbr(String secuAbbr) {
		this.secuAbbr = secuAbbr;
	}

	public int getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(int exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

}
