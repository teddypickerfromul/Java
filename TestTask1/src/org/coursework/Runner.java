package org.coursework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Runner {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */

	static long MAXDICTWORDS = 10000;
	static long MAXINPUTFILESIZE = 2097152;

	static String htmlHead = "<html><head><title>Text</title></head><body>";
	static String htmlTail = "</body></html>";

	static String lineBreaker = System.getProperty("line.separator");

	public static void main(String[] args) throws FileNotFoundException,
			IOException, FileFormatException {

		if (args.length < 3) {
			System.out.println("Illegal usage!");
			System.out
					.println("Use it so : App  <absolute path to input file> <absolute path to dictionary file> <absolute path to output HTML>");
			System.out
					.println("Example : App /home/user/in.txt /home/user/dict.txt /home/teddy/out.html");
		} else {

			DictionaryFile d = new DictionaryFile(args[1], MAXDICTWORDS);
			InputFile inf = new InputFile(args[0], MAXINPUTFILESIZE);
			inf.process(d, htmlHead, htmlTail, args[2]);
			System.gc();
		}

	}

}
