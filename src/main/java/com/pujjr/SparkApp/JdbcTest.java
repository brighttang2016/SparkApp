package com.pujjr.SparkApp;

import java.util.List;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.Builder;
import org.apache.derby.iapi.sql.conn.SQLSessionContext;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.functions;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class JdbcTest {
	private static String name = null;
	public static void main(String[] args) {
		long timeStart = System.currentTimeMillis();
		long timeEnd = 0;
		//初始化方法一：
		/*SparkConf conf = new SparkConf();
//		conf.setMaster("local");
		conf.setMaster("spark://192.168.137.16:7077");
		conf.setAppName("SparkSQLJDBC2MySQL");
//		conf.set("spark.sql.warehouse.dir", "/path/to/my/");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        DataFrameReader reader = sqlContext.read().format("jdbc");
        */
       //初始化方法二：
        SparkSession sparkSession = SparkSession.builder()
        	.appName("spark jdbc 测试")
//        	.config("master", "local")
        	.config("master", "spark://192.168.137.16:7077")
        	.config("spark.sql.warehouse.dir", "/path/to/my/")
        	.getOrCreate();
        sparkSession.conf().set("spark.sql.shuffle.partitions", 6);
        sparkSession.conf().set("spark.executor.memory", "512M");
        DataFrameReader reader = sparkSession.read().format("jdbc");
        
        reader.option("url","jdbc:mysql://192.168.137.16:3306/testdb");//数据库路径
        reader.option("dbtable", "t_big_data");
//        reader.option("dbtable", "t_user_test");
        reader.option("driver","com.mysql.jdbc.Driver");
        reader.option("user","root");
        reader.option("password","root");
        
        /*reader.option("url","jdbc:mysql://127.0.0.1:3306/testdb");//数据库路径
        reader.option("dbtable", "t_big_data");
        reader.option("driver","com.mysql.jdbc.Driver");
        reader.option("user","test");
        reader.option("password","test");*/
        
        Dataset<Row> dataSet = reader.load();//这个时候并不真正的执行，lazy级别的。基于dtspark表创建DataFrame
        JavaRDD<Row> javaRdd = dataSet.javaRDD();
        
        JdbcTest.name = "唐tom7778";
        JavaRDD<Row> javaRdd2 = javaRdd.filter(new Function<Row, Boolean>() {
			@Override
			public Boolean call(Row row) throws Exception {
				// TODO Auto-generated method stub
				String userId = row.getAs("userId");
				String userName = row.getAs("name");
				return userId.equals("777") && userName.equals(JdbcTest.name);
//				return row.getAs("userId").equals("9999");
			}
		});
//        System.out.println("javaRdd2.count():"+javaRdd2);
        List<Row> rowList = javaRdd2.take((int) javaRdd2.count());
        for (Row row : rowList) {
			System.out.println("row:"+row.getAs("userId"));
		}
        
        reader.option("dbtable", "t_big_apply");
        Dataset<Row> bigApplyDataSet = reader.load();
        JavaRDD<Row> bigApplyJavaRdd = bigApplyDataSet.javaRDD().filter(new Function<Row, Boolean>() {
			@Override
			public Boolean call(Row row) throws Exception {
				String userId = row.getAs("userId");
				return userId.equals("9999");
			}
		});
        System.out.println("bigApplyJavaRdd.count():"+bigApplyJavaRdd.count());
        timeEnd = System.currentTimeMillis();
        System.out.println("操作耗时："+(timeEnd - timeStart)+"mm");
	}
}
