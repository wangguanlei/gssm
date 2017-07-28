package com.wgl.ssm.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {
		try {
			Class c = Class.forName("com.wgl.ssm.model.User");
//			c.getDeclaredFields()
			Field[] fs = c.getDeclaredFields();
			for(Field f : fs){
				System.out.println(f.getName());
			}
			Method[] ms = c.getMethods();
			for(Method m : ms){
				System.out.println(m.getName());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
