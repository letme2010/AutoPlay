package org.cxt.lt;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.cxt.lt.util.ConfigManager;
import org.cxt.lt.util.LT;
import org.cxt.lt.util.SecureConfigManager;
import org.cxt.lt.util.UIFlagManager;
import org.cxt.lt.util.UIFlagManager.FlagWrap;
import org.cxt.lt.util.UIFlagManager.OnFladDetetedListener;

public class Main {

	private static void setCopyBoardText(String aText) {
		Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
		setClipboardText(sysc, aText);

	}

	private static void setClipboardText(Clipboard clip, String writeMe) {
		Transferable tText = new StringSelection(writeMe);
		clip.setContents(tText, null);
	}

	private static final OnFladDetetedListener sOnFladDetetedListener = new OnFladDetetedListener() {

		@Override
		public void onFlagDetetedSuccess(int aFlag, FlagWrap aFlagWrap) {
			switch (aFlag) {
			case UIFlagManager.LOGIN_WAIT_FLAG: {

				setCopyBoardText(SimplayUserManager.getUserName());
				LtRobot.getInstance().pressCtrlV();
				LtRobot.getInstance().delay(500);

				LtRobot.getInstance().pressTab();
				LtRobot.getInstance().delay(500);

				setCopyBoardText(ConfigManager
						.getString("SIMPLAY_LOGIN_USER_PASSWORD"));
				LtRobot.getInstance().pressCtrlV();
				LtRobot.getInstance().delay(500);

				LtRobot.getInstance().pressEnter();

			}
				break;

			case UIFlagManager.SCRIPT_LIB: {
				LtRobot.getInstance().leftClick(136, 70);
			}
				break;
			case UIFlagManager.COLLECTION: {
				LtRobot.getInstance().leftClick(84, 243);
			}
				break;

			case UIFlagManager.OPEN_SCRIPT: {
				LtRobot.getInstance().leftClick(658, 155);
			}
				break;

			case UIFlagManager.DIALOG1: {
				LtRobot.getInstance().pressEnter();
			}
				break;

			case UIFlagManager.DIALOG2: {
				LtRobot.getInstance().pressEnter();
			}
				break;

			case UIFlagManager.DIALOG3: {
				LtRobot.getInstance().pressEnter();
			}
				break;

			case UIFlagManager.DIALOG_MODE_TIPS: {
				LtRobot.getInstance().pressEnter();
			}
				break;

			default: {
				LT.assertTrue(false);
			}
				break;
			}
		}

		@Override
		public void onFlagDetetedTimeOut(int aFlag, FlagWrap aFlagWrap) {
			// TODO Auto-generated method stub

		}

	};

	@Deprecated
	public static void robotScreen() {

		BufferedImage image = LtRobot.getInstance().screenShot(
				new FlagWrap(34, 239, 76, 259, OffsetType.SIMPLAY_MAIN_WINDOW,
						"SCRIPT_LIB"));

		Util.saveImageToDefaultFile(image);
	}

	/**
	 * @param args
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {

		boolean isFullWindowSytle = true;

		boolean hasCfgParams = false;

		for (int i = 0, len = args.length; i < len; i++) {
			String arg = args[i];

			if ("-cfg".equals(arg)) {
				String configFilePath = args[i + 1];
				ConfigManager.initConfigFilePath(configFilePath);
				hasCfgParams = true;
			}
		}

		SecureConfigManager.initConfigFilePath(ConfigManager
				.getString("SECURE_KV_FILE_PATH"));

		if (!hasCfgParams) {
			System.err.println("Please execute me by -cfg params");
			return;
		}

		// kill something...
		Runtime.getRuntime().exec(
				"taskkill /f /im "
						+ ConfigManager.getString("SIMPLE_PLAY_PROCESS_NAME"));
		Thread.sleep(1000);

		Runtime.getRuntime().exec(
				"taskkill /f /im "
						+ ConfigManager.getString("RUNNER_PROCESS_NAME"));
		Thread.sleep(1000);

		Runtime.getRuntime().exec(ConfigManager.getString("SIMPLE_PLAY_PATH"));

		// invork action.

		UIFlagManager.addListener(sOnFladDetetedListener);

		UIFlagManager.invorkDetect(UIFlagManager.LOGIN_WAIT_FLAG);

		UIFlagManager.invorkDetect(UIFlagManager.SCRIPT_LIB);

		UIFlagManager.invorkDetect(UIFlagManager.COLLECTION);

		UIFlagManager.invorkDetect(UIFlagManager.OPEN_SCRIPT);

		UIFlagManager.invorkDetect(UIFlagManager.DIALOG1);

		UIFlagManager.invorkDetect(UIFlagManager.DIALOG2);

		UIFlagManager.invorkDetect(UIFlagManager.DIALOG3);

		UIFlagManager.invorkDetect(UIFlagManager.DIALOG_MODE_TIPS);

		{
			Thread.sleep(3000);

			BufferedImage image = LtRobot
					.getInstance()
					.screenShot(
							new FlagWrap(
									228,
									404,
									445,
									436,
									OffsetType.SIMPLAY_RED_KILLER_BACKGROUND_SCRIPT_WINDOW,
									""));

			Util.saveImageToDefaultFile(image);
		}

		System.out.println("success");

		if (false) {

			setCopyBoardText(SimplayUserManager.getUserName());
			LtRobot.getInstance().pressCtrlV();
			Thread.sleep(500);

			LtRobot.getInstance().pressTab();
			Thread.sleep(500);

			setCopyBoardText(ConfigManager
					.getString("SIMPLAY_LOGIN_USER_PASSWORD"));
			LtRobot.getInstance().pressCtrlV();
			Thread.sleep(500);

			LtRobot.getInstance().pressEnter();

			System.out.println("wait 10 sec for simple play login.");
			Thread.sleep(10000);

			// // script lib
			LtRobot.getInstance().leftClick(136, 70);
			Thread.sleep(1000);

			// my collection
			LtRobot.getInstance().leftClick(84, 243);
			Thread.sleep(1000);

			// open script
			LtRobot.getInstance().leftClick(658, 155);
			Thread.sleep(1000);

			// // wait load script.
			Thread.sleep(60000);

			LtRobot.getInstance().pressEnter();
			LtRobot.getInstance().pressEnter();
			LtRobot.getInstance().pressEnter();
			LtRobot.getInstance().pressEnter();

			LtRobot.getInstance().leftClick(340, 420);
			Thread.sleep(1000);

			LtRobot.getInstance().pressEnter();
			LtRobot.getInstance().pressEnter();
			LtRobot.getInstance().pressEnter();
			LtRobot.getInstance().pressEnter();

			Thread.sleep(2000);
			// Enter script face
			LtRobot.getInstance().leftClickInScriptUI(574, 183);
			Thread.sleep(1000);

			// copy goline path.
			setCopyBoardText(ConfigManager.getString("GOLINE_SD"));
			LtRobot.getInstance().pressCtrlV();
			LtRobot.getInstance().pressEnter();

			// set password
			LtRobot.getInstance().leftClickInScriptUI(270, 204);
			Thread.sleep(500);

			LtRobot.getInstance().rightClickCurrntPosition();
			Thread.sleep(500);

			LtRobot.getInstance().pressCtrlA();
			LtRobot.getInstance().pressDelete();
			Thread.sleep(500);

			setCopyBoardText(ConfigManager.getString("SD_LOGIN_PASSWORD"));
			LtRobot.getInstance().pressCtrlV();
			Thread.sleep(500);

			// set window style
			LtRobot.getInstance().leftClickInScriptUI(523, 203);
			Thread.sleep(500);

			if (isFullWindowSytle) {
				// default is full window
				LtRobot.getInstance().leftClickInScriptUI(500, 233);
			} else {
				// set 1280x768
				LtRobot.getInstance().leftClickInScriptUI(500, 233 - 13);
			}

			Thread.sleep(1000);
			LtRobot.getInstance().pressEnter();
			Thread.sleep(1000);
			LtRobot.getInstance().pressEnter();
			Thread.sleep(500);

			// set window system
			LtRobot.getInstance().leftClickInScriptUI(545, 343);
			Thread.sleep(500);
			LtRobot.getInstance().leftClickInScriptUI(518, 372);

			// save setting
			LtRobot.getInstance().pressEnter();
			Thread.sleep(1000);
			LtRobot.getInstance().pressEnter();

			// start script
			LtRobot.getInstance().startScript();

		}

	}
}
