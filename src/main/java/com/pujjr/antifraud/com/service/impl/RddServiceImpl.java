package com.pujjr.antifraud.com.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.Builder;
import org.apache.spark.storage.StorageLevel;

import com.pujjr.SparkApp.JdbcTest;
import com.pujjr.antifraud.com.service.IRddService;
import com.pujjr.antifraud.util.TransactionMapData;

import scala.Serializable;


/**
 * @author tom
 *
 */
public class RddServiceImpl implements IRddService,Serializable {
	private static String name = null;
	private static final Logger logger = Logger.getLogger(RddServiceImpl.class);
	@Override
	public String selectCurr(String appId) {
		logger.info("Rdd服务");
		JavaSparkContext sc = (JavaSparkContext) TransactionMapData.getInstance().get("sc");
        SQLContext sqlContext = new SQLContext(sc);
        DataFrameReader reader = sqlContext.read().format("jdbc");

        reader.option("url","jdbc:mysql://192.168.137.16:3306/testdb");//数据库路径
        reader.option("driver","com.mysql.jdbc.Driver");
        reader.option("user","root");
        reader.option("password","root");
        
        //t_big_data
        reader.option("dbtable", "t_user_test");
        Dataset<Row> dataSet = reader.load();//这个时候并不真正的执行，lazy级别的。基于dtspark表创建DataFrame
        JavaRDD<Row> javaRdd = dataSet.javaRDD();
        javaRdd.persist(StorageLevel.MEMORY_ONLY());
//        logger.debug("javaRdd.count():"+javaRdd.count());
        System.out.println("javaRdd.count():"+javaRdd.count());
        
        JavaRDD<Row> javaRdd2 = javaRdd.filter(new Function<Row, Boolean>() {
			@Override
			public Boolean call(Row row) throws Exception {
//				logger.debug("row:"+row);
//				return row.getAs("userId").equals("777") && row.getAs("name").equals(JdbcTest.name);
				return row.getAs("userid").equals("1");
			}
		});
        System.out.println("javaRdd2.count():"+javaRdd2.count());
//        logger.debug("RDD处理结束");
    	System.out.println("RDD处理结束");
    	
    	
    	/*
    	//模拟中间业务与第三方交易
    	int i = 0;
    	while(i < 6){
    		try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println("业务逻辑处理"+Thread.currentThread().getId());
    		i++;
    	}
    	*/
    	
    	//中间业务逻辑处理完成，返回客户端
    	System.out.println("ttttttttttttttt");
//    	this.sendToClient(ctx);
//    	new Test().doSomething(ctx);
//    	new Thread(new SendThread(this,ctx)).start();
    	System.out.println("执行完成");
		return null;
	}

	@Override
	public String selectHis(String appId) {

		return null;
	}

}
