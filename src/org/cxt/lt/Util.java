package org.cxt.lt;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.cxt.lt.util.ConfigManager;

public class Util {
	public static Point getScreenSize() {
		Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(resolution);

		return new Point(rectangle.width, rectangle.height);
	}

	public static void saveImageToDefaultFile(BufferedImage aBufferedImage) {
		saveImageToFile(aBufferedImage,
				ConfigManager.getString("SHOT_IMAGE_TMP_FILE"));
	}

	public static void saveImageToFile(BufferedImage aBufferedImage,
			String aFilePath) {

		File file = new File(aFilePath);

		file.getParentFile().mkdirs();

		try {
			ImageIO.write(aBufferedImage, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean compareImage(BufferedImage image1,
			BufferedImage image2) {

		boolean ret = true;

		int w1 = image1.getWidth(null);
		int h1 = image1.getHeight(null);

		int w2 = image2.getWidth(null);
		int h2 = image2.getHeight(null);

		if ((w1 == w2) && (h1 == h2)) {

			for (int i = 0; i < w1; ++i) {
				for (int j = 0; j < h1; ++j) {
					if (image1.getRGB(i, j) == image2.getRGB(i, j)) {
						continue;
					} else {
						ret = false;
						return ret;
					}
				}
			}

		} else {
			ret = false;
		}

		return ret;
	}

}
