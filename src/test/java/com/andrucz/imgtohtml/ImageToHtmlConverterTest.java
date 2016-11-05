package com.andrucz.imgtohtml;

import java.io.File;
import java.io.IOException;

/*
 * Not a real test. Just a way to generate an HTML to find errors manually.
 */
public final class ImageToHtmlConverterTest {

	public static void main(String[] args) throws IOException {
		ImageToHTMLConverter.convert(new File("src/test/resources/angry-monkey.jpg"),
				new File("src/test/resources/result.html"));
	}

}
