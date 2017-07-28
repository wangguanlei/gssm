package com.wgl.ssm.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConstPropertiesUtil {

	private static final Log log = LogFactory.getLog(ConstPropertiesUtil.class);
	private static Properties env = new Properties();
	private static Properties realEnv = new Properties();

	static {
		try {
			InputStream is = ConstPropertiesUtil.class.getClassLoader().getResourceAsStream("const.properties");
			env.load(is);
			is.close();
			InputStream realIs = ConstPropertiesUtil.class.getClassLoader().getResourceAsStream(getProperty("static_const"));
			realEnv.load(realIs);
			realIs.close();
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
	 * 取属性值
	 * 
	 * @param key
	 * @return
	 */
	public static String getStaticConst(String key) {
		return realEnv.getProperty(key);
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
			File file = new File(ConstPropertiesUtil.class.getClassLoader().getResource(".").getPath() + File.separator
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
		System.out.println(ConstPropertiesUtil.getProperty("static_const"));
		System.out.println(getStaticConst("oss_accesskeyid"));
		//LTAIGge8psFz9XKF
//		File file = new File("../../test.txt");
//		System.out.println(file.getAbsolutePath());
//		PropertiesLoaderUtils.loadProperties(
	}

}
