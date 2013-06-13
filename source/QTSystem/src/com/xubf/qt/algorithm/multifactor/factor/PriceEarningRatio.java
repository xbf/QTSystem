/**
 * @author xubf
 * 市盈率（PE ratio：price earning ratio），盈利收益率的倒数
 * 是指在一个考察期内，股票价格与每股收益的比率，计算公式如下：
 * PE ratio = Price per Share/Earnings per Share
 */
package com.xubf.qt.algorithm.multifactor.factor;

import java.util.Collections;
import java.util.Comparator;

import com.xubf.qt.algorithm.multifactor.StockInfo;

public class PriceEarningRatio extends AbstractFactor implements Comparator<StockInfo> {

	public final static String FACTOR_NAME = "PriceEarningRatio";
	
	private static PriceEarningRatio priceEarningRatio = null;
	
	public static PriceEarningRatio getInstance() {
		if (priceEarningRatio == null) {
			priceEarningRatio = new PriceEarningRatio();
		}
		
		return priceEarningRatio;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		Collections.sort(this.getStkInfoArray(), this);
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
}
