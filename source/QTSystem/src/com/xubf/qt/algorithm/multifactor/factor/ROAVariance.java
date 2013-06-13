/**
 * @author xubf
 * ROA变动
 * 此项指标的计算数据源很可能来自“公司业绩快报表”（COM_PERFORMANCEEXPRESS）中。
 * 变动的计算方法：
 * 个人理解，应该将上期的ROA指标与本期的ROA指标进行比较，别动情况仍然是一个百分比，按照每只股票的变动情况进行排序，
 * 其他变动应该也是这种计算方式。
 * 公式：ROA变动 = (本期ORA - 上期ROA) / 上期ROA
 * 
 */
package com.xubf.qt.algorithm.multifactor.factor;

import java.util.Collections;
import java.util.Comparator;
import com.xubf.qt.algorithm.multifactor.StockInfo;

public class ROAVariance extends AbstractFactor implements Comparator<StockInfo> {

	public final static String FACTOR_NAME = "ROAVariance";

	private static ROAVariance roaVariance = null;

	public static ROAVariance getInstance() {
		if (roaVariance == null) {
			roaVariance = new ROAVariance();
		}

		return roaVariance;
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
	
	public void calculate() {
		
	}

}
