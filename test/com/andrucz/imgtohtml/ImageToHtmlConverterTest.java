package com.andrucz.imgtohtml;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;

public final class ImageToHtmlConverterTest {

	public static void main(String[] args) throws IOException {
		File file = new File("test/angry-monkey.jpg");
		BufferedImage image = ImageIO.read(file);
		
		File dest = new File("test/result.html");
		dest.createNewFile();
		
		PrintStream out = new PrintStream(dest);
		try {
			ImageToHTMLConverter.write(image, out);
			out.flush();
		} finally {
			out.close();
		}
	}
	
}
