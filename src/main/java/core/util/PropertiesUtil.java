package core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

import core.exception.HMException;

public class PropertiesUtil {

	public static final ClassPathResource PROPERTY_FILE = new ClassPathResource("properties/globals.properties");

	public static String getProdMode() {

		String value = "";

		FileInputStream fis = null;
		BufferedInputStream bis = null;

		try {
			Properties props = new Properties();

			File file = new File(PROPERTY_FILE.getURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			props.load(bis);

			if  ( null == props.getProperty("globals.prod.mode") )  {
				value = "";
			}
			else  {
				value = props.getProperty("globals.prod.mode").trim();
			}

			bis.close();
			fis.close();
		} catch (Exception e) {
			throw new HMException("Property Error [getProdMode()]");
		}

		return value;

	}

	public static String getProperty(String keyName) {

		String value = "";

		FileInputStream fis = null;
		BufferedInputStream bis = null;

		try {
			Properties props = new Properties();

			File file = new File(PROPERTY_FILE.getURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			props.load(bis);

			if  ( null == props.getProperty(keyName) )  {
				value = "";
			}
			else  {
				value = props.getProperty(keyName).trim();
			}

			bis.close();
			fis.close();
		} catch (Exception e) {
			throw new HMException("Property Error [getProperty()]");
		}

		return value;

	}

}
