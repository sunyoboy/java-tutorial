package com.usual.imageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class GenerateImage {

	public static final int WIDTH = 60;
	public static final int HEIGHT = 20;

	private static Random random = new Random();

	public static Random getRandom() {
		return random;
	}

	public static Color getRandomColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;

		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);

		Color color = new Color(r, g, b);
		return color;
	}

	public static int getRandomValue(int value) {
		return getRandom().nextInt(value);
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(getRandomColor(200, 250));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		g.setColor(getRandomColor(160, 200));

		Random random = getRandom();
		// 产生干扰线
		for (int i = 0; i < 130; i++) {
			int x1 = random.nextInt(WIDTH);
			int y1 = random.nextInt(HEIGHT);
			int x2 = random.nextInt(12);
			int y2 = random.nextInt(12);
			g.drawLine(x1, y1, x1 + x2, y1 + y2);
		}

		String code = "";
		for (int i = 0; i < 4; i++) {
			String number = String.valueOf(random.nextInt(10));
			code += number;
			g.setColor(new Color(15 + getRandomValue(120),
					15 + getRandomValue(120), 15 + getRandomValue(120)));
			g.drawString(number, 12*i + 6, 16);
		}
		
		System.out.println(code);
		g.dispose();
		ImageIO.write(image, "JPEG", new FileOutputStream("D:/tmp/code.jpg"));
	}
}
