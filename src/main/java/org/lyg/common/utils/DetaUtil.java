package org.lyg.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.lyg.cache.Cache;
import org.lyg.cache.CacheManager;

public class DetaUtil {
	public static void initDB(){
		File config = new File("C:/DBconfig.lyg");
		if (config.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(config));
				String tempString;
				while ((tempString = reader.readLine()) != null) {
					Cache c = new Cache();
					c.setValue(tempString.split("->")[1]);
					CacheManager.putCache("DBPath", c);
					File fileDBPath = new File(tempString.split("->")[1]);
					if (fileDBPath.isDirectory()) {
					}else {
						fileDBPath.mkdir();
					}
				}
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}else {
			FileWriter fw = null;
			try {
				fw = new FileWriter("C:/DBconfig.lyg", true);
				fw.write("path->" + "C:/DetaDB");
				fw.close();
				File fileDBPath = new File("C:/DetaDB");
				if (fileDBPath.isDirectory()) {
				}else {
					fileDBPath.mkdir();
				}
				Cache c = new Cache();
				c.setValue("C:/DetaDB");
				CacheManager.putCache("DBPath", c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		DetaDBUtil.initCulumnNameType();
	}
}
