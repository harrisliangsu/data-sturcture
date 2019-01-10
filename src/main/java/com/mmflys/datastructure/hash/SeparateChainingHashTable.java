package com.mmflys.datastructure.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Shark Chili
 * @Email: sharkchili.su@gmail.com
 * @Date: 2019/1/10 0010
 */
public class SeparateChainingHashTable<AnyType> {

	public static final int DEFAULT_TABLE_SIZE=101;

	private List<AnyType>[] theLists;
	private int currentSize;

	public SeparateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public SeparateChainingHashTable(int size) {
		this.currentSize = size;
		theLists= new LinkedList[nextPrime(size)];
		for (int i = 0; i < theLists.length; i++) {
			theLists[i]=new LinkedList<AnyType>();
		}
	}

	public void insert(AnyType x) {
		List<AnyType> whichList=theLists[myHash(x)];
		if (!whichList.contains(x)){
			whichList.add(x);
			if (++currentSize>theLists.length){
				reHash();
			}
		}
	}

	public void remove(AnyType x) {
		List<AnyType> whichList=theLists[myHash(x)];
		if (whichList.contains(x)){
			whichList.remove(x);
			currentSize--;
		}
	}

	public boolean contains(AnyType x) {
		List<AnyType> whichList=theLists[myHash(x)];
		return whichList.contains(x);
	}

	public void makeEmpty() {
		for (List<AnyType> theList : theLists) {
			theList.clear();
		}
		currentSize=0;
	}

	private void reHash(){

	}

	private int myHash(AnyType x){
		int hashVal=x.hashCode();
		hashVal%=theLists.length;
		if (hashVal<0){
			hashVal+=theLists.length;
		}
		return hashVal;
	}

	private static int nextPrime(int n){
		while (true){
			if (isPrime(++n)){
				return n;
			}
		}
	}

	private static  boolean isPrime(int n){
		int count=0;
		for (int i = 1; i <= n; i++) {
			if (n%i==0) count++;
			if (count==2&&i!=n){
				return false;
			}
		}
		return true;
	}
}
