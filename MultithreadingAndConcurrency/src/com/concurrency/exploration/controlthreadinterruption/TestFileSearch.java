package com.concurrency.exploration.controlthreadinterruption;

import java.util.concurrent.TimeUnit;

public class TestFileSearch {
	public static void main(String[] args) {
		String initPath = "/Users/MaiSon/";
		String fileName = "SearchFile.java";
		
		FileSearch searcher = new FileSearch(initPath, fileName);
		
		Thread task = new Thread(searcher);
		
		task.start();
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		task.interrupt();
	}
}
