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
		String date = "20130101";
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
		PriceSalseRatio.getInstance().sort();
		
		
		
		
		

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
			List<HashMap<String, Object>> resultList = session
					.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100001");
			Iterator<HashMap<String, Object>> resultIter = resultList.iterator();
			while (resultIter.hasNext()) {
				HashMap<String, Object> result = resultIter.next();

				if (!stockInfoMap.containsKey(result.get("tradingcode"))) {
					StockInfo stockInfo = new StockInfo();
					stockInfo.setStockCode((String)result.get("tradingcode"));
					stockInfo.setComCode((Integer)result.get("comcode"));
					stockInfo.setInterCode(Integer.toString((Integer)result.get("secucode")));
					if ((Integer)result.get("exchangecode") == 101) {
						stockInfo.setExchangeType('1');
					} else if ((Integer)result.get("exchangecode") == 105) {
						stockInfo.setExchangeType('2');
					} else {
						;
					}
					stockInfo.setStockName((String)result.get("secuabbr"));

					stockInfoMap.put((String)result.get("tradingcode"), stockInfo);

				} else {
					StockInfo stockInfo = stockInfoMap.get(result.get("tradingcode"));
					stockInfo.setStockCode((String)result.get("tradingcode"));
					stockInfo.setComCode((Integer)result.get("comcode"));
					stockInfo.setInterCode(Integer.toString((Integer)result.get("secucode")));
					if ((Integer)result.get("exchangecode") == 101) {
						stockInfo.setExchangeType('1');
					} else if ((Integer)result.get("exchangecode") == 105) {
						stockInfo.setExchangeType('2');
					} else {
						;
					}
					stockInfo.setStockName((String)result.get("secuabbr"));
				}
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void loadPB(HashMap<String, StockInfo> stockInfoMap, String date) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.GAZX_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
//			VO100002 vo100002 = new VO100002();
//
//			vo100002.setBeginDate(Integer.toString(date) + "01");
//			vo100002.setEndDate(Integer.toString(date) + "31");
//		
//			HashMap<String, Object> resultSet = session.selectOne(
//					"com.xubf.qt.quotation.inter.gazx.GangAoMapper.100002", vo100002);
//
//			SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");
//
//			String tradingDay = format.format((Date) resultSet.get("TRADINGDAY"));

			HashMap<String, String> inParam = new HashMap<String, String>();
			
			inParam.put("trading_date", date);
			
			List<HashMap<String, Object>> resultList = session.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100003",
					inParam);
			Iterator<HashMap<String, Object>> resultIter = resultList.iterator();
			while (resultIter.hasNext()) {
				HashMap<String, Object> result = resultIter.next();
				if (stockInfoMap.containsKey(result.get("tradingcode"))) {
					StockInfo stockInfo = stockInfoMap.get(result.get("tradingcode"));
					HashMap<String, AbstractFactor> factorMap = stockInfo.getFactorMap();
					if (factorMap.containsKey(PriceBookRatio.FACTOR_NAME)) {
						PriceBookRatio pbRatio = (PriceBookRatio)factorMap.get(PriceBookRatio.FACTOR_NAME);
						pbRatio.setValue((Double)result.get("pb"));
						
					} else {
						PriceBookRatio pbRatio = new PriceBookRatio();
						pbRatio.setValue((Double)result.get("pb"));
						factorMap.put(PriceBookRatio.FACTOR_NAME, pbRatio);	
					}
				}
			}

		} finally {
			session.close();
		}
	}

	@Override
	public void loadPE(HashMap<String, StockInfo> stockInfoMap, String date) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.GAZX_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
//			VO100002 vo100002 = new VO100002();
//
//			vo100002.setBeginDate(Integer.toString(date) + "01");
//			vo100002.setEndDate(Integer.toString(date) + "31");
//			HashMap<String, Object> resultSet = session.selectOne(
//					"com.xubf.qt.quotation.inter.gazx.GangAoMapper.100002", vo100002);
//
//			SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");
//
//			String tradingDay = format.format((Date) resultSet.get("TRADINGDAY"));
			
			HashMap<String, String> inParam = new HashMap<String, String>();
			
			inParam.put("trading_date", date);

			List<HashMap<String, Object>> resultList = session.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100003",
					inParam);
			Iterator<HashMap<String, Object>> resultIter = resultList.iterator();
			while (resultIter.hasNext()) {
				HashMap<String, Object> result = resultIter.next();
				if (stockInfoMap.containsKey(result.get("tradingcode"))) {
					StockInfo stockInfo = stockInfoMap.get(result.get("tradingcode"));
					HashMap<String, AbstractFactor> factorMap = stockInfo.getFactorMap();
					
					if (factorMap.containsKey(PriceEarningRatio.FACTOR_NAME)) {
						PriceEarningRatio peRatio = (PriceEarningRatio)factorMap.get(PriceEarningRatio.FACTOR_NAME);
						peRatio.setValue((Double)result.get("pe"));
						
					} else {
						PriceEarningRatio peRatio = new PriceEarningRatio();
						peRatio.setValue((Double)result.get("pe"));
						factorMap.put(PriceEarningRatio.FACTOR_NAME, peRatio);	
					}
				}
			}

		} finally {
			session.close();
		}
	}

	@Override
	public void loadPC(HashMap<String, StockInfo> stockInfoMap, String date) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.GAZX_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
//			VO100002 vo100002 = new VO100002();
//
//			vo100002.setBeginDate(Integer.toString(date) + "01");
//			vo100002.setEndDate(Integer.toString(date) + "31");
//			HashMap<String, Object> resultSet = session.selectOne(
//					"com.xubf.qt.quotation.inter.gazx.GangAoMapper.100002", vo100002);
//
//			SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");
//
//			String tradingDay = format.format((Date) resultSet.get("TRADINGDAY"));
			
			HashMap<String, String> inParam = new HashMap<String, String>();
			
			inParam.put("trading_date", date);

			List<HashMap<String, Object>> resultList = session.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100003",
					inParam);
			Iterator<HashMap<String, Object>> resultIter = resultList.iterator();
			while (resultIter.hasNext()) {
				HashMap<String, Object> result = resultIter.next();
				if (stockInfoMap.containsKey(result.get("tradingcode"))) {
					StockInfo stockInfo = stockInfoMap.get(result.get("tradingcode"));
					HashMap<String, AbstractFactor> factorMap = stockInfo.getFactorMap();
					if (factorMap.containsKey(PriceCashFlowRatio.FACTOR_NAME)) {
						PriceCashFlowRatio pcRatio = (PriceCashFlowRatio)factorMap.get(PriceCashFlowRatio.FACTOR_NAME);
						pcRatio.setValue((Double)result.get("pc"));
						
					} else {
						PriceCashFlowRatio pcRatio = new PriceCashFlowRatio();
						pcRatio.setValue((Double)result.get("pc"));
						factorMap.put(PriceCashFlowRatio.FACTOR_NAME, pcRatio);	
					}
				}
			}

		} finally {
			session.close();
		}
	}

	@Override
	public void loadPS(HashMap<String, StockInfo> stockInfoMap, String date) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.GAZX_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
//			VO100002 vo100002 = new VO100002();
//
//			vo100002.setBeginDate(Integer.toString(date) + "01");
//			vo100002.setEndDate(Integer.toString(date) + "31");
//			HashMap<String, Object> resultSet = session.selectOne(
//					"com.xubf.qt.quotation.inter.gazx.GangAoMapper.100002", vo100002);
//
//			SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");
//
//			String tradingDay = format.format((Date) resultSet.get("TRADINGDAY"));
			
			HashMap<String, String> inParam = new HashMap<String, String>();
			
			inParam.put("trading_date", date);

			List<HashMap<String, Object>> resultList = session.selectList("com.xubf.qt.quotation.inter.gazx.GangAoMapper.100003",
					inParam);
			Iterator<HashMap<String, Object>> resultIter = resultList.iterator();
			while (resultIter.hasNext()) {
				HashMap<String, Object> result = resultIter.next();
				if (stockInfoMap.containsKey(result.get("tradingcode"))) {
					StockInfo stockInfo = stockInfoMap.get(result.get("tradingcode"));
					HashMap<String, AbstractFactor> factorMap = stockInfo.getFactorMap();
					if (factorMap.containsKey(PriceSalseRatio.FACTOR_NAME)) {
						PriceSalseRatio psRatio = (PriceSalseRatio)factorMap.get(PriceSalseRatio.FACTOR_NAME);
						psRatio.setValue((Double)result.get("pc"));
						
					} else {
						PriceSalseRatio psRatio = new PriceSalseRatio();
						psRatio.setValue((Double)result.get("pc"));
						factorMap.put(PriceSalseRatio.FACTOR_NAME, psRatio);	
					}
				}
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void loadROAVar(HashMap<String, StockInfo> stockInfoMap, String date) {
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
