package test;

import java.io.FileWriter;
import java.io.IOException;

public class CharacterFileOutput {
	public static void main(String[] args) {
		FileWriter out = null;
		try {
			out = new FileWriter("book.txt");
			System.out.println("Encoding: " + out.getEncoding());
			out.write("Core Programming");
			out.close();
			out = null;
		} catch (IOException ioe) {
			System.out.println("IO problem: " + ioe);
			ioe.printStackTrace();
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ioe2) {
			}
		}
	}
}
