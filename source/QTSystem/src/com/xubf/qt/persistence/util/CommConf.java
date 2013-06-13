/**
 * 公共参数表，管理对系统主要公共参数
 * @author xubf
 */
package com.xubf.qt.persistence.util;

public class CommConf {

	/**
	 * 换仓周期
	 * 分为
	 * month 按月
	 */
	
	public final static int HOLD_PERIOD = 12;
	
	/**
	 * 前后数据最大间隔，单位为月。
	 */
	public final static int INTERVAL_THRESHOLD = 3;
	
	/**
	 * 每月计算组合的日期
	 * 
	 */
	public final static int CALCU_DAY = 15;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
