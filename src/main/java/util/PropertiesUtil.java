package util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author July.Wang
 *
 */
public class PropertiesUtil {

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	private static Properties props;

	public static final String DIRVER_PATH = "driver.path";
	public static final String FILE_PATH = "file.path";

	static {
		String fileName = "config.properties";
		props = new Properties();
		try {
			props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),
					"UTF-8"));
		} catch (IOException e) {
			logger.error("配置文件读取异常", e);
		}
	}

	// 获取配置文件中的key对呀的value
	public static String getProperty(String key) {
		String value = props.getProperty(key.trim());
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return value.trim();
	}

	// 获取配置文件中的key对呀的value，如果未空，返回默认值
	public static String getProperty(String key, String defaultValue) {
		String value = props.getProperty(key.trim());
		if (StringUtils.isBlank(value)) {
			value = defaultValue;
		}
		return value.trim();
	}

}