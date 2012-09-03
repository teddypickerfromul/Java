package test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinaryFileInput {
	public static void main(String[] args) {
		DataInputStream in = null;
		File file = new File("primes.bin");
		try {
			in = new DataInputStream(new FileInputStream(file));
			int prime;
			long size = file.length() / 4; // 4 bytes per int
			for (long i = 0; i < size; i++) {
				prime = in.readInt();
				System.out.println(prime);
			}
			in.close();
		} catch (IOException ioe) {
			System.out.println("IO problem: " + ioe);
			ioe.printStackTrace();
		}
	}

}
