package com.andrucz.imgtohtml;

import java.awt.image.BufferedImage;
import java.io.PrintStream;

import static com.andrucz.commons.check.Checks.*;

public final class ImageToHTMLConverter {

	private ImageToHTMLConverter() {
	}
	
	public static void write(BufferedImage image, PrintStream out) {
		checkNotNull(image, "image");
		checkNotNull(out, "out");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<style type=\"text/css\">");
		out.println("table {border-style: none; border-spacing: 0px; padding: 0px;}");
		out.println("td {width: 1px; height: 1px;}");
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
					int r = (rgb >> 16) & 0xFF;
					int g = (rgb >> 8) & 0xFF;
					int b = rgb & 0xFF;
					
					out.print("<td bgcolor=\"");
					writeHexColor(r, g, b, out);
					out.print("\"");
					
					if (span > 1) {
						out.print(" colspan=\"" + span + "\"");
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