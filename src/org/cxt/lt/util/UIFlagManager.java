package org.cxt.lt.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.cxt.lt.LtRobot;
import org.cxt.lt.OffsetType;
import org.cxt.lt.Util;

public class UIFlagManager {

	private static final Map<Integer, FlagWrap> sMap = new HashMap<Integer, FlagWrap>();

	public static final int LOGIN_WAIT_FLAG = 1;
	public static final int SCRIPT_LIB = 2;
	public static final int COLLECTION = 3;
	public static final int OPEN_SCRIPT = 4;
	public static final int DIALOG1 = 5;
	public static final int DIALOG2 = 6;
	public static final int DIALOG3 = 7;
	public static final int DIALOG_MODE_TIPS = 8;
	public static final int SELECTE_SCRIPT = 9;
	public static final int S_WORD_START = 10;
	public static final int SCRIPT_SD_GUNDAM = 11;

	static {

		sMap.put(LOGIN_WAIT_FLAG, new FlagWrap(40, 115, 90, 130,
				OffsetType.SIMPLAY_LOGIN_WINDOW_OFFSET, "LOGIN_WAIT_FLAG"));

		sMap.put(SCRIPT_LIB, new FlagWrap(110, 35, 162, 98,
				OffsetType.SIMPLAY_MAIN_WINDOW, "SCRIPT_LIB"));

		sMap.put(COLLECTION, new FlagWrap(34, 239, 76, 259,
				OffsetType.SIMPLAY_MAIN_WINDOW, "COLLECTION"));

		sMap.put(OPEN_SCRIPT, new FlagWrap(633, 142, 682, 170,
				OffsetType.SIMPLAY_MAIN_WINDOW, "OPEN_SCRIPT"));

		sMap.put(DIALOG1, new FlagWrap(226, 370, 399, 385,
				OffsetType.SIMPLAY_MAIN_WINDOW, "DIALOG1"));

		sMap.put(DIALOG2, new FlagWrap(571, 411, 603, 425,
				OffsetType.SIMPLAY_MAIN_WINDOW, "DIALOG2"));

		sMap.put(DIALOG3, new FlagWrap(323, 355, 464, 378,
				OffsetType.SIMPLAY_MAIN_WINDOW, "DIALOG3"));

		sMap.put(DIALOG_MODE_TIPS, new FlagWrap(293, 276, 530, 294,
				OffsetType.SIMPLAY_MAIN_WINDOW, "DIALOG_MODE_TIPS"));

		sMap.put(SELECTE_SCRIPT, new FlagWrap(30, 130, 83, 149,
				OffsetType.SIMPLAY_MAIN_WINDOW, "SELECTE_SCRIPT"));

		sMap.put(S_WORD_START, new FlagWrap(550, 159, 565, 175,
				OffsetType.SIMPLAY_MAIN_WINDOW, "S_WORD_START"));

		sMap.put(SCRIPT_SD_GUNDAM, new FlagWrap(337, 226, 394, 246,
				OffsetType.SIMPLAY_MAIN_WINDOW, "SCRIPT_SD_GUNDAM"));
	}

	private static final List<OnFladDetetedListener> sListenerList = new ArrayList<OnFladDetetedListener>();

	public static void addListener(OnFladDetetedListener aListener) {

		LT.assertTrue(null != aListener);
		sListenerList.add(aListener);
	}

	public static void removeListener(OnFladDetetedListener aListener) {
		LT.assertTrue(null != aListener);
		sListenerList.remove(aListener);
	}

	public interface OnFladDetetedListener {
		public void onFlagDetetedSuccess(int aFlag, FlagWrap aFlagWrap);

		public void onFlagDetetedTimeOut(int aFlag, FlagWrap aFlagWrap);
	}

	public static FlagWrap getFlagWrap(int aFlag) {
		return sMap.get(aFlag);
	}

	public static void invorkDetect(int aFlag) {
		FlagWrap flagWrap = UIFlagManager.getFlagWrap(aFlag);

		do {

			BufferedImage image = LtRobot.getInstance().screenShot(flagWrap);
			BufferedImage flag = UIFlagManager.getImage(flagWrap.getFlagKey());

			if (Util.compareImageBinary(image, flag)) {

				break;
			} else {
				System.out.println("wait " + flagWrap.getFlagKey() + ".");
				LtRobot.getInstance().delay(1000);

			}

		} while (true);

		System.out.println(flagWrap.getFlagKey() + " detect success.");

		LtRobot.getInstance().showShotSuccessRect(flagWrap);
		LtRobot.getInstance().delay(1000);
		LtRobot.getInstance().hideRect();

		for (OnFladDetetedListener listener : sListenerList) {
			listener.onFlagDetetedSuccess(aFlag, flagWrap);
		}
	}

	public static BufferedImage getImage(String aFlagName) {
		LT.assertTrue(null != aFlagName);

		File file = new File(ConfigManager.getString("UI_WAIT_FLAG_FOLDER"),
				aFlagName + ".png");

		System.out.println(file.getAbsolutePath());
		LT.assertTrue(file.exists());

		BufferedImage ret = null;

		try {
			ret = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			LT.assertTrue(false);
		}

		return ret;

	}

	public static class FlagWrap {
		private int mLeft;
		private int mTop;
		private int mRight;
		private int mBottom;

		private int mOffsetType;

		private String mFlagKey;

		public FlagWrap(int aLeft, int aTop, int aRight, int aBottom,
				int aOffsetType, String aFlagKey) {
			super();
			mLeft = aLeft;
			mTop = aTop;
			mRight = aRight;
			mBottom = aBottom;
			mOffsetType = aOffsetType;
			mFlagKey = aFlagKey;
		}

		private int getOffsetLeft() {
			int ret = 0;

			switch (this.getOffsetType()) {
			case OffsetType.NO_OFFSET: {

			}
				break;

			case OffsetType.SIMPLAY_LOGIN_WINDOW_OFFSET: {
				ret = LtRobot.getLoginUILeftTopOffset().x;
			}
				break;
			case OffsetType.SIMPLAY_MAIN_WINDOW: {
				ret = LtRobot.getLeftTopOffset().x;
			}
				break;

			case OffsetType.SIMPLAY_RED_KILLER_BACKGROUND_SCRIPT_WINDOW: {
				ret = LtRobot.getLeftTopScriptUIOffset().x;
			}

				break;

			default: {
				LT.assertTrue(false);
			}
				break;
			}

			return ret;
		}

		private int getOffsetTop() {
			int ret = 0;

			switch (this.getOffsetType()) {
			case OffsetType.NO_OFFSET: {

			}
				break;

			case OffsetType.SIMPLAY_LOGIN_WINDOW_OFFSET: {
				ret = LtRobot.getLoginUILeftTopOffset().y;
			}
				break;

			case OffsetType.SIMPLAY_MAIN_WINDOW: {
				ret = LtRobot.getLeftTopOffset().y;
			}
				break;

			case OffsetType.SIMPLAY_RED_KILLER_BACKGROUND_SCRIPT_WINDOW: {
				ret = LtRobot.getLeftTopScriptUIOffset().y;
			}
				break;

			default: {
				LT.assertTrue(false);
			}
				break;
			}

			return ret;
		}

		public int getLeft() {
			return mLeft + this.getOffsetLeft();
		}

		public int getTop() {
			return mTop + this.getOffsetTop();
		}

		public int getRight() {
			return mRight + this.getOffsetLeft();
		}

		public int getBottom() {
			return mBottom + this.getOffsetTop();
		}

		private int getOffsetType() {
			return mOffsetType;
		}

		public String getFlagKey() {
			return mFlagKey;
		}

		public String toString() {

			return "[" + this.getLeft() + "," + this.getTop() + ","
					+ this.getRight() + "," + this.getBottom() + "]";

		}

	}

}
