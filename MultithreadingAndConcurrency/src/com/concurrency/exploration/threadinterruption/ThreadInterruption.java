package com.concurrency.exploration.threadinterruption;

public class ThreadInterruption {
	public static void main(String args[]) {
		Thread task = new PrimeGenerator();
		
		task.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		task.interrupt();
		
		System.out.printf("Main: Status of thread: %s\n", task.getState());
		System.out.printf("Main: isInterrupted: %s\n", task.isInterrupted());
		System.out.printf("Main: isAlive: %s\n", task.isAlive());
	}
}
