package org.mapjdbc.util;


/**
 * 
 * Description: Creating a simple example on Singleton Design Pattern. In this we create a class where we make only 1 instance of it through out the application
 * @author Barnali
 *
 **/
public class SingletonDemo {
	
	//2.The instance of the SingletonDemo is required by other class thus it is declared in private static
	private static SingletonDemo instance;

	//1.To prevent anybody else from creating the instance of SingletonDemo, so we have locked the constructor by making it visible inside the class only
	private SingletonDemo(){}
	
	//3.We create a static method which will return a singleton object. Synchronized is used for thread safety purpose
	public static synchronized SingletonDemo getInstance(){
		
		//To check if instance has been created or not . It will create instance only for 1st time.
		if(instance==null){
			
			instance= new SingletonDemo();
		}
		return instance;
		
	}
	
	public void display(){
		System.out.println("Hello, this is a simple example on Sindleton Design Pattern");
	}
	
}
