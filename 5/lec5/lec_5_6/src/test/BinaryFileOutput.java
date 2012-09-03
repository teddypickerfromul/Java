package test;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryFileOutput {
	public static void main(String[] args) {
		int[] primes = { 1, 2, 3, 5, 11, 17, 19, 23 };
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream("primes.bin"));
			for (int i = 0; i < primes.length; i++) {
				out.writeInt(primes[i]);
			}
			out.close();
		} catch (IOException ioe) {
			System.out.println("IO problem: " + ioe);
			ioe.printStackTrace();
		}
	}
}
