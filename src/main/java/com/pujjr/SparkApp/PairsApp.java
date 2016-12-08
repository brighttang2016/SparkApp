package com.pujjr.SparkApp;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

/**
 * @author tom
 *
 */
public class PairsApp {

	/**
	 * tom 2016年12月8日
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String logFile = "/usr/local/spark-2.0.0-bin-hadoop2.7/README.md"; // Should be some file on your system
	    SparkConf conf = new SparkConf().setAppName("Simple Application");
	    JavaSparkContext sc = new JavaSparkContext(conf);
	    JavaRDD<String> logData = sc.textFile(logFile).cache();
	    /*new PairFunction<String, String, String>() {
	    	
		};*/
	}	

}
