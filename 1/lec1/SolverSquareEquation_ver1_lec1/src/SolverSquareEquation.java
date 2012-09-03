import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolverSquareEquation {
	public static void main(String[] args) throws IOException {
		int numberOfRoots = 3;
		double a, b, c, x1 = 0, x2 = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("a=");
		a = Double.parseDouble(br.readLine());
		System.out.print("b=");
		b = Double.parseDouble(br.readLine());
		System.out.print("c=");
		c = Double.parseDouble(br.readLine());
		if (a != 0) {			
			double d = b * b - 4 * a * c;
			if (d < 0) {
				numberOfRoots = 0;
			} else if (d == 0) {
				numberOfRoots = 1;
				x1 = -b / (2 * a);
			} else if (d > 0) {
				numberOfRoots = 2;
				x1 = (-b - Math.sqrt(d)) / (2 * a);
				x2 = (-b + Math.sqrt(d)) / (2 * a);
			}
		} else if (a == 0) {
			if (b == 0 && c == 0) {
				numberOfRoots = -1;
			} else if (b == 0 && c != 0) {
				numberOfRoots = 0;
			} else if (b != 0 && c != 0) {
				numberOfRoots = 1;
				x1 = -c / b;
			}
		}
		switch (numberOfRoots) {
		case -1: {
			System.out.println("X-любое число.");
			break;
		}
		case 0: {
			System.out.println("Корней нет.");
			break;
		}
		case 1: {
			System.out.println("X1=" + x1);
			break;
		}
		case 2: {
			System.out.println("X1=" + x1 + " X2=" + x2);
			break;
		}
		}
	}
}
