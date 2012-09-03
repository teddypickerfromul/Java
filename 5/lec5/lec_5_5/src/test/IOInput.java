package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOInput {
	public static void main(String[] args) {
		BufferedReader keyboard;
		String line;
		try {
			System.out.print("Enter value: ");
			System.out.flush();
			keyboard = new BufferedReader(new InputStreamReader(System.in));
			line = keyboard.readLine();
			System.out.println("value  = " + line);
		} catch (IOException e) {
			System.out.println("Error reading input!");
		}
	}
}
