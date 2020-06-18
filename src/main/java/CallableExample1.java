package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableExample1 {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<Integer>> factorailList = new ArrayList<>();
		Random random = new Random();
		for(int i=0; i<4; i++) {
			int number = random.nextInt(10);
			FactorialNumber fnumobj = new FactorialNumber(number);
			
			factorailList.add(executor.submit(fnumobj));
		}
		for (Future<Integer> future : factorailList) {
			try {
				System.out.println("Future result is: "+ future.get() + " Tsk completed ? => "+future.isDone());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
}

class FactorialNumber implements Callable<Integer> {

	private Integer number;
	
	public FactorialNumber(int number) {
		this.number=number;
	}
	@Override
	public Integer call() throws Exception {
		System.out.println("Submission for factorial of number : "+ number);
		if(number==0 || number==1) {
			return 1;
		}
		int factorial =1;
		for(int i=2; i<=number; i++) {
			factorial=factorial*i;
			TimeUnit.MILLISECONDS.sleep(20);
		}
		return factorial;
	}
	
}