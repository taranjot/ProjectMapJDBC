package org.mapjdbc.test;

import java.lang.reflect.InvocationTargetException;

import org.mapjdbc.build.QueryBuilder;
import org.mapjdbc.exceptions.ApplicationException;

public class Test {
	
	public static void main(String[] args) {
		MyClassTest myClassTest = new MyClassTest(1, "Taran");
		QueryBuilder<MyClassTest> queryBuilder = new QueryBuilder();
		try {
			System.out.println(queryBuilder.generateInsert(myClassTest));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
