package org.lyg.db.create.imp;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.lyg.cache.CacheManager;
import org.lyg.common.utils.DetaDBUtil;
@SuppressWarnings("unchecked")
public class CreateTablesImp {
	public static void createTable(Map<String, Object> object) throws Exception {
		if(!object.containsKey("baseName")||!object.containsKey("tableName")){
			return;
		}
		//get base
		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString() + "/" + object.get("baseName").toString();
		File DBPathFile = new File(DBPath);
		if(!DBPathFile.isDirectory()) {
			return;
		}
		//make table dir
		String tablePath = DBPath + "/" + object.get("tableName").toString();
		File tablePathFile = new File(tablePath);
		if(tablePathFile.exists()) {
			return;
		}
		//make spec dir
		tablePathFile.mkdirs();
		String tableSpecPath = DBPath + "/spec"; 
		String tableRowsPath = DBPath + "/rows";
		File tableSpecPathFile = new File(tableSpecPath);
		File tableRowsPathFile = new File(tableRowsPath);
		tableSpecPathFile.mkdir();
		tableRowsPathFile.mkdir();
		//make data
		List<String[]> culumnNames = (List<String[]>) object.get("culumnName");
		Iterator<String[]> iterator = culumnNames.iterator();
		while(iterator.hasNext()) {
			String[] culumnDefinition = iterator.next();
			//create name
			String culumnNamePath=tableSpecPath + "/" + culumnDefinition[2];
			File culumnNameFile = new File(culumnNamePath);
			if(DetaDBUtil.withoutCulumnNameType(culumnDefinition[3])) {
				throw new Exception();
			}
			culumnNameFile.mkdir();
			//create file
			FileWriter fw = null;
			fw = new FileWriter(culumnNamePath+"/value.lyg", true);
			fw.write(culumnDefinition[3]);
			fw.close();
		}	
	}
}
