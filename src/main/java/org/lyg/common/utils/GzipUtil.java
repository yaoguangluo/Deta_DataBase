package org.lyg.common.utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
public class GzipUtil {
	// 压缩
	public static byte[] compress(byte[] data) throws IOException {
		if (data == null || data.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(data);
		gzip.finish();
		gzip.close();
		byte[] ret = out.toByteArray();
		out.close();
		return  ret;//out.toString("ISO-8859-1");
	}

	public static byte[] compress(String str, String stringTypes) throws IOException {
		if (str == null || str.length() == 0) {
			return null;
		}
		return compress(str.getBytes(stringTypes));
	}

	public static byte[] uncompress(byte[] data) throws IOException {
		if (data == null || data.length == 0) {
			return data;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		gunzip.close();
		in.close();
		return out.toByteArray();
	}
}
//--------------------- 
//作者：树先生i ,罗瑶光进行了修正。
//来源：CSDN 
//原文：https://blog.csdn.net/qq_18895659/article/details/72190322 
//版权声明：本文为博主原创文章，转载请附上博文链接！