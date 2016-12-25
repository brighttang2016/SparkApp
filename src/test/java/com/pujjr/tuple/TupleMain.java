package com.pujjr.tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * java 二元组、三元组测试 2016-12-09
 * @author tom
 *
 */
public class TupleMain {
    public static void main(String[] args) {
    	int userNum = 100;
    	List<UserBean> userList = new ArrayList<UserBean>();
    	for (int i = 0; i < 10; i++) {
			UserBean userBean = new UserBean();
			userBean.setUserId(i+"");
			userBean.setAge(30);
			userBean.setSex("男");
			userBean.setUserName("糖"+i);
			userList.add(userBean);
		}
    	
    	TwoTuple<List<UserBean>, Integer> twoTuple = new TwoTuple<List<UserBean>, Integer>(userList, userNum);
    	System.out.println("二元组 first:"+twoTuple.first);
    	System.out.println("二元组 second:"+twoTuple.second);
    	
    	ThreeTuple<List<UserBean>, Integer, String> threeTuple = new ThreeTuple<List<UserBean>, Integer, String>(userList, userNum, "brighttang");
    	System.out.println("三元组 first："+threeTuple.first);
    	System.out.println("三元组 second："+threeTuple.second);
    	System.out.println("三元组 third："+threeTuple.third);
    	
    	List multiList = new ArrayList();
    	multiList.add(userNum);
    	multiList.add(userList);
    	multiList.add("brighttang");
    	System.out.println("不指定泛型的list效果相当于三元组， multiList："+multiList);
    	
    	System.out.println("生成三元组接口实现类对象");
    	IThreeTuple<String, String, String> twoTupleImpl = new IThreeTuple<String, String, String>(){
			@Override
			public IThreeTuple<String, String, String> call(String t) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
    	};
    }
}
