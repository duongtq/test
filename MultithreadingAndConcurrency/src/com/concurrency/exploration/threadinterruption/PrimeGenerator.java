package com.concurrency.exploration.threadinterruption;

public class PrimeGenerator extends Thread {
	
	@Override
	public void run() {
		long number = 1L;
		while (true) {
			if (isPrime(number)) {
				System.out.printf("Number %d is a Prime.\n", number);
			}
			
			if (this.isInterrupted()) {
				System.out.println("The Prime Generator has been interrupted.");
				return;
			}
			number++;
		}
	}
	
	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

}
