package org.cxt.lt.test;

import java.awt.Color;

import javax.swing.JFrame;

import junit.framework.TestCase;

public class TestSnot extends TestCase {

	private JFrame mFrame;

	// public void test1() {
	//
	// LtRobot robot = new LtRobot();
	// robot.screenShot(42, 112, 92, 128);
	// }
	//
	// public void test2() throws IOException {
	//
	// BufferedImage image1 = ImageIO.read(new File(
	// "C:\\Users\\letme2010\\Desktop", "img1.png"));
	//
	// BufferedImage image2 = ImageIO.read(new File(
	// "C:\\Users\\letme2010\\Desktop", "img2.png"));
	//
	// assertTrue(Util.compareImage(image1, image2));
	//
	// }
	//
	// public void test3() throws IOException {
	//
	// BufferedImage image1 = ImageIO.read(new File(
	// "C:\\Users\\letme2010\\Desktop", "img1.png"));
	//
	// BufferedImage image2 = ImageIO.read(new File(
	// "C:\\Users\\letme2010\\Desktop", "img3.png"));
	//
	// assertFalse(Util.compareImage(image1, image2));
	//
	// }
	//
	// public void test4() throws InterruptedException
	// {
	// KeyboardFocusManager.getCurrentKeyboardFocusManager()
	// .addKeyEventDispatcher(new KeyEventDispatcher() {
	// @Override
	// public boolean dispatchKeyEvent(KeyEvent e) {
	// System.out.println("Got key event!");
	// return false;
	// }
	// });
	//
	// Thread.sleep(100000);
	// }

	public void test5() throws InterruptedException {

		mFrame = new JFrame();
		mFrame.setFocusable(false);

		mFrame.setUndecorated(true);
		
		mFrame.setLocation(30, 30);
		mFrame.setSize(300, 300);
		mFrame.setBackground(new Color(1.0f, 0, 1.0f, 0.5f));
		mFrame.setVisible(true);

		Thread.sleep(3000);

		mFrame.setVisible(false);
		Thread.sleep(1000);

		mFrame.setSize(200, 200);
		mFrame.setBackground(new Color(0.0f, 1.0f, 1.0f, 0.5f));
		mFrame.setVisible(true);

		Thread.sleep(3000);

	}
}
