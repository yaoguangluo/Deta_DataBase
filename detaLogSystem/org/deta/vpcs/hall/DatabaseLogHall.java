package org.deta.vpcs.hall;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.lyg.cache.CacheManager;
import org.lyg.common.utils.GzipUtil;
import org.lyg.stable.StableData;

public class DatabaseLogHall {
	static String logCategoryPath;
	static String logCurrentFilePath;
	static String logCurrentFile;
	public static void createBinLogHall() throws Exception {
		//db write operation
		initLogCategoryPath();
		initCurrentFilePath();
		//write error rollback
		//binlog
	}

	private static void initCurrentFilePath() {
		 long yearMonthDay = System.currentTimeMillis();
		 long day = yearMonthDay/(1000 * 60 * 60 * 24);
		 logCurrentFilePath = logCategoryPath + "/log/logger" + day + ".det";
	}

	private static void initLogCategoryPath() throws Exception {
		if(null != CacheManager.getCacheInfo("DBPath")) {
			logCategoryPath = CacheManager.getCacheInfo("DBPath").getValue().toString();	
		}else {
			throw new Exception();
		}
	}
	
	public static void writeLogFile(long when, String who, String plsql) {
		checkCurrentFileRange();
		//zip text string and write; im thinking about a new method to make string write small.
		String logString ="#@" + when + "-" + who + "@:" + plsql;
		FileWriter fw = null;
		try {
			fw = new FileWriter(logCurrentFilePath, true);
			fw.write(new String(GzipUtil.compress(logString.getBytes(StableData.CHARSET_UTF8)), StableData.CHARSET_UTF8));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void checkCurrentFileRange() {
		 long yearMonthDay = System.currentTimeMillis();
		 long day = yearMonthDay/(1000 * 60 * 60 * 24);
		 String willMakeFile = logCategoryPath + "/log/logger" + day + ".det";
		 String willMakeFilePath = logCategoryPath + "/log";
		 File file = new File(willMakeFile);
		 File fileWillMakeFilePath = new File(willMakeFilePath);
		 if(!fileWillMakeFilePath.exists()) {
			 fileWillMakeFilePath.mkdirs();
			 //dont make new for this class, will get memory leakage
		 }
		 if(!file.exists()) {
			 //dont make new for this class, will get memory leakage
			 logCurrentFilePath = willMakeFile;
		 }
		//if currentfiletime - currenttime > 1 day
		//if currentfilesize > 100mb will make discussion later,now just make one file per day.
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
			//read file
			//list file, unzip files, loop time stamp, execplsql
		}
	}
}