package org.deta.vpcs.hall;

import java.io.File;

import org.lyg.cache.CacheManager;

public class DatabaseLogHall {
	static String logCategoryPath;
	static String logCurrentFilePath;
	public static void createBinLogHall() throws Exception {
		//db write operation
		initLogCategoryPath();
		initCurrentFilePath();
		//write error rollback
		//binlog
	}

	//deta plsql format: time + executeName + plsql string; 
	private static void initCurrentFilePath() {
		 long yearMonthDay = System.currentTimeMillis();
		 long day = yearMonthDay/(1000*60*60*24);
		 logCurrentFilePath = logCategoryPath + "/log/logger" + day + ".det";
	}

	private static void initLogCategoryPath() throws Exception {
		if(null != CacheManager.getCacheInfo("DBPath")) {
			logCategoryPath = CacheManager.getCacheInfo("DBPath").getValue().toString();	
		}else {
			throw new Exception();
		}
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