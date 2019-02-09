package org.deta.vpcs.hall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.deta.db.resume.UnZip;
import org.lyg.cache.CacheManager;
import org.lyg.common.utils.GzipUtil;
import org.lyg.db.plsql.imp.ExecPLSQLImp;
import org.lyg.stable.StableData;

@SuppressWarnings("unused")
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
		String logString ="#@:" + when + "@:" + who + "@:" + plsql;
		FileWriter fw = null;
		try {
			fw = new FileWriter(logCurrentFilePath, true);
			fw.write("\n\r\n");
			fw.write(new String(GzipUtil.compress(logString.getBytes(StableData.CHARSET_UTF8)), StableData.CHARSET_UTF8));
			//	fw.write(logString);
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

	private static void coverageByTime(long time) throws Exception {
		//	1删除已损坏的数据库 已完成
		File needClear = new File("C:/DetaDB");
		needClear.delete();
		//	2解压备份数据库 已完成
		UnZip.unZipWithPath("C:/DetaLog/zipCover/zip_1549583065203.zip", "C:/DetaLog/zipCover/cover");
		//	3循环执行备份plsql命令，直到等于大于时间戳完成返回。	
		BufferedReader reader = new BufferedReader(new FileReader("C:/DetaLog/log/logger.det"));
		String tempString;
		while ((tempString = reader.readLine()) != null) {
			//解gzip压缩并执行数据库恢复
			tempString = new String(GzipUtil.uncompress(tempString.getBytes(StableData.CHARSET_UTF8)), StableData.CHARSET_UTF8);
			long currentTime =Long.valueOf(tempString.split("@:")[1]);
			if(currentTime < time) {//逐条恢复到点。
				ExecPLSQLImp.ExecPLSQL(tempString.split("@:")[3], true);
			}
		}
		reader.close();
	}
}