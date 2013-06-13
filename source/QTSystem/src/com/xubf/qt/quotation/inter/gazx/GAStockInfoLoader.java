package com.xubf.qt.quotation.inter.gazx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.xubf.qt.algorithm.multifactor.StockInfo;
import com.xubf.qt.algorithm.multifactor.factor.AbstractFactor;
import com.xubf.qt.algorithm.multifactor.factor.PriceCashFlowRatio;
import com.xubf.qt.algorithm.multifactor.factor.PriceEarningRatio;
import com.xubf.qt.algorithm.multifactor.factor.PriceBookRatio;
import com.xubf.qt.algorithm.multifactor.factor.PriceSalseRatio;
import com.xubf.qt.persistence.util.ConnUtil;
import com.xubf.qt.quotation.inter.adapter.StockInfoLoader;

public class GAStockInfoLoader implements StockInfoLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GAStockInfoLoader loader = new GAStockInfoLoader();
		HashMap<String, StockInfo> stockInfoMap = new HashMap<String, StockInfo>();
		loader.loadBasicInfo(stockInfoMap);
		int date = 201301;
		loader.loadPB(stockInfoMap, date);
		loader.loadPE(stockInfoMap, date);
		loader.loadPC(stockInfoMap, date);
		loader.loadPS(stockInfoMap, date);
		System.out.println(stockInfoMap.size());
		Collection<StockInfo> stkCol = stockInfoMap.values();

		ArrayList<StockInfo> pbList = new ArrayList<StockInfo>(stkCol); // 按PB排序的集合
//		Collections.sort(pbList, PriceBookRatio.getInstance());
		PriceBookRatio.getInstance().setStkInfoArray(pbList);
		PriceBookRatio.getInstance().sort();

		ArrayList<StockInfo> peList = new ArrayList<StockInfo>(stkCol); // 按PE排序的集合
//		Collections.sort(peList, PriceEarningRatio.getInstance());
		PriceEarningRatio.getInstance().setStkInfoArray(peList);
		PriceEarningRatio.getInstance().sort();
		
		ArrayList<StockInfo> pcList = new ArrayList<StockInfo>(stkCol); // 按PC排序的集合
//		Collections.sort(pcList, PriceCashFlowRatio.getInstance());
		PriceCashFlowRatio.getInstance().setStkInfoArray(pcList);
		PriceCashFlowRatio.getInstance().sort();
		
		ArrayList<StockInfo> psList = new ArrayList<StockInfo>(stkCol); // 按PS排序的集合
//		Collections.sort(psList, PriceSalseRatio.getInstance());
		PriceSalseRatio.getInstance().setStkInfoArray(psList);
		PriceCashFlowRatio.getInstance().sort();
		
		
		
		
		

		// File file = new File("D:\\output.txt");
		// FileWriter writer = null;
		// try {
		// writer = new FileWriter(file);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Iterator<StockInfo> stkIter = PBList.iterator();
		// while (stkIter.hasNext()) {
		// StockInfo stkInfo = stkIter.next();
		// try {
		// writer.write(stkInfo.getInterCode() + "|" + stkInfo.getStockCode() +
		// "|" + stkInfo.getStockName() + "|" +
		// stkInfo.getFactorMap().toString());
		// writer.write(13);
		// writer.write(10);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// try {
		// writer.flush();
		// writer.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// File file1 = new File("D:\\output1.txt");
		// FileWriter writer1 = null;
		// try {
		// writer1 = new FileWriter(file1);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Iterator<StockInfo> stkIter1 = PEList.iterator();
		// while (stkIter1.hasNext()) {
		// StockInfo stkInfo = stkIter1.next();
		// try {
		// writer1.write(stkInfo.getInterCode() + "|" + stkInfo.getStockCode() +
		// "|" + stkInfo.getStockName() + "|" +
		// stkInfo.getFactorMap().toString());
		// writer1.write(13);
		// writer1.write(10);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// try {
		// writer1.flush();
		// writer1.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	@Override
	public void loadBasicInfo(HashMap<String, StockInfo> stockInfoMap) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.GAZX_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
			List<VO100001> stkBasicInfoList = session
					.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100001");
			Iterator<VO100001> stkInfoIter = stkBasicInfoList.iterator();
			while (stkInfoIter.hasNext()) {
				VO100001 vo100001 = stkInfoIter.next();

				if (!stockInfoMap.containsKey(vo100001.getTradingCode())) {
					StockInfo stockInfo = new StockInfo();
					stockInfo.setStockCode(vo100001.getTradingCode());
					stockInfo.setComCode(vo100001.getComCode());
					stockInfo.setInterCode(Integer.toString(vo100001.getSecuCode()));
					if (vo100001.getExchangeCode() == 101) {
						stockInfo.setExchangeType('1');
					} else if (vo100001.getExchangeCode() == 105) {
						stockInfo.setExchangeType('2');
					} else {
						;
					}
					stockInfo.setStockName(vo100001.getSecuAbbr());

					stockInfoMap.put(vo100001.getTradingCode(), stockInfo);

				} else {
					StockInfo stockInfo = stockInfoMap.get(vo100001.getTradingCode());
					stockInfo.setStockCode(vo100001.getTradingCode());
					stockInfo.setComCode(vo100001.getComCode());
					stockInfo.setInterCode(Integer.toString(vo100001.getSecuCode()));
					if (vo100001.getExchangeCode() == 101) {
						stockInfo.setExchangeType('1');
					} else if (vo100001.getExchangeCode() == 105) {
						stockInfo.setExchangeType('2');
					} else {
						;
					}
					stockInfo.setStockName(vo100001.getSecuAbbr());
				}
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void loadPB(HashMap<String, StockInfo> stockInfoMap, int date) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.GAZX_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
			VO100002 vo100002 = new VO100002();

			vo100002.setBeginDate(Integer.toString(date) + "01");
			vo100002.setEndDate(Integer.toString(date) + "31");
			HashMap<String, Object> resultSet = session.selectOne(
					"com.xubf.qt.quotation.inter.gazx.GangAoMapper.100002", vo100002);

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");

			String tradingDay = format.format((Date) resultSet.get("TRADINGDAY"));

			List<VO100003> vo100003List = session.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100003",
					tradingDay);
			Iterator<VO100003> vo100003Iter = vo100003List.iterator();
			while (vo100003Iter.hasNext()) {
				VO100003 vo100003 = vo100003Iter.next();
				if (stockInfoMap.containsKey(vo100003.getTradingCode())) {
					StockInfo stockInfo = stockInfoMap.get(vo100003.getTradingCode());
					HashMap<String, AbstractFactor> factorMap = stockInfo.getFactorMap();
					if (factorMap.containsKey(PriceBookRatio.FACTOR_NAME)) {
						PriceBookRatio pbRatio = (PriceBookRatio)factorMap.get(PriceBookRatio.FACTOR_NAME);
						pbRatio.setValue(vo100003.getPb());
						
					} else {
						PriceBookRatio pbRatio = new PriceBookRatio();
						pbRatio.setValue(vo100003.getPb());
						factorMap.put(PriceBookRatio.FACTOR_NAME, pbRatio);	
					}
				}
			}

		} finally {
			session.close();
		}
	}

	@Override
	public void loadPE(HashMap<String, StockInfo> stockInfoMap, int date) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.GAZX_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
			VO100002 vo100002 = new VO100002();

			vo100002.setBeginDate(Integer.toString(date) + "01");
			vo100002.setEndDate(Integer.toString(date) + "31");
			HashMap<String, Object> resultSet = session.selectOne(
					"com.xubf.qt.quotation.inter.gazx.GangAoMapper.100002", vo100002);

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");

			String tradingDay = format.format((Date) resultSet.get("TRADINGDAY"));

			List<VO100003> vo100003List = session.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100003",
					tradingDay);
			Iterator<VO100003> vo100003Iter = vo100003List.iterator();
			while (vo100003Iter.hasNext()) {
				VO100003 vo100003 = vo100003Iter.next();
				if (stockInfoMap.containsKey(vo100003.getTradingCode())) {
					StockInfo stockInfo = stockInfoMap.get(vo100003.getTradingCode());
					HashMap<String, AbstractFactor> factorMap = stockInfo.getFactorMap();
					
					if (factorMap.containsKey(PriceEarningRatio.FACTOR_NAME)) {
						PriceEarningRatio peRatio = (PriceEarningRatio)factorMap.get(PriceEarningRatio.FACTOR_NAME);
						peRatio.setValue(vo100003.getPe());
						
					} else {
						PriceEarningRatio peRatio = new PriceEarningRatio();
						peRatio.setValue(vo100003.getPe());
						factorMap.put(PriceEarningRatio.FACTOR_NAME, peRatio);	
					}
				}
			}

		} finally {
			session.close();
		}
	}

	@Override
	public void loadPC(HashMap<String, StockInfo> stockInfoMap, int date) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.GAZX_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
			VO100002 vo100002 = new VO100002();

			vo100002.setBeginDate(Integer.toString(date) + "01");
			vo100002.setEndDate(Integer.toString(date) + "31");
			HashMap<String, Object> resultSet = session.selectOne(
					"com.xubf.qt.quotation.inter.gazx.GangAoMapper.100002", vo100002);

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");

			String tradingDay = format.format((Date) resultSet.get("TRADINGDAY"));

			List<VO100003> vo100003List = session.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100003",
					tradingDay);
			Iterator<VO100003> vo100003Iter = vo100003List.iterator();
			while (vo100003Iter.hasNext()) {
				VO100003 vo100003 = vo100003Iter.next();
				if (stockInfoMap.containsKey(vo100003.getTradingCode())) {
					StockInfo stockInfo = stockInfoMap.get(vo100003.getTradingCode());
					HashMap<String, AbstractFactor> factorMap = stockInfo.getFactorMap();
					if (factorMap.containsKey(PriceCashFlowRatio.FACTOR_NAME)) {
						PriceCashFlowRatio pcRatio = (PriceCashFlowRatio)factorMap.get(PriceCashFlowRatio.FACTOR_NAME);
						pcRatio.setValue(vo100003.getPc());
						
					} else {
						PriceCashFlowRatio pcRatio = new PriceCashFlowRatio();
						pcRatio.setValue(vo100003.getPc());
						factorMap.put(PriceCashFlowRatio.FACTOR_NAME, pcRatio);	
					}
				}
			}

		} finally {
			session.close();
		}
	}

	@Override
	public void loadPS(HashMap<String, StockInfo> stockInfoMap, int date) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.GAZX_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
			VO100002 vo100002 = new VO100002();

			vo100002.setBeginDate(Integer.toString(date) + "01");
			vo100002.setEndDate(Integer.toString(date) + "31");
			HashMap<String, Object> resultSet = session.selectOne(
					"com.xubf.qt.quotation.inter.gazx.GangAoMapper.100002", vo100002);

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");

			String tradingDay = format.format((Date) resultSet.get("TRADINGDAY"));

			List<VO100003> vo100003List = session.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100003",
					tradingDay);
			Iterator<VO100003> vo100003Iter = vo100003List.iterator();
			while (vo100003Iter.hasNext()) {
				VO100003 vo100003 = vo100003Iter.next();
				if (stockInfoMap.containsKey(vo100003.getTradingCode())) {
					StockInfo stockInfo = stockInfoMap.get(vo100003.getTradingCode());
					HashMap<String, AbstractFactor> factorMap = stockInfo.getFactorMap();
					if (factorMap.containsKey(PriceSalseRatio.FACTOR_NAME)) {
						PriceSalseRatio psRatio = (PriceSalseRatio)factorMap.get(PriceSalseRatio.FACTOR_NAME);
						psRatio.setValue(vo100003.getPc());
						
					} else {
						PriceSalseRatio psRatio = new PriceSalseRatio();
						psRatio.setValue(vo100003.getPc());
						factorMap.put(PriceSalseRatio.FACTOR_NAME, psRatio);	
					}
				}
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void loadROAVar(HashMap<String, StockInfo> stockInfoMap, int date) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.GAZX_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
//			HashMap<String, String> inParam = new HashMap<String, String>();
//			inParam.put("comCode", "389837");
//			List<HashMap<String, Object>> vo100004List = session.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100004", inParam);
			
			Collection<StockInfo> stkCol = stockInfoMap.values();
			Iterator<StockInfo> stkIter = stkCol.iterator();
			
			while (stkIter.hasNext()) {
				StockInfo stkInfo = stkIter.next();
				String stkCode = stkInfo.getStockCode();
				int comCode = stkInfo.getComCode();
				
				HashMap<String, String> inParam = new HashMap<String, String>();
				inParam.put("calcu_date", "20130501");
				
				List<HashMap<String, Object>> vo100005List = session.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100005", inParam);
				
			}
			

//			Iterator<VO100003> vo100003Iter = vo100003List.iterator();
//			while (vo100003Iter.hasNext()) {
//				VO100003 vo100003 = vo100003Iter.next();
//				if (stockInfoMap.containsKey(vo100003.getTradingCode())) {
//					StockInfo stockInfo = stockInfoMap.get(vo100003.getTradingCode());
//					HashMap<String, AbstractFactor> factorMap = stockInfo.getFactorMap();
//					if (factorMap.containsKey(PriceSalseRatio.FACTOR_NAME)) {
//						PriceSalseRatio psRatio = (PriceSalseRatio)factorMap.get(PriceSalseRatio.FACTOR_NAME);
//						psRatio.setValue(vo100003.getPc());
//						
//					} else {
//						PriceSalseRatio psRatio = new PriceSalseRatio();
//						psRatio.setValue(vo100003.getPc());
//						factorMap.put(PriceSalseRatio.FACTOR_NAME, psRatio);	
//					}
//				}
//			}
		} finally {
			session.close();
		}		
	}
}
