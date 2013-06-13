package com.xubf.qt.quotation.inter.gazx;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.xubf.qt.persistence.util.ConnUtil;

public class MySqlTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSessionFactory sessionFactory = ConnUtil.getInstance(ConnUtil.QTSYSTEM_CONF);
		SqlSession session = sessionFactory.openSession();
		try {
			HashMap<String, Integer> hashMap = session.selectOne(
					"com.xubf.qt.quotation.inter.gazx.SecuQuotationMapper.selectQuot", "20030428600004");
			System.out.println("is empty?" + hashMap.isEmpty());
			System.out.println("name is " + hashMap.get("init_date"));
		} finally {
			session.close();
		}
	}

}
