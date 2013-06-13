package com.xubf.qt.algorithm.multifactor.factor;

import java.util.ArrayList;

import com.xubf.qt.algorithm.multifactor.StockInfo;


public abstract class AbstractFactor {
	
	/**
	 * 权重，标明每个因子在计算过程中的权重
	 */
	private double weight;
	
	/**
	 * 每只股票在每个因子下的得分
	 */
	private int score; 
	
	/**
	 * 每只股票的该因子值
	 */
	private double value;
	
	/**
	 * 排好序的证券信息类
	 */
	private ArrayList<StockInfo> stkInfoArray;

	public ArrayList<StockInfo> getStkInfoArray() {
		return stkInfoArray;
	}

	public void setStkInfoArray(ArrayList<StockInfo> stkInfoArray) {
		this.stkInfoArray = stkInfoArray;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
		
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	
	public abstract void sort();
	
	
}
