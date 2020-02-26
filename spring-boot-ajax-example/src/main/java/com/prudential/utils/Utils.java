package com.prudential.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;

public class Utils {

	public static final String PATH = "C:/Users/c23250/Documents/Project/Flink/SME/resubmission_config.json";
//	public static final String PATH = "/home/padmin/conf/resubmission_config.json";
//	public static final String PATH = "/home/padmin/kuro/resubmission/resubmission_config.json";

	public static Properties parsePropertiesString(String s) {
		final Properties p = new Properties();
		try {
			p.load(new StringReader(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	public static String getFileFromClasspath(ClassLoader clzLoader, String filename) {
		String loadedFile = null;
		InputStream is = null;

		try {
			is = clzLoader.getResourceAsStream(filename);
			loadedFile = IOUtils.toString(is, Charset.forName("UTF-8"));
		} catch (IOException | NullPointerException e) {
			loadedFile = null;
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}

		return loadedFile;
	}

	public static String encrypt(final String secret, final String data) {

		byte[] decodedKey = Base64.getDecoder().decode(secret + data);

		try {
			Cipher cipher = Cipher.getInstance("AES");
			// rebuild key using SecretKeySpec
			SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, originalKey);
			byte[] cipherText = cipher.doFinal(data.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(cipherText);
		} catch (Exception e) {
			throw new RuntimeException("Error occured while encrypting data", e);
		}

	}

	public static String readFileContent(String path) {
		try {
			File f = new File(path);
			String content = null;
			if (f.exists()) {
				content = new String(Files.readAllBytes(Paths.get(path, new String[0])));
			} else {
				URL url = Utils.class.getClass().getResource(path);
				content = new String(Files.readAllBytes(Paths.get(url.toURI())));
			}
			return content;
		} catch (Exception e) {
			throw new RuntimeException("Unable to read content from file:" + path, e);
		}
	}

	public static String getDate7Ago() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate;
		Calendar calendar = new GregorianCalendar();
		inputDate = new Date();

		calendar.setTime(inputDate);
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		
		return dateFormat.format(calendar.getTime());
	}

}
