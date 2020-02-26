package com.prudential.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogApp implements Serializable {

	private static final long serialVersionUID = 1023948788243868578L;
	private final String NAMECLASS;
	private static final String PATHDIR = "/home/padmin/log/resubmission/";
	private String type;
	private String LOG_NAME = "customlog";
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat dfLog = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

	public static enum LOG {
		ERROR, WARN, INFO, DEBUG, FATAL
	};

	public LogApp(Class clazz) {
		NAMECLASS = clazz.getName();
		File file = new File(PATHDIR);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public LogApp(Class clazz, String logName) {
		NAMECLASS = clazz.getName();
		LOG_NAME = logName;
		File file = new File(PATHDIR);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public void info(String msg) {
		String fileLogName;
		if (type == null) {
			fileLogName = LOG_NAME + ".log." + df.format(new Date());
		} else {
			fileLogName = LOG_NAME + type + ".log." + df.format(new Date());
		}
		File file = new File(PATHDIR + fileLogName);
		String contentLog = writeContentLog(msg, LOG.INFO);
		writeLog(file, contentLog);

	}

	public void debug(String msg) {
		String fileLogName;
		if (type == null) {
			fileLogName = LOG_NAME + ".log." + df.format(new Date());
		} else {
			fileLogName = LOG_NAME + type + ".log." + df.format(new Date());
		}
		File file = new File(PATHDIR + fileLogName);
		String contentLog = writeContentLog(msg, LOG.DEBUG);
		writeLog(file, contentLog);
	}

	public void error(String msg) {
		String fileLogName;
		if (type == null) {
			fileLogName = LOG_NAME + ".log." + df.format(new Date());
		} else {
			fileLogName = LOG_NAME + type + ".log." + df.format(new Date());
		}
		File file = new File(PATHDIR + fileLogName);
		String contentLog = writeContentLog(msg, LOG.ERROR);
		writeLog(file, contentLog);
	}

	public void warn(String msg) {
		String fileLogName;
		if (type == null) {
			fileLogName = LOG_NAME + ".log." + df.format(new Date());
		} else {
			fileLogName = LOG_NAME + type + ".log." + df.format(new Date());
		}
		File file = new File(PATHDIR + fileLogName);
		String contentLog = writeContentLog(msg, LOG.WARN);
		writeLog(file, contentLog);
	}

	public void fatal(String msg) {
		String fileLogName;
		if (type == null) {
			fileLogName = LOG_NAME + ".log." + df.format(new Date());
		} else {
			fileLogName = LOG_NAME + type + ".log." + df.format(new Date());
		}
		File file = new File(PATHDIR + fileLogName);
		String contentLog = writeContentLog(msg, LOG.FATAL);
		writeLog(file, contentLog);

	}

	public void writeLogApp(String log) {
		String fileLogName;
		if (type == null) {
			fileLogName = LOG_NAME + ".log." + df.format(new Date());
		} else {
			fileLogName = LOG_NAME + type + ".log." + df.format(new Date());
		}
		File file = new File(PATHDIR + fileLogName);
		String contentLog = writeContentLog(log);
		writeLog(file, contentLog);
	}

	private String writeContentLog(String log) {
//		DateFormat dfLog = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		StringBuilder sb = new StringBuilder();
		sb.append(dfLog.format(new Date()));
		sb.append(" - ");
		sb.append(log);
		return sb.toString();
	}

	private String writeContentLog(String log, LOG level) {
//		DateFormat dfLog = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append(dfLog.format(new Date()));
		sb.append(" ");
		sb.append(level);
		sb.append(" ");
		sb.append(NAMECLASS);
		sb.append(" - ");
		sb.append(log);
		return sb.toString();
	}

	private synchronized Boolean writeLog(File logFile, String content) {
		boolean ret = false;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
			writer.write(content);
			writer.append("\n");
			writer.close();
			ret = true;
		} catch (Exception e) {

		}
		return ret;
	}

}
