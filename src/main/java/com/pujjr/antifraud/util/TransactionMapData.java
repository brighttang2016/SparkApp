package com.pujjr.antifraud.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * @author tom
 *
 */
public class TransactionMapData implements Cloneable{
	public Map<String,Object> map = null;
	private static TransactionMapData tmd = null;
	public void put(String key,Object value){
		this.map.put(key, value);
	}
	public Object get(String key){
		return this.map.get(key);
	}
	
	private TransactionMapData(){
		
	}
	public Object clone() throws CloneNotSupportedException {  
		return super.clone();
    }  
	
	public static synchronized TransactionMapData getInstance(){
		if(TransactionMapData.tmd == null){
			TransactionMapData.tmd = new TransactionMapData();
			tmd.map = new HashMap<String,Object>();
			SparkConf conf = new SparkConf();
			conf.setMaster("local");
//			conf.setMaster("spark://192.168.137.16:7077");
			conf.setAppName("PujjrAntiFraud");
			conf.set("spark.sql.warehouse.dir", "/path/to/my/");
	        JavaSparkContext sc = new JavaSparkContext(conf);
	        TransactionMapData.tmd.put("sc", sc);
			return TransactionMapData.tmd;
		}else{
			return TransactionMapData.tmd;
		}	
	}
}
