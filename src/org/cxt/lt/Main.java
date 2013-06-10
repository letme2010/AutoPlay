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
import org.cxt.lt.util.UIFlagManager.OnFlagDetetedListener;

public class Main {

	private static void setCopyBoardText(String aText) {
		Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
		setClipboardText(sysc, aText);

	}

	private static void setClipboardText(Clipboard clip, String writeMe) {
		Transferable tText = new StringSelection(writeMe);
		clip.setContents(tText, null);
	}

	private static final OnFlagDetetedListener sOnFladDetetedListener = new OnFlagDetetedListener() {

		@Override
		public void onFlagDetetedSuccess(int aFlag, FlagWrap aFlagWrap) {
			switch (aFlag) {
			case UIFlagManager.LOGIN_WAIT_FLAG: {

				setCopyBoardText(SimplayUserManager.getUserName());
				LtRobot.getInstance().pressCtrlV();
				LtRobot.getInstance().delay(500);

				LtRobot.getInstance().pressTab();
				LtRobot.getInstance().delay(500);

				setCopyBoardText(SecureConfigManager
						.getString("SIMPLAY_LOGIN_USER_PASSWORD"));
				LtRobot.getInstance().pressCtrlV();
				LtRobot.getInstance().delay(500);

				LtRobot.getInstance().pressEnter();

			}
				break;

			case UIFlagManager.SCRIPT_LIB: {
				LtRobot.getInstance().leftClickInMainWindow(136, 70);
			}
				break;
			case UIFlagManager.COLLECTION: {
				LtRobot.getInstance().leftClickInMainWindow(84, 243);
			}
				break;

			case UIFlagManager.OPEN_SCRIPT: {
				LtRobot.getInstance().leftClickInMainWindow(658, 155);
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

			case UIFlagManager.SELECTE_SCRIPT: {
				LtRobot.getInstance().leftClickInMainWindow(58, 141);
			}
				break;

			case UIFlagManager.S_WORD_START: {
				LtRobot.getInstance().leftClickInMainWindow(558, 168);
			}
				break;

			case UIFlagManager.SCRIPT_SD_GUNDAM: {
				LtRobot.getInstance().leftClickInMainWindow(368, 236);
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

		if (true) {

			// kill something...

			Util.exec("taskkill /f /im "
					+ ConfigManager.getString("SIMPLE_PLAY_PROCESS_NAME"));
			Thread.sleep(1000);

			Util.exec("taskkill /f /im "
					+ ConfigManager.getString("RUNNER_PROCESS_NAME"));
			Thread.sleep(1000);

			Util.exec(ConfigManager.getString("SIMPLE_PLAY_PATH"));

			// execute action.

			UIFlagManager.addListener(sOnFladDetetedListener);

			UIFlagManager.invorkDetect(UIFlagManager.LOGIN_WAIT_FLAG);

			UIFlagManager.invorkDetect(UIFlagManager.SCRIPT_LIB);

			UIFlagManager.invorkDetect(UIFlagManager.SELECTE_SCRIPT);

			UIFlagManager.invorkDetect(UIFlagManager.S_WORD_START);

			UIFlagManager.invorkDetect(UIFlagManager.SCRIPT_SD_GUNDAM);

		}

		LtRobot.getInstance().delay(3000);

		BufferedImage greenButtonFlagImage = UIFlagManager
				.getImage("OPEN_SCRIPT_GREEN_BUTTON2");
		LT.assertTrue(null != greenButtonFlagImage);

		BufferedImage scriptFlagImage = UIFlagManager
				.getImage("SCRIPT_OF_RED_KILLER_BACKGROUND");

		LT.assertTrue(null != scriptFlagImage);

		int greenButtonLeft = 688;
		int greenButtonRight = 717;
		int scanGreenButtonTopLimit = 200;
		int scanGreenButtonBottomLimit = 530;

		int scriptShotLeft = 688 - 544;
		int scriptShotRight = 688 - 217;
		int scriptShotTopLimit = scanGreenButtonTopLimit
				+ LtRobot.getLeftTopOffset().y;
		int scriptShotBottomLimit = scanGreenButtonBottomLimit
				+ LtRobot.getLeftTopOffset().y;

		for (int top = scriptShotTopLimit; top <= scriptShotBottomLimit; ++top) {

			if (true) {
				// detect logic

				FlagWrap scriptShotFlagWrap = new FlagWrap(scriptShotLeft, top,
						scriptShotRight,
						top + greenButtonFlagImage.getHeight(),
						OffsetType.SIMPLAY_MAIN_WINDOW, "");

				BufferedImage scriptShotImage = LtRobot.getInstance()
						.screenShot(scriptShotFlagWrap);

				// Util.saveImageToFile(scriptShotImage,
				// "C:\\Users\\letme2010\\Desktop\\t\\" + top + ".png");

				if (Util.compareImage(scriptShotImage, scriptFlagImage)) {
					System.out.println("find it.");

					LtRobot.getInstance().leftClickInMainWindow(700,
							scriptShotFlagWrap.getOriginTop() + 9);

					break;
				}
			}

			if (false) {
				// use green button to get script image.
				FlagWrap flagWrap = new FlagWrap(greenButtonLeft, top,
						greenButtonRight, top
								+ (greenButtonFlagImage.getHeight()),
						OffsetType.SIMPLAY_MAIN_WINDOW, "");

				BufferedImage shotImage = LtRobot.getInstance().screenShot(
						flagWrap);

				if (Util.compareImage(greenButtonFlagImage, shotImage)) {
					// System.err.println(flagWrap);

					FlagWrap scriptFlagWrap = new FlagWrap(
							flagWrap.getOriginLeft() - 544,
							flagWrap.getOriginTop(),
							flagWrap.getOriginRight() - 217,
							flagWrap.getOriginBottom(),
							OffsetType.SIMPLAY_MAIN_WINDOW, "");

					BufferedImage shotScriptImage = LtRobot.getInstance()
							.screenShot(scriptFlagWrap);

					Util.saveImageToFile(
							shotScriptImage,
							"C:\\Users\\letme2010\\Desktop\\t\\"
									+ flagWrap.getTop() + ".png");

				}
			}

		}

		if (false) {
			Thread.sleep(3000);

			BufferedImage image = LtRobot.getInstance().screenShot(
					new FlagWrap(527, 156, 581, 170,
							OffsetType.SIMPLAY_MAIN_WINDOW, ""));

			Util.saveImageToDefaultFile(image);
		}

		System.out.println("finish.");

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
			LtRobot.getInstance().leftClickInMainWindow(136, 70);
			Thread.sleep(1000);

			// my collection
			LtRobot.getInstance().leftClickInMainWindow(84, 243);
			Thread.sleep(1000);

			// open script
			LtRobot.getInstance().leftClickInMainWindow(658, 155);
			Thread.sleep(1000);

			// // wait load script.
			Thread.sleep(60000);

			LtRobot.getInstance().pressEnter();
			LtRobot.getInstance().pressEnter();
			LtRobot.getInstance().pressEnter();
			LtRobot.getInstance().pressEnter();

			LtRobot.getInstance().leftClickInMainWindow(340, 420);
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

			setCopyBoardText(SecureConfigManager.getString("SD_LOGIN_PASSWORD"));
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
