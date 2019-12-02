/** 
  *      
  * 
  */
package com.aigodata.common.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * date: 2018年6月18日 下午4:14:25<br/>
 * 
 * @version 1.0
 * @since JDK
 * 
 */
public class ReadWriteUtil {

	public static final String read(InputStream in) throws Exception {
		StringBuffer contentBuffer = new StringBuffer();
		java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(in, "utf-8"));
		String inputLine = null;
		while ((inputLine = reader.readLine()) != null) {
			contentBuffer.append(inputLine);
		}
		reader.close();
		in.close();
		return contentBuffer.toString();
	}

	public static final byte[] readByte(InputStream in) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] bytes = new byte[4096];
		int len = -1;
		while ((len = in.read(bytes)) != -1) {
			os.write(bytes, 0, len);
		}
		in.close();
		return os.toByteArray();
	}

	public static final byte[] readByte(String filePath) throws Exception {
		return readByte(new FileInputStream(filePath));
	}

	public static final String read(File file) throws Exception {
		return read(new FileInputStream(file));
	}

	public static final String read(String fileName) throws Exception {
		if (fileName == null || fileName.trim().equals("")) {
			return "";
		}
		return read(new File(fileName));
	}

	/**
	 * 写文件
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static final void write(String filePath, InputStream in) throws Exception {
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		int len = -1;
		byte[] bytes = new byte[4096];
		while ((len = in.read(bytes)) != -1) {
			fos.write(bytes, 0, bytes.length);
		}
		fos.close();
		in.close();
	}

	public static final void write(String filePath, byte[] bys) throws Exception {
		write(filePath, new ByteArrayInputStream(bys));
	}

	public static final void write(String filePath, String fileContent) throws Exception {
		write(filePath, fileContent.getBytes("UTF-8"));
	}
}
