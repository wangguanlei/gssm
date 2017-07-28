package com.wgl.ssm.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtil {

	private static final Log log = LogFactory.getLog(PropertiesUtil.class);
	private static Properties env = new Properties();

	static {
		try {
			InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			env.load(is);
			is.close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * 取属性值
	 * 
	 * @param key
	 * @return
	 */
	private static String getProperty(String key) {
		return env.getProperty(key);
	}

	/**
	 * 设置属性值
	 * 
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unused")
	private static void setProperty(String key, String value) {
		try {
			File file = new File(PropertiesUtil.class.getClassLoader().getResource(".").getPath() + File.separator
					+ "const.properties");
			FileOutputStream outStream = new FileOutputStream(file);
			env.setProperty(key, value);
			// 写入properties文件
			env.store(outStream, null);
		} catch (Exception ex) {
			log.error(ex);
		}
	}

	public static void main(String[] args) {
		System.out.println(PropertiesUtil.getProperty("jdbc.driver"));
		System.out.println(PropertiesUtil.getProperty("jdbc.url"));
		System.out.println(PropertiesUtil.getProperty("jdbc.user"));
		System.out.println(PropertiesUtil.getProperty("jdbc.password"));
		System.out.println(PropertiesUtil.getProperty("driver"));
		System.out.println(PropertiesUtil.getProperty("url"));
		System.out.println(PropertiesUtil.getProperty("user"));
		System.out.println(PropertiesUtil.getProperty("password"));
//		System.out.println(PropertiesUtil.getProperty("c3p0.pool.size.max"));
		//LTAIGge8psFz9XKF
//		File file = new File("../../test.txt");
//		System.out.println(file.getAbsolutePath());
//		PropertiesLoaderUtils.loadProperties(
	}

}
