package main.java;

import java.lang.Thread.UncaughtExceptionHandler;

public class UncaughtExceptionHandlerExample1  {

	public static void main(String[] args) {
		Task task = new Task();
		Thread thread = new Thread(task, "Task Thread");
		thread.start();
	}

}

class UnCaughtExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Uncaught exception message:");
		System.out.printf("Thread name: %s\n", t.getName());
		System.out.printf("Exception class: %s, message: %s\n", e.getClass().getName(), e.getMessage());
		e.printStackTrace(System.out);
		new Thread(new Task()).start();
	}
	
}

class Task implements Runnable {

	@Override
	public void run() {
		Thread.currentThread().setUncaughtExceptionHandler(new UnCaughtExceptionHandler());
		System.out.println(Integer.parseInt("123"));
	      System.out.println(Integer.parseInt("234"));
	      System.out.println(Integer.parseInt("345"));
	      System.out.println(Integer.parseInt("XYZ")); //This will cause NumberFormatException
	      System.out.println(Integer.parseInt("456"));
		
	}
	
}