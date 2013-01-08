package com.andrucz.imgtohtml;

import java.io.File;
import java.io.IOException;

public final class ImageToHtmlConverterTest {

	public static void main(String[] args) throws IOException {
		ImageToHTMLConverter.write(new File("test/angry-monkey.jpg"), new File("test/result.html"));
	}
	
}
