package com.xubf.qt.persistence.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnUtil {
	public final static String GAZX_CONF = "gazx_conf";
	public final static String QTSYSTEM_CONF = "qtsystem_conf";
	
	private static SqlSessionFactory gazxSessionFactory;
	private static SqlSessionFactory qtsystemSessionFactory;

	private ConnUtil() {
		;
	}
	
	public static SqlSessionFactory getInstance(String environmentId) {
		String resource = "mybatis-config.xml";
		if (environmentId.equals(GAZX_CONF)) {
			if (gazxSessionFactory == null) {
				InputStream inputStream = null;
				try {
					inputStream = Resources.getResourceAsStream(resource);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gazxSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,environmentId);
			}
			
			return gazxSessionFactory;
			
		} else if (environmentId.equals(QTSYSTEM_CONF)) {
			if (qtsystemSessionFactory == null) {
				InputStream inputStream = null;
				try {
					inputStream = Resources.getResourceAsStream(resource);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				qtsystemSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,environmentId);
			}
			
			return qtsystemSessionFactory;
		}

		return null;
	}
}
