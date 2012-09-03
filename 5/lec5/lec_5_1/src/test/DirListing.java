package test;

import java.io.File;

public class DirListing {
	public static void main(String[] args) {
		File dir = new File(System.getProperty("user.dir"));
		if (dir.isDirectory()) {
			System.out.println("Directory of " + dir);
			String[] listing = dir.list();
			for (int i = 0; i < listing.length; i++) {
				System.out.println("\t" + listing[i]);
			}
		}
	}
}
