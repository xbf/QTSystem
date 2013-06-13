/**
 * @author xubf
 * 市净率（PB ratio：price/book value ratio），就是账面市值比的倒数。
 * 计算公式如下：
 * 市净率 = 每股股价/每股净资产
 * 市净率较低的股票通常投资价值较高，市净率较高的股票投资价值较低。
 */
package com.xubf.qt.algorithm.multifactor.factor;

import java.util.Collections;
import java.util.Comparator;

import com.xubf.qt.algorithm.multifactor.StockInfo;

public class PriceBookRatio extends AbstractFactor implements Comparator<StockInfo> {

	public final static String FACTOR_NAME = "PriceBookRatio";
	
	private static PriceBookRatio priceBookRatio = null;
	

	public static PriceBookRatio getInstance() {
		if (priceBookRatio == null) {
			priceBookRatio = new PriceBookRatio();
		}
		return priceBookRatio;
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
