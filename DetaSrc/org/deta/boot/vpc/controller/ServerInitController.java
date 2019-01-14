package org.deta.boot.vpc.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;
import org.deta.boot.vpc.process.TimeProcess;
import org.deta.boot.vpc.sleeper.Sleeper;
import org.deta.boot.vpc.sleeper.SleeperHall;
import org.lyg.cache.DetaDBBufferCacheManager;
import org.lyg.common.utils.DetaUtil;
public class ServerInitController {
	private static ServerSocket server;
	private static Properties properties;
	private static int port;
	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/main/resources/property.proterties")));
			System.out.println("----德塔VPCS数据库服务器资源载入:成功！");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void init() throws IOException {
		try {
			port = Integer.parseInt(properties.getProperty("port"));
			server = new ServerSocket(port);
			System.out.println("----德塔VPCS数据库服务器端口启动:" + port);
			DetaUtil.initDB();
			System.out.println("----德塔VPCS数据库服务器DMA确认:成功！");
			RequestFilterController.initBlockList();
			System.out.println("----德塔VPCS数据库服务器IP过滤服务启动:成功！");
			DetaDBBufferCacheManager.reflection();
			System.out.println("----德塔VPCS数据库服务器启动整库过程映射服务:成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void haoHiYooFaker(SleeperHall sleeperHall) {
		sleeperHall.callSkivvy(); 
	}

	public static void initServer() throws IOException {
		System.out.println("----DETA VPCS--1.7");
		System.out.println("----Author: 罗瑶光");
		System.out.println("----浏阳德塔软件开发有限公司开源项目");
		TimeProcess timeProcess=new TimeProcess();
		timeProcess.begin();
		SleeperHall sleeperHall = new SleeperHall();
		init();
		timeProcess.end();
		System.out.println("----德塔VPCS数据库服务器启动一切正常-总耗时:" + timeProcess.duration()+ "毫秒");
		while(true){
			if(sleeperHall.getThreadsCount() < 1500){
				Sleeper sleeper = new Sleeper();
				try {
					sleeper.hugPillow(sleeperHall, server.accept(), sleeper.hashCode());
					sleeper.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				haoHiYooFaker(sleeperHall);
			}
		}
	}
}