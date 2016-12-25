package com.pujjr.tuple;

/**定义三元组类
 * @author tom
 *
 */
public class ThreeTuple<A,B,C> {
	public final A first;
	public final B second;
	public final C third;
	public ThreeTuple(A a,B b,C c){
		first = a;
		second = b;
		third = c;
	}
}
