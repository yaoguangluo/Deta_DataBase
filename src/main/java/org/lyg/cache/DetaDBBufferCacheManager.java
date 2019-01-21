package org.lyg.cache;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.lyg.db.reflection.Base;
import org.lyg.db.reflection.Cell;
import org.lyg.db.reflection.DB;
import org.lyg.db.reflection.Row;
import org.lyg.db.reflection.Spec;
import org.lyg.db.reflection.Table;
@SuppressWarnings("unused")
public class DetaDBBufferCacheManager {
	public static DB db = new DB();
	public static boolean dbCache = false;
	private DetaDBBufferCacheManager() {
		super();
	}
	
	public static void reflection() throws IOException {
		ConcurrentHashMap<String, Base> bases = new ConcurrentHashMap<>();
		db.setBases(bases);
		//1»ñÈ¡dbÂ·¾¶£»
		String dBPath = CacheManager.getCacheInfo("DBPath").getValue().toString();
		File fileDBPath = new File(dBPath);
		if (fileDBPath.isDirectory()) {
			String[] baseNames = fileDBPath.list();
			for(int i = 0; i < baseNames.length; i++) {
				loopBases(db, dBPath, baseNames[i]);
			}
		}
		dbCache = true;
	}
	
	private static void loopBases(DB db, String dBPath, String baseName) throws IOException {
		Base base = new Base();
		ConcurrentHashMap<String, Table> tables = new ConcurrentHashMap<>();
		base.setTables(tables);
		String dBasePath = dBPath + "/" + baseName;
		//get base
		File fileDBasePath = new File(dBasePath);
		if (fileDBasePath.isDirectory()) {
			ConcurrentHashMap<String, Object> tableMap = new ConcurrentHashMap<>();
			//get tables
			String[] tableNames = fileDBasePath.list();
			for(int i = 0; i < tableNames.length; i++) {
				loopTables(base, dBasePath, tableNames[i]);
			}
		}
		db.putBase(baseName, base);
	}
	
	private static void loopTables(Base base, String dBasePath, String tableName) throws IOException {
		Table table = new Table();
		String tablePath = dBasePath + "/" + tableName;
		File fileTablePath = new File(tablePath);
		if (fileTablePath.isDirectory()) {
			String specPath = tablePath + "/spec";
			String rowsPath = tablePath + "/rows";
			loopSpec(table,specPath);
			loopRows(table,rowsPath);
		}
		base.putTable(tableName, table);
	}
	
	private static void loopSpec(Table table, String specPath) throws IOException {
		Spec spec = new Spec();
		ConcurrentHashMap<String, String> culumnTypes = new ConcurrentHashMap<>();
		spec.setCulumnTypes(culumnTypes);
		File fileSpecPath = new File(specPath);
		if (fileSpecPath.isDirectory()) {
			String[] specs = fileSpecPath.list();
			for(int i = 0; i < specs.length; i++) {
				String specCulumnPath = specPath + "/" + specs[i];
				String specCulumnValuePath = specCulumnPath + "/value.lyg";
				//if get
				BufferedReader reader = new BufferedReader(new FileReader(specCulumnPath + "/" + "value.lyg"));  
				String temp = "";
				String tempString = "";
				while ((tempString = reader.readLine()) != null) {
					temp += tempString;
				}
				reader.close();
				spec.setCulumnType(specs[i], temp);
			}
		}
		table.setSpec(spec);
	}

	private static void loopRows(Table table, String rowsPath) throws IOException {
		ConcurrentHashMap<String, Row> rows = new ConcurrentHashMap<>();
		table.setRows(rows);
		File fileRowsPath = new File(rowsPath);
		if (fileRowsPath.isDirectory()) {
			String[] rowIndex = fileRowsPath.list();
			for(int i = 0; i < rowIndex.length; i++) {
				loopRow(table, fileRowsPath, rowIndex[i]);	
			}
		}
	}

	private static void loopRow(Table table, File fileRowsPath, String rowIndex) throws IOException {
		Row row = new Row();
		ConcurrentHashMap<String, Cell> cells = new ConcurrentHashMap<>();
		row.setCells(cells);
		String rowIndexPath = fileRowsPath + "/" + rowIndex;
		File fileRowPath = new File(rowIndexPath);
		if (fileRowPath.isDirectory()) {
			String[] culumns = fileRowPath.list();
			for(int i = 0; i < culumns.length; i++) {
				String rowCulumnPath = rowIndexPath + "/" + culumns[i];
				String rowCulumnValuePath = rowCulumnPath + "/value.lyg";
				//if get
				if(!culumns[i].contains("is_delete")) {
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new FileReader(rowCulumnValuePath)); 
					}catch(Exception e){
						e.printStackTrace();
					}
					String temp = "";
					String tempString = "";
					while ((tempString = reader.readLine()) != null) {
						temp += tempString;
					}
					reader.close();
					Cell cell = new Cell();
					cell.setCellValue(temp);
					row.putCell(culumns[i], cell);
				}else {
					Cell cell = new Cell();
					cell.setCellValue("");
					row.putCell(culumns[i], cell);
				}
			}
		}
		table.putRow(rowIndex, row);;
	}
} 