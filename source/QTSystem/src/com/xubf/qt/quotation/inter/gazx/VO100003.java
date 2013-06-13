package com.xubf.qt.quotation.inter.gazx;

import java.util.Date;
/**
 * 
 * @author xubf
 *
 */
public class VO100003 {

	private int secuCode;
	private String tradingCode;
	private Date tradingDay;
	private char tradingstatus;
	private double pe; //市盈率
	private double pb; //市净率
	private double pc; //市现率
	private double ps; //市收率
	
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

	public String getTradingCode() {
		return tradingCode;
	}

	public void setTradingCode(String tradingCode) {
		this.tradingCode = tradingCode;
	}

	public Date getTradingDay() {
		return tradingDay;
	}

	public void setTradingDay(Date tradingDay) {
		this.tradingDay = tradingDay;
	}

	public char getTradingstatus() {
		return tradingstatus;
	}

	public void setTradingstatus(char tradingstatus) {
		this.tradingstatus = tradingstatus;
	}

	public double getPe() {
		return pe;
	}

	public void setPe(double pe) {
		this.pe = pe;
	}

	public double getPb() {
		return pb;
	}

	public void setPb(double pb) {
		this.pb = pb;
	}

	public double getPc() {
		return pc;
	}

	public void setPc(double pc) {
		this.pc = pc;
	}

	public double getPs() {
		return ps;
	}

	public void setPs(double ps) {
		this.ps = ps;
	}

	
	
}
