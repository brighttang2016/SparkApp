package com.pujjr.antifraud.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration.PropertiesReader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.pujjr.antifraud.test.PropertyReadTest;

/**
 * @author tom
 *
 */
public class Utils {
	/**
	 * 获取property值
	 * tom 2017年1月3日
	 * @param key
	 * @return
	 */
	public static Object getProperty(String key){
		Object value = "";
		Properties pops = new Properties();
		String path;
		path = PropertyReadTest.class.getClassLoader().getResource("").getPath();
		try {
			pops.load(new FileInputStream(new File(path+"//"+"sys.properties")));
			value = pops.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
