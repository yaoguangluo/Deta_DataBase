package org.deta.boot.vpc.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;
import java.util.Random;
import org.deta.boot.vpc.process.TimeProcess;
import org.deta.boot.vpc.sleeper.Sleeper;
import org.deta.boot.vpc.sleeper.SleeperHall;
import org.lyg.common.utils.DetaUtil;
public class ServerInitController {
	private static ServerSocket server;
	private static Properties properties;
	private static int port;
	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/main/resources/property.proterties")));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void init() {
		try {
			port = Integer.parseInt(properties.getProperty("port"));
			server = new ServerSocket(port);
			DetaUtil.initDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
	}

	public static void initServer() {
		System.out.println("----DETA VPCS--1.7");
		System.out.println("----Author: Yaoguang.Luo");
		System.out.println("----浏阳德塔软件开发有限公司开源项目");
		TimeProcess timeProcess=new TimeProcess();
		timeProcess.begin();
		SleeperHall sleeperHall = new SleeperHall();
		init();
		timeProcess.end();
		System.out.println("----德塔VPCS数据库服务器启动正常-耗时:" + timeProcess.duration()+ "毫秒");
		while(true){
			if(sleeperHall.getThreadsCount() < 1500){
				Sleeper sleeper = new Sleeper();
				System.out.println(sleeper.hashCode());
				try {
					sleeper.hugPillow(sleeperHall, server.accept(), sleeper.hashCode());
					sleeper.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}