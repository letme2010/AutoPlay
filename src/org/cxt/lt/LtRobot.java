package org.cxt.lt;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.cxt.lt.util.ConfigManager;
import org.cxt.lt.util.LT;
import org.cxt.lt.util.UIFlagManager.FlagWrap;

public class LtRobot {

	private static LtRobot sInstance;

	private Robot mRobot;

	private JFrame mFrame = new JFrame();;

	synchronized public static LtRobot getInstance() {

		if (null == sInstance) {
			sInstance = new LtRobot();
		}

		return sInstance;
	}

	public LtRobot() {

		this.init();

		mFrame.setFocusable(false);
		mFrame.setUndecorated(true);
	}

	/**
	 * Delay
	 * 
	 * @param i
	 */
	public void delay(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void leftClick(int x, int y) {

		try {
			Thread.sleep(ConfigManager.getInt("ACTION_DELAY"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.mRobot.mouseMove(x, y);
		this.mRobot.mousePress(InputEvent.BUTTON1_MASK);
		this.mRobot.mouseRelease(InputEvent.BUTTON1_MASK);

	}

	public void leftClickInMainWindow(int x, int y) {

		Point leftTopOffset = LtRobot.getLeftTopOffset();
		this.leftClick(leftTopOffset.x + x, leftTopOffset.y + y);

	}

	public void leftClickInScriptUI(int x, int y) {

		Point leftTopOffset = LtRobot.getLeftTopScriptUIOffset();
		this.leftClick(leftTopOffset.x + x, leftTopOffset.y + y);
	}

	public void keyPress(int key) {
		this.mRobot.keyPress(key);
	}

	public void keyRelease(int key) {
		this.mRobot.keyRelease(key);
	}

	public void clickKey(int key) {

		this.mRobot.keyPress(key);
		this.mRobot.keyRelease(key);

	}

	public static Point getLoginUILeftTopOffset() {
		Point screenSize = Util.getScreenWorkingSize();

		return new Point(
				(screenSize.x - ConfigManager
						.getInt("SIMPLAY_PLAY_LOGIN_WINDOW_WIDTH")) / 2,
				(screenSize.y - ConfigManager
						.getInt("SIMPLAY_PLAY_LOGIN_WINDOW_HEIGHT")) / 2);
	}

	public static Point getLeftTopOffset() {
		Point screenSize = Util.getScreenWorkingSize();

		return new Point(
				(screenSize.x - ConfigManager
						.getInt("SIMPLAY_PLAY_WINDOW_WIDTH")) / 2,
				(screenSize.y - ConfigManager
						.getInt("SIMPLAY_PLAY_WINDOW_HEIGHT")) / 2);
	}

	public static Point getLeftTopScriptUIOffset() {
		Point screenSize = Util.getScreenWorkingSize();

		return new Point(
				(screenSize.x - ConfigManager
						.getInt("SIMPLAY_PLAY_WINDOW_WIDTH")) / 2,
				(screenSize.y - ConfigManager
						.getInt("SIMPLAY_PLAY_WINDOW_SCRIPT_UI_HEIGHT")) / 2);
	}

	private Point getLeftTopLoginOffset() {
		Point screenSize = Util.getScreenWorkingSize();

		return new Point(
				(screenSize.x - ConfigManager
						.getInt("SIMPLAY_PLAY_LOGIN_WINDOW_WIDTH")) / 2,
				(screenSize.y - ConfigManager
						.getInt("SIMPLAY_PLAY_LOGIN_WINDOW_HEIGHT")) / 2);
	}

	private void init() {
		try {
			this.mRobot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void pressEnter() {
		this.mRobot.keyPress(KeyEvent.VK_ENTER);
		this.mRobot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void pressCtrlV() {
		this.mRobot.keyPress(KeyEvent.VK_CONTROL);
		this.mRobot.keyPress(KeyEvent.VK_V);
		this.mRobot.keyRelease(KeyEvent.VK_V);
		this.mRobot.keyRelease(KeyEvent.VK_CONTROL);

	}

	public void pressDelete() {
		this.mRobot.keyPress(KeyEvent.VK_DELETE);
		this.mRobot.keyRelease(KeyEvent.VK_DELETE);
	}

	public void pressTab() {
		this.mRobot.keyPress(KeyEvent.VK_TAB);
		this.mRobot.keyRelease(KeyEvent.VK_TAB);
	}

	public void startScript() {
		this.mRobot.keyPress(KeyEvent.VK_F10);
		this.mRobot.keyRelease(KeyEvent.VK_F10);
	}

	public void rightClickCurrntPosition() {
		this.mRobot.mousePress(InputEvent.BUTTON3_MASK);
		this.mRobot.mouseRelease(InputEvent.BUTTON3_MASK);
	}

	public void pressCtrlA() {
		this.mRobot.keyPress(KeyEvent.VK_CONTROL);
		this.mRobot.keyPress(KeyEvent.VK_A);
		this.mRobot.keyRelease(KeyEvent.VK_A);
		this.mRobot.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * screen shot
	 * 
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @return
	 */

	public BufferedImage screenShot(FlagWrap aFlagWrap) {
		return this.screenShot(aFlagWrap.getLeft(), aFlagWrap.getTop(),
				aFlagWrap.getRight(), aFlagWrap.getBottom());
	}

	public BufferedImage screenShot(int left, int top, int right, int bottom) {
		this.showShotWaitRect(left, top, right, bottom);
		this.delay(10);
		this.hideRect();

		return this._screenShot(left, top, right, bottom);
	}

	private BufferedImage _screenShot(int left, int top, int right, int bottom) {

		return this.mRobot.createScreenCapture(new Rectangle(left, top, right
				- left, bottom - top));

	}

	/**
	 * Frame display
	 * 
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void showShotWaitRect(int left, int top, int right, int bottom) {
		this.showRect(left, top, right, bottom, new Color(1.0f, 0, 0, 0.3f));
	}

	public void showShotSuccessRect(FlagWrap aFlagWrap) {
		LT.assertTrue(null != aFlagWrap);
		this.showRect(aFlagWrap, new Color(0, 1.0f, 0, 0.3f));
	}

	public void showRect(FlagWrap aFlagWrap, Color aColor) {

		LT.assertTrue(null != aFlagWrap);
		LT.assertTrue(null != aColor);

		this.showRect(aFlagWrap.getLeft(), aFlagWrap.getTop(),
				aFlagWrap.getRight(), aFlagWrap.getBottom(), aColor);
	}

	public void showRect(int left, int top, int right, int bottom, Color color) {

		mFrame.setBackground(color);
		mFrame.setLocation(left, top);

		LT.assertTrue((0 <= left) && (0 <= right) && (0 <= top)
				&& (0 <= bottom));

		LT.assertTrue(0 <= (right - left));
		LT.assertTrue(0 <= (bottom - top));

		mFrame.setSize(right - left, bottom - top);
		mFrame.setVisible(true);
	}

	public void hideRect() {
		mFrame.setVisible(false);
	}

}
