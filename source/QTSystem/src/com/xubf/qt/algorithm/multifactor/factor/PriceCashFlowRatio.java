/**
 * @author xubf
 * 市现率（PriceCashFlowRatio）,就是现金收益率的倒数
 * 计算公式如下：
 * 市现率 = 股票价格 / 每股现金流量
 * 
 */
package com.xubf.qt.algorithm.multifactor.factor;

import java.util.Collections;
import java.util.Comparator;

import com.xubf.qt.algorithm.multifactor.StockInfo;

public class PriceCashFlowRatio extends AbstractFactor implements Comparator<StockInfo> {
	public final static String FACTOR_NAME = "PriceCashFlowRatio";
	
	private static PriceCashFlowRatio priceCashFlowRatio = null;
	
	public static PriceCashFlowRatio getInstance() {
		
		if (priceCashFlowRatio == null) {
			priceCashFlowRatio = new PriceCashFlowRatio();
		}
		return priceCashFlowRatio;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compare(StockInfo o1, StockInfo o2) {
		// TODO Auto-generated method stub
		if (o1.getFactorMap().get(FACTOR_NAME) == null) {
			return -1;
		} else if (o2.getFactorMap().get(FACTOR_NAME) == null) {
			return 1;
		} else if (o1.getFactorMap().get(FACTOR_NAME).getValue() > o2.getFactorMap().get(FACTOR_NAME).getValue()) {
			return 1;
		} else if (o1.getFactorMap().get(FACTOR_NAME).getValue() < o2.getFactorMap().get(FACTOR_NAME).getValue()) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		Collections.sort(this.getStkInfoArray(), this);
	}

}
