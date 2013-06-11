package lrg.cxt.lt.deamon;

import org.cxt.lt.Util;
import org.cxt.lt.util.ConfigManager;

public class Deamon {

	public static void main(String[] args) throws InterruptedException {

		// "tasklist /FI "IMAGENAME eq runner.exe""

		boolean hasCfgParams = false;

		for (int i = 0, len = args.length; i < len; i++) {
			String arg = args[i];

			if ("-cfg".equals(arg)) {
				String configFilePath = args[i + 1];
				System.out.println("-cfg " + configFilePath);
				ConfigManager.initConfigFilePath(configFilePath);
				hasCfgParams = true;
			}
		}

		if (!hasCfgParams) {
			System.err.println("Please execute me by -cfg params");
			return;
		}

		String testSimplayScriptRunningCMD = ConfigManager
				.getString("TEST_SIMPLAY_SCRIPT_RUNNING_CMD");

//		String msg = Util.exec(testSimplayScriptRunningCMD);

	}

}
