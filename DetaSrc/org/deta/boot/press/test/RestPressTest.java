//package org.deta.boot.press.test;
//
//
//import org.lyg.cache.TimeCheck;
//public class RestPressTest {
//	
//	public static void test() {
//	  int i=0;	
//	}
//	
//	public void test1() {
//		  int i=0;	
//	}
//	
//	public static void main(String[] agrv) {
//		//static
//		TimeCheck T=new TimeCheck();
//		T.begin();
//		for(long i=0;i<900000000;i++) {
//			RestPressTest.test();
//		}
//		T.end();
//		//
//		T.begin();
//		RestPressTest r=new RestPressTest();
//		for(long i=0;i<900000000;i++) {
//			r.test();
//		}
//		T.end();
//		//
//		T.begin();
//		for(long i=0;i<900000000;i++) {
//			new RestPressTest().test();
//		}
//		T.end();
//		//
//		
//		//
//		T.begin();
//		RestPressTest r1=new RestPressTest();
//		for(long i=0;i<900000000;i++) {
//			r1.test1();
//		}
//		T.end();
//		//
//		T.begin();
//		for(long i=0;i<900000000;i++) {
//			new RestPressTest().test1();
//		}
//		T.end();
//	}
//}