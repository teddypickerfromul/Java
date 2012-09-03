import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class hello {
	public static void main(String[] args) throws IOException {
		System.out.println("Введи свое имя сюда:");
		InputStreamReader conv = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(conv);				
		System.out.println("Привет "+in.readLine());
	}
} 
