package org.cxt.lt.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
	private static Map<String, Object> sConfigMap = new HashMap<String, Object>();

	private static boolean sHasInit = false;

	private ConfigManager() {

	}

	public static void initConfigFilePath(String configFilePath) {
		File configFile = new File(configFilePath);
		if (configFile.exists() && configFile.isFile()) {
		} else {
			throw new RuntimeException("config file not found.");
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(configFile)));

			for (;;) {
				String line = reader.readLine();
				if (null != line) {
					int indexOfEqualsSin = line.indexOf("=");

					String key = line.substring(0, indexOfEqualsSin);
					String value = line.substring(indexOfEqualsSin + 1,
							line.length());

					LT.notNull(key);
					LT.notNull(value);

					sConfigMap.put(key, value);

				} else {
					break;
				}
			}

			reader.close();

			sHasInit = true;

		} catch (Exception e) {
			System.err
					.println("init GConfig instance fail, check config file in current folder.");
		}
	}

	private static void checkInit() {
		if (!sHasInit) {
			throw new RuntimeException("not init.");
		}
	}

	public static String getString(String key) {

		checkInit();

		LT.notNull(sConfigMap);
		String ret = (String) sConfigMap.get(key);
		LT.notNull(ret);
		LT.assertTrue(!"".equals(ret.trim()));
		return ret;
	}

	public static int getInt(String key) {

		checkInit();

		String retStr = getString(key);
		Integer ret = Integer.valueOf(retStr);
		return ret;
	}
}
