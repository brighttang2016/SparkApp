package com.pujjr.tuple;


/**定义三元组接口
 * @author tom
 *
 */
public interface IThreeTuple<T,K,V> {
	IThreeTuple<T,K, V> call(T t) throws Exception;
}
