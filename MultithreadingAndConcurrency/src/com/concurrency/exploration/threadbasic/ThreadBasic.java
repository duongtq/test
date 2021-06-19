package com.concurrency.exploration.threadbasic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class ThreadBasic {
	public static void main(String[] args) {
		System.out.printf("Minimum Priority: %d\n", Thread.MIN_PRIORITY);
		System.out.printf("Normal Priority: %d\n", Thread.NORM_PRIORITY);
		System.out.printf("Maximum Priority: %d\n", Thread.MAX_PRIORITY);
		
		Thread[] threads = new Thread[10];
		Thread.State[] statuses = new Thread.State[10];
		
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator());
			if (i % 2 == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread " + i);
		}
		
		try (FileWriter fileWriter = new FileWriter("src/log.txt");) {
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for (int i = 0; i < 10; i++) {
				printWriter.printf("Main: Status of Thread %d: %s\n", i, threads[i].getState());
				statuses[i] = threads[i].getState();
			}
			
			for (int i = 0; i < 10; i++) {
				threads[i].start();
			}
			
			boolean finish = false;
			
			while (!finish) {
				for (int i = 0; i < 10; i++) {
					if (threads[i].getState() != statuses[i]) {
						writeThreadInfo(printWriter, threads[i], statuses[i]);
						statuses[i] = threads[i].getState();
					}
				}
				
				finish = true;
				
				for (int i = 0; i < 10; i++) {
					finish = finish && (threads[i].getState() == State.TERMINATED);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void writeThreadInfo(PrintWriter printWriter, Thread thread, Thread.State state) {
		printWriter.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		printWriter.printf("Main : Priority: %d\n", thread.getPriority());
		printWriter.printf("Main : Old State: %s\n", state);
		printWriter.printf("Main : New State: %s\n", thread.getState());
		printWriter.printf("Main : ------------------------------------\n");
	}
}
