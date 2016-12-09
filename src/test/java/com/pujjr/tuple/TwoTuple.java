package com.pujjr.tuple;

/**定义二元组类
 * @author tom
 *
 */
public class TwoTuple<A,B> {
	public final A first;
	public final B second;
	public TwoTuple(A a,B b){
		first = a;
		second = b;
	}
}
