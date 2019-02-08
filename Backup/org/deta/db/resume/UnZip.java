package org.deta.db.resume;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

//refer http://www.blogjava.net/dreamstone/archive/2007/08/09/134986.html
//yaoguangluo fix and normalization.

public class UnZip {
	static final int BUFFER = 1024 * 2;
	@SuppressWarnings("rawtypes")
	public static void unZipWithPath(String zipFullPath, String zipCategory) {
		try {
			String fileName = zipFullPath; //需要解压的文件zip地址
			//String filePath = zipCategory; //解压到地址
			ZipFile zipFile = new ZipFile(fileName);
			Enumeration emu = zipFile.entries();
			while(emu.hasMoreElements()){
				ZipEntry entry = (ZipEntry)emu.nextElement();
				if (entry.isDirectory()){
					//new File(filePath + entry.getName()).mkdirs();
					new File(entry.getName()).mkdirs();
					continue;
				}
				BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(entry));
			//	File file = new File(filePath + entry.getName());
				File file = new File(entry.getName());
				File parent = file.getParentFile();
				if(parent != null && (!parent.exists())){
					parent.mkdirs();
				}
			//	System.out.println(file.getPath());
				if(!file.getPath().contains(".lyg")) {
					file.mkdirs();
				}else {
					FileOutputStream fileOutputStream = new FileOutputStream(file);
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream,BUFFER);           
					int count;
					byte data[] = new byte[BUFFER];
					while ((count = bufferedInputStream.read(data, 0, BUFFER)) != -1){
						bufferedOutputStream.write(data, 0, count);
					}
					bufferedOutputStream.flush();
					bufferedOutputStream.close();
				}
				bufferedInputStream.close();	
			}
			zipFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}