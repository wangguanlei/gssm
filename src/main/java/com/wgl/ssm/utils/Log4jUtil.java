package com.wgl.ssm.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Log4jUtil {

	public static final String LOG4J_PROPERTIES = "log4j.properties";

	public static final String LOG_FOLDER = "log"; // 日志目录，系统启动时自动创建

	public static final String ERROR_LOG = "error.log";

	private static Log log;

	private static ThreadLocal<Object> threadFlag = new ThreadLocal<Object>();

	static {
		init();
	}

	/**
	 * 初始化
	 */
	private static void init() {
		try {
			createLogFolder();
			redirectSystemErrInfo();
			info("Log4jUtil initialized!");
		} catch (Throwable t) {
			warn("System error info redirect failed!");
		}
	}

	/*
	 * 创建日志文件目录
	 */
	private static void createLogFolder() {
		File logFolder = new File(LOG_FOLDER);
		boolean isLogFolderCreated = logFolder.mkdir();
		if (isLogFolderCreated)
			info(LOG_FOLDER + " folder created! " + logFolder.getAbsolutePath());
	}

	/*
	 * 将系统错误信息重定向到文件 这里文件主要记录的是运行时非预期的异常。
	 */
	private static void redirectSystemErrInfo() throws Throwable {
		Properties properties = new Properties();
		properties.load(Log4jUtil.class.getClassLoader().getResourceAsStream(LOG4J_PROPERTIES));
		String errLogFileName = properties.getProperty(ERROR_LOG);
		PrintStream oldErrPS = System.err;
		PrintStream errPS = createNewErrPS(errLogFileName, oldErrPS);
		System.setErr(errPS);
		info("Redirect SystemErrInfo successfully!");
	}

	private static PrintStream createNewErrPS(String errLogFileName, final PrintStream oldErrPS) throws Throwable {
		return new PrintStream(new FileOutputStream(errLogFileName)) {
			@Override
			public void println(String x) {
				super.println(x);
				oldErrPS.println(x);
			}

			@Override
			public void println(Object x) {
				super.println(x);
				oldErrPS.println(x);
			}
		};
	}

	/*
	 * 取得日志实例
	 * 
	 * @return
	 */
	private synchronized static Log getLog() {
		if (log == null)
			log = LogFactory.getLog(Log4jUtil.class);
		return log;
	}

	/**
	 * 严重错误（1/6） 非常严重的错误，导致系统中止。
	 * 
	 * @param msg
	 */
	public static void fatal(Object msg) {
		getLog().fatal(constructMsg(msg));
	}

	/**
	 * 非预期的运行时错误（2/6） 其它运行期错误或不是预期的条件。
	 * 
	 * @param msg
	 */
	public static void error(Object msg) {
		getLog().error(constructMsg(msg));
	}

	/**
	 * 警告（3/6） 使用了不赞成使用的API、非常拙劣使用API, '几乎就是'错误, 其它运行时不合需要和不合预期的状态但还没必要称为 "错误"。
	 * 
	 * @param msg
	 */
	public static void warn(Object msg) {
		getLog().warn(constructMsg(msg));
	}

	/**
	 * 一般信息（4/6） 运行时产生的有意义的事件。
	 * 
	 * @param msg
	 */
	public static void info(Object msg) {
		getLog().info(constructMsg(msg));
	}

	/**
	 * 调试信息（5/6） 系统流程中的细节信息，一般正常运行时不被打印。
	 * 
	 * @param msg
	 */
	public static void debug(Object msg) {
		getLog().debug(constructMsg(msg));
	}

	/**
	 * 更细节的调试信息（6/6） 比debug更加细节的信息，一般正常运行时不被打印。
	 * 
	 * @param msg
	 */
	public static void trace(Object msg) {
		getLog().trace(constructMsg(msg));
	}

	/**
	 * 一般捕获的异常信息 所有捕获到的异常务必调用此方法处理，无需 e.printStackTrace() 。
	 * 
	 * @param e
	 */
	public static void exception(Exception e) {
		exception(e.getMessage(), e);
	}

	public static void exception(Object msg, Exception e) {
		getLog().info(constructExceptionMsg(msg), e);
	}

	/*
	 * 构造异常日志信息
	 */
	private static String constructExceptionMsg(Object msg) {
		return constructMsg("Exception:" + String.valueOf(msg));
	}

	/*
	 * 构造完整的日志信息
	 */
	private static String constructMsg(Object msg) {
		StringBuffer cMsg = new StringBuffer();
		// 1.DEBUG 模式下的附加信息
		constructMsgForDebug(cMsg);
		// 2.添置FLAG标记下的附加信息
		constructMsgForFlag(cMsg);
		// 3.主体信息
		cMsg.append(msg);
		return cMsg.toString();
	}

	@SuppressWarnings("unchecked")
	private static void constructMsgForDebug(StringBuffer cMsg) {
		if (getLog().isDebugEnabled()) {
			try {
				Class locationInfoCls = Class.forName("org.apache.log4j.spi.LocationInfo");
				Object locationInfo = locationInfoCls.getConstructor(Throwable.class, String.class)
						.newInstance(new Throwable(), Log4jUtil.class.getName());
				cMsg.append("[");
				cMsg.append(locationInfoCls.getMethod("getClassName", (Class[]) null).invoke(locationInfo,
						(Object[]) null));
				cMsg.append(":");
				cMsg.append(locationInfoCls.getMethod("getLineNumber", (Class[]) null).invoke(locationInfo,
						(Object[]) null));
				cMsg.append("] ");
			} catch (Exception e) {
				warn("Could not log with line number and class name, maybe org.apache.log4j.spi.LocationInfo not found!");
				exception(e);
			}
		}
	}

	private static void constructMsgForFlag(StringBuffer cMsg) {
		Object flag = threadFlag.get();
		if (flag != null) {
			cMsg.append("( ");
			cMsg.append(flag);
			cMsg.append(" ) ");
		}
	}

	/**
	 * 释放当前线程日志标记
	 */
	public static void releaseFlag() {
		threadFlag.remove();
	}

	/**
	 * 给当前线程日志创建一个标记
	 * 
	 * @param flag
	 */
	public static void setFlag(Object flag) {
		threadFlag.set(flag);
	}

	public static boolean isDebugEnabled() {
		return getLog().isDebugEnabled();
	}

	public static boolean isTraceEnabled() {
		return getLog().isTraceEnabled();
	}

	public static Log getLog(Class clazz) throws Exception {
		return LogFactory.getLog(clazz);
	}

	public static Log getLog(String name) throws Exception {
		return LogFactory.getLog(name);
	}
}
