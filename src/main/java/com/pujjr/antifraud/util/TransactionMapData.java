package com.pujjr.antifraud.util;

import java.util.HashMap;
import java.util.Map;

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
	
	public static TransactionMapData getInstance(){
		if(TransactionMapData.tmd == null){
			TransactionMapData.tmd = new TransactionMapData();
			tmd.map = new HashMap<String,Object>();
			return TransactionMapData.tmd;
		}else{
			return TransactionMapData.tmd;
		}	
	}
}
