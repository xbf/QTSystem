/**
 * @author xubf
 * 
 * 市收率 P/Salse
 */

package com.xubf.qt.algorithm.multifactor.factor;

import java.util.Collections;
import java.util.Comparator;

import com.xubf.qt.algorithm.multifactor.StockInfo;

public class PriceSalseRatio extends AbstractFactor implements Comparator<StockInfo> {

	public final static String FACTOR_NAME = "PriceSalseRatio";

	private static PriceSalseRatio priceSalseRatio = null;

	public static PriceSalseRatio getInstance() {
		if (priceSalseRatio == null) {
			priceSalseRatio = new PriceSalseRatio();
		}
		return priceSalseRatio;
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
