package org.deta.vpcs.hall;

import java.io.File;

public class DatabaseLogHall {
	static String logCategoryPath;
	static String logCurrentFilePath;
	public static void createBinLogHall() {
		//db write operation
		initLogCategoryPath();
		initCurrentFilePath();
		//write error rollback
		//binlog
	}

	private static void initCurrentFilePath() {
		
	}

	private static void initLogCategoryPath() {
		
	}
	
	public static void writeLogFile(String plsql) {
		checkCurrentFileRange();
		//zip text string and write; im thinking about a new method to make string write small.
	}

	private static void checkCurrentFileRange() {
		//if currentfiletime - currenttime > 1 day
		//if currentfilesize > 100mb
		//makenew file;
	}
	
	@SuppressWarnings("unused")
	private static void coverageByTime(String time) {
		//loop unzip loggers,
		//find point of time,and start read and exec the plsql from the loggers.
		File file = new File(logCategoryPath);
		if(file.isDirectory()) {
			File[] files=file.listFiles();
			//...
		}
	}
}