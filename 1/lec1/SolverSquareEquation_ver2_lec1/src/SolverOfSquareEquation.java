import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolverOfSquareEquation {
	public static void main(String[] args) throws IOException {
		double a, b, c;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("a=");
		a = Double.parseDouble(br.readLine());
		System.out.print("b=");
		b = Double.parseDouble(br.readLine());
		System.out.print("c=");
		c = Double.parseDouble(br.readLine());
		SquareEquation sq = new SquareEquation(a, b, c);
		Solution sol = sq.solve();
		switch (sol.getNumberOfRoots()) {
		case Solution.ANY_NUMBER: {
			System.out.println("X-любое число.");
			break;
		}
		case Solution.NOT_EXIST_ROOTS: {
			System.out.println("Корней нет.");
			break;
		}
		case Solution.ONE_ROOT: {
			System.out.println("X1=" + sol.getX1());
			break;
		}
		case Solution.TWO_ROOTS: {
			System.out.println("X=" + sol.getX1() + " X2=" + sol.getX2());
			break;
		}
		}
	}
}
