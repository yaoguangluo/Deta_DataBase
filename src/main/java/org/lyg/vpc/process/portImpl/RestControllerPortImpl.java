package org.lyg.vpc.process.portImpl;

import org.json.JSONException;
import org.lyg.cache.CacheManager;
import org.lyg.vpc.process.companyImpl.LoginServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestControllerPortImpl {

	public static Map<String, Object> startResults(int aa, String token, String auth) throws NumberFormatException, JSONException, Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String checkStatus = LoginServiceImpl.checkTokenStatus(token, "common");
		if(checkStatus.contains("invalid")&&(auth.contains("1"))) {
			result.put("loginInfo", "unsuccess");
			result.put("returnResult", checkStatus);
			return result;
		}
		result.put("end", aa);
	//	System.out.println("4444" + result.get("end"));
		// String AA = helloBean.saySomething(aa);
		// result.put("end", AA);
		return result;
		//return Response.status(Status.OK).entity(result).build();
		/*
             Session session = HibernateUtil.getSessionFactory().openSession();
			 session.beginTransaction();
	    	 Map<String, Object> result=new HashMap<String, Object>();

		 	 String hql = "FROM Lolroler as l where l.name= :userName";

		 	 Query query = session.createQuery(hql);
		 	 result.put("end", results);

		 	 query.setString("userName", "Vi");
		 	 List<Lolroler> results = query.list(); 
		 	 session.getTransaction().commit();
			 return Response.status(Status.OK).entity(result).build();
		 */
		/*
            if(null == redisTemplate.opsForValue().get("click")){
				redisTemplate.opsForValue().set("click", "0" , 24000 , TimeUnit.HOURS);
			}else{
				String click = redisTemplate.opsForValue().get("click");
				long click_long = Long.parseLong(click);
				click_long += 1;
				redisTemplate.opsForValue().set("click", String.valueOf(click_long) , 24000 , TimeUnit.HOURS);
			}
			System.out.println(1111);
	    	EventDispatcher dispatcher = new EventDispatcher();
	        dispatcher.registerHandler(UserCreatedEvent.class, new UserCreatedEventHandler());
			System.out.println(2222);
	        User user = new User("iluwatar");
	        dispatcher.dispatch(new UserCreatedEvent(user));

			System.out.println(3333);
	    	 Map<String, Object> result=new HashMap<String, Object>();
	    	 result.put("end", user.getUsername());
	    	 System.out.println("4444"+result.get("end"));
	    	 return Response.status(Status.OK).entity(result).build();
		 */
	}

	public static Map<String, Object> startResultsBb(int bb, String token, String auth) throws NumberFormatException, JSONException, Exception {
		Map<String, Object> output = new HashMap<>();
		String checkStatus = LoginServiceImpl.checkTokenStatus(token, "common");
		if(checkStatus.contains("invalid")&&(auth.contains("1"))) {
			output.put("loginInfo", "unsuccess");
			output.put("returnResult", checkStatus);
			return output;
		}
		try {
			URL url = new URL("http://localhost:3306/aa?aa=1");
			System.out.println("http://localhost:3306/aa?aa=1");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String out = "";
			String out1;
			System.out.println("Output from Server .... \n");
			while ((out1 = br.readLine()) != null) {
				out += out1;
			}
			output.put("result", out);
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static Map<String, Object> getDBCategory(String baseName, String token, String auth) throws Exception {
		Map<String, Object> output = new HashMap<>();
		if(token != null && !token.equalsIgnoreCase("")){
			String checkStatus = LoginServiceImpl.checkTokenStatus(token, "common");
			if(checkStatus.contains("invalid")&&((auth==null?"1":auth).contains("1"))) {
				output.put("loginInfo", "unsuccess");
				output.put("returnResult", checkStatus);
				return output;
			}
		}else{
			output.put("loginInfo", "unsuccess");
			output.put("returnResult", "invalid request");
			return output;
		}	
		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString() + "/" + baseName;
		//锁定表
		Map<String, Object> table = new HashMap<>();
		File fileDBPath = new File(DBPath);
		if (fileDBPath.isDirectory()) {
			String[] files = fileDBPath.list();
			for(String file:files) {
				table.put(file, DBPath + "/" + file);
			}
		}
		Map<String, Map<String, Object>> bases = new HashMap<>();
		bases.put(baseName, table);
		output.put("obj", bases);
		return output;
	}

	public static Map<String, Object> getAllDBCategory(String token, String auth) throws Exception {
		Map<String, Object> output = new HashMap<>();
		if(token != null && !token.equalsIgnoreCase("")){
			String checkStatus = LoginServiceImpl.checkTokenStatus(token, "common");
			if(checkStatus.contains("invalid")&&((auth==null?"1":auth).contains("1"))) {
				output.put("loginInfo", "unsuccess");
				output.put("returnResult", checkStatus);
				return output;
			}
		}else{
			output.put("loginInfo", "unsuccess");
			output.put("returnResult", "invalid request");
			return output;
		}	
		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString();
		Map<String, Object> db = new HashMap<>();
		List<Object> baseList = new ArrayList<>();
		File fileDBPath = new File(DBPath);
		if (fileDBPath.isDirectory()) {
			String[] files = fileDBPath.list();
			for(String file:files) {
				Map<String, Object> base = new HashMap<>();
				String DBBasePath = DBPath + "/" + file;
				File fileDBBasePath = new File(DBBasePath);
				if (fileDBBasePath.isDirectory()) {
					List<Object> tableList = new ArrayList<>();
					String[] filesInfileDBBasePath = fileDBBasePath.list();
					for(String fileInfileDBBasePath: filesInfileDBBasePath) {
						Map<String, Object> table = new HashMap<>();
						String DBTablePath = DBBasePath + "/" + fileInfileDBBasePath;
						table.put("tableName", fileInfileDBBasePath);
						table.put("tablePath", DBTablePath);
						tableList.add(table);
					}	
					base.put("tableList", tableList);
				}	
				base.put("baseName", file);
				base.put("basePath", DBBasePath);
				baseList.add(base);
			}
			db.put("baseList", baseList);
		}
		db.put("dbName", "deta");
		db.put("dbPath", DBPath);
		//锁定表
		return db;
	}
}