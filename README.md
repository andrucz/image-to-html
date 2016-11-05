image-to-html
=============

Transforms an image into an HTML table (each pixel is transformed into a table cell). Just for fun (if it can be considered fun).

Example of use
--------------

```java
File image = new File("angry-monkey.jpg");
File destination = new File("result.html");
ImageToHTMLConverter.convert(image, destination);
```
