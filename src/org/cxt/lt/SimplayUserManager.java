package org.cxt.lt;

import java.util.Date;

import org.cxt.lt.util.ConfigManager;

public class SimplayUserManager {
	public static final String getUserName() {
		@SuppressWarnings("deprecation")
		int index = new Date().getHours() + 2;
		return ConfigManager.getString("SIMPLAY_LOGIN_USER_NAME_PREFIX")
				+ index;
	}
}
