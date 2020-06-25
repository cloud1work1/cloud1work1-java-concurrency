package main.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample1 {

	public static void main(String[] args) {
		Integer threadCounter=0;
		BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);
		CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(10, 20, 5000, TimeUnit.MILLISECONDS, blockingQueue);
		customThreadPoolExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println("DemoTask Rejected : " + ((DemoTask) r).getName());
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
				System.out.println("Lets add another time : " + ((DemoTask) r).getName());
				executor.execute(r);
			}
		});
		customThreadPoolExecutor.prestartAllCoreThreads();
		while(true) {
			threadCounter++;
			customThreadPoolExecutor.execute(new DemoTask("DEMOTASK RUNNABLE"));
			if(threadCounter==0) {
				break;
			}
		}
	}
}

class DemoTask implements Runnable {

	private String name;
	
	
	public DemoTask(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println("Executing :" +  name);
	}
	
}

class CustomThreadPoolExecutor extends ThreadPoolExecutor {

	public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		if(t != null) {
			t.printStackTrace();
		}
	}
}