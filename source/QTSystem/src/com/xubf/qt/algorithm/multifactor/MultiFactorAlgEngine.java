package com.xubf.qt.algorithm.multifactor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import com.xubf.qt.algorithm.multifactor.factor.PriceEarningRatio;
import com.xubf.qt.quotation.inter.adapter.StockInfoLoader;
import com.xubf.qt.quotation.inter.gazx.GAStockInfoLoader;

public class MultiFactorAlgEngine {
	
	/**
	 * 投资组合结构，每个月一个组合，供12个月的组合数据
	 * key值为年月，如201201，201202，201203，201204，201205....201212
	 * value值为每个月的组合信息
	 */

	private HashMap<String, HashMap<String, StockInfo>> invesGroup;
	private HashMap<String, StockInfo> stockInfoMap;

	public MultiFactorAlgEngine() {
		// TODO Auto-generated constructor stub
		stockInfoMap = new HashMap<String, StockInfo>();
		//invesGroup = new HashMap<String, HashMap<String,StockInfo>>();
//		StockInfoLoader infoLoader = new GAStockInfoLoader();
//		for (int i = 0; i < 12; i++) {
//			stockMap = new HashMap<String, StockInfo>();
//			infoLoader.loadBasicInfo(stockMap);
//			
//			invesGroup.put(Integer.toString(i+1), stockMap);
//		}
	}
	
	
	/**
	 * 日期进行组合提取
	 * @param date 年月信息，格式 YYYYMM
	 */
	public void loadByMonth(int date) {
		//SimpleDateFormat formate = new SimpleDateFormat("YYYYMM");
		StockInfoLoader infoLoader = new GAStockInfoLoader();
		infoLoader.loadBasicInfo(stockInfoMap);
		infoLoader.loadPB(stockInfoMap, date);
		infoLoader.loadPE(stockInfoMap, date);
		Collection<StockInfo> stkInfoCol = stockInfoMap.values();
		ArrayList<StockInfo> stkInfoList = new ArrayList<StockInfo>(stkInfoCol);
		
		Collections.sort(stkInfoList, new PriceEarningRatio());
	}
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
