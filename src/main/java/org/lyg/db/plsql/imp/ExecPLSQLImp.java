package org.lyg.db.plsql.imp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class ExecPLSQLImp {
	public static Map<String, Object> ExecPLSQL(String plsql, boolean mod) throws Exception{
		//working for here
		Map<String, Object> output = new ConcurrentHashMap<>();
		//1make container
		output.put("start", "0");
		output.put("countJoins", "0");
		//2make line
		String[] commands = plsql.replace(" ", "").replace("\n", "").split(";");
		for(String command:commands) {
			String[] acknowledge = command.split(":");
			if(acknowledge[0].equals("setRoot")) {
				PLSQLCommandImp.proceseSetRoot(acknowledge, output);
			}
			if(acknowledge[0].equals("baseName")) {
				PLSQLCommandImp.processBaseName(acknowledge, output);
			}
			if(acknowledge[0].equals("tableName")) {
				PLSQLCommandImp.processTableName(acknowledge, output);
			}
			if(acknowledge[0].equals("culumnName")) {
				PLSQLCommandImp.processListNeedStart(acknowledge, output);
			}
			if(acknowledge[0].equals("changeCulumnName")) {
				PLSQLCommandImp.processListNeedStart(acknowledge, output);
			}
			if(acknowledge[0].equals("culumnValue")) {
				PLSQLCommandImp.processListNeedStart(acknowledge, output);
			}
			if(acknowledge[0].equals("join")) {
				PLSQLCommandImp.processJoin(acknowledge, output);
			}
			if(acknowledge[0].equals("condition")) {
				PLSQLCommandImp.processListNeedStart(acknowledge, output);
			}
			if(acknowledge[0].equals("relation")) {
				PLSQLCommandImp.processListNeedStart(acknowledge, output);
			}
			if(acknowledge[0].equals("aggregation")) {
				PLSQLCommandImp.processListNeedStart(acknowledge, output);
			}
			if(acknowledge[0].equals("getCulumns")) {
				PLSQLCommandImp.processListNeedStart(acknowledge, output);
			}
			output.put("newCommand", acknowledge[0]);
			PLSQLCommandImp.processExec(acknowledge, output, mod);
			output.put("lastCommand", output.get("newCommand"));
		}
		PLSQLCommandImp.processCheck(output.get("newCommand").toString(), output, mod);
		return output;
	}
}