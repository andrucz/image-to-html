package com.andrucz.imgtohtml;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;

public final class ImageToHTMLConverter {

	private ImageToHTMLConverter() {
	}

	public static void convert(File file, PrintStream out) throws IOException {
		BufferedImage image = ImageIO.read(file);
		convert(image, out);
	}

	public static void convert(File file, File dest) throws IOException {
		BufferedImage image = ImageIO.read(file);
		convert(image, dest);
	}

	public static void convert(BufferedImage image, File dest) throws FileNotFoundException {
		PrintStream out = new PrintStream(dest);
		try {
			convert(image, out);
			out.flush();
		} finally {
			out.close();
		}
	}

	public static void convert(BufferedImage image, PrintStream out) {
		if (image == null) {
			throw new NullPointerException("image");
		}
		if (out == null) {
			throw new NullPointerException("out");
		}

		out.println("<html>");
		out.println("<head>");
		out.println("<style type=\"text/css\">");
		out.println("table {border-collapse: collapse; border-spacing: 0;}");
		out.println("table td {padding: 0; width: 1; height: 1;}");
		out.println("</style>");
		out.println("</head>");

		out.println("<body>");

		int h = image.getHeight();
		int w = image.getWidth();

		out.println("<table>");

		for (int i = 0; i < h; i++) {

			out.print("<tr>");

			int lastRGB = 0;
			int span = 1;

			for (int j = 0; j < w; j++) {
				int rgb = image.getRGB(j, i);

				if (j == 0) {
					lastRGB = rgb;
				}

				if (j == w - 1 || lastRGB != rgb) {
					int r = (lastRGB >> 16) & 0xFF;
					int g = (lastRGB >> 8) & 0xFF;
					int b = lastRGB & 0xFF;

					out.print("<td bgcolor=\"");
					writeHexColor(r, g, b, out);
					out.print("\"");

					if (span > 1) {
						out.print(" colspan=\"");
						out.print(span);
						out.print("\"");
						span = 1;
					}

					out.print("/>");

					lastRGB = rgb;

				} else {
					span++;
				}
			}

			out.println("</tr>");
		}

		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

	private static void writeHexCode(int colorComponent, PrintStream out) {
		String hex = Integer.toString(colorComponent, 16);

		if (hex.length() == 1) {
			out.print('0');
		}

		out.print(hex.toUpperCase());
	}

	private static void writeHexColor(int red, int green, int blue, PrintStream out) {
		out.print('#');
		writeHexCode(red, out);
		writeHexCode(green, out);
		writeHexCode(blue, out);
	}

}