/**
 * @author xubf
 * 导入证券基本信息接口类
 */
package com.xubf.qt.quotation.inter.adapter;

import java.util.HashMap;

import com.xubf.qt.algorithm.multifactor.StockInfo;

public interface StockInfoLoader {
	/**
	 * 导入证券基本信息
	 * @param stockInfoMap
	 */
	public void loadBasicInfo( HashMap<String, StockInfo> stockInfoMap );
	
	/**
	 * 导入某一交易日每只证券的市净率信息
	 * @param stockInfoMap
	 */
	public void loadPB( HashMap<String, StockInfo> stockInfoMap, int date );
	
	/**
	 * 导入某一交易日每只证券的市盈率信息
	 * @param stockInfoMap
	 */
	public void loadPE( HashMap<String, StockInfo> stockInfoMap, int date );

	/**
	 * 导入某一交易日每只证券的市现率信息
	 * @param stockInfoMap
	 */
	public void loadPC( HashMap<String, StockInfo> stockInfoMap, int date );
	
	/**
	 * 导入某一交易日每只证券的市收率信息
	 * @param stockInfoMap
	 */
	public void loadPS( HashMap<String, StockInfo> stockInfoMap, int date );	
	
	/**
	 * 计算每个月的ROA变动，本月没有财务指标的用最近一个月的财务指标为准。
	 * @param stockInfoMap
	 * @param date
	 */
	public void loadROAVar( HashMap<String, StockInfo> stockInfoMap, int date );
}
