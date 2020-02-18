package utils;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigDataprovider {
	Properties properties;
	Logger logger = Logger.getLogger(ConfigDataprovider.class);

	public ConfigDataprovider() {
		File src = new File("./configurations/Config.properties");
		try (FileInputStream fis = new FileInputStream(src);){
			
			properties = new Properties();
			properties.load(fis);
		} catch (Exception e) {
			logger.warn("Unable to load config File", e);
		}

	}

	public String getDataFromConfig(String key) {
		return properties.getProperty(key);
	}

	public String getApiURI() {

		return properties.getProperty("apiUri");
	}
	public String getCity() {

		return properties.getProperty("city");
	}
	public String getpostURI() {

		return properties.getProperty("post");
	}
	public String getPOSTPath() {

		return properties.getProperty("postpath");
	}
	
}
