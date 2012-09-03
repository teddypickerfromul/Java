package test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CharacterFileInput {
	public static void main(String[] args) {
		File file = new File("book.txt");
		FileReader in = null;
		if (file.exists()) {
			try {
				in = new FileReader(file);
				System.out.println("Encoding: " + in.getEncoding());
				char[] buffer = new char[(int) file.length()];
				in.read(buffer);
				System.out.println(buffer);
				in.close();
			} catch (IOException ioe) {
				System.out.println("IO problem: " + ioe);
				ioe.printStackTrace();
			}
		}
	}

}
