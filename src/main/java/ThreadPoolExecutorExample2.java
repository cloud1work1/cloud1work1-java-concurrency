package main.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample2 {

	public static void main(String[] args) {
		Integer threadCounter=0;
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(50);
		CustomThreadExecutor2 executor = new CustomThreadExecutor2(10, 20, 5000, TimeUnit.MILLISECONDS, queue);
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println("DemoTask Rejected : " + ((DemoTask) r).getName());
				try {
					Thread.sleep(1000);
				}catch(InterruptedException ie) {
					ie.printStackTrace();
				}
				System.out.println("Lets add another time : " + ((DemoTask) r).getName());
				executor.execute(r);
			}
		});
		executor.prestartAllCoreThreads();
		while(true) {
			threadCounter++;
			executor.execute(new DemoTask2("DEMOTASK2"));
			if(threadCounter==10) {
				break;
			}
		}
		
	}
}

class DemoTask2 implements Runnable {
	private String name;
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public DemoTask2(String name) {
		super();
		this.name = name;
	}



	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println("Executing : " + name);
	}
	
}

class CustomThreadExecutor2 extends ThreadPoolExecutor {
	
	private final Semaphore sempahore;

	public CustomThreadExecutor2(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		sempahore = new Semaphore(corePoolSize+50);
	}
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
	}
	
	@Override
	public void execute(final Runnable task) {
		boolean acquired=false;
		do {
			try {
				sempahore.acquire();
				acquired=true;
			}catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		
		}while(!acquired);
		try {
			super.execute(task);
		}catch(RejectedExecutionException ree) {
			System.out.println("Task Rejected");
			sempahore.release();
			 throw ree;
		}
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		if(t!=null) {
			t.printStackTrace();
		}
		sempahore.release();
	}
	
	
}