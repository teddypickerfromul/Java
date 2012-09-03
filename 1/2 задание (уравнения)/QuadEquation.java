import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuadEquation {
	public static void main(String[] args) throws NumberFormatException, IOException {
		double a,b,c;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("A = ");
		a = Double.parseDouble(br.readLine());
		System.out.print("B = ");
		b = Double.parseDouble(br.readLine());
		System.out.print("C = ");
		c = Double.parseDouble(br.readLine());
		
		QuadParams params = new QuadParams(a,b,c);		
		if(!(params.check())) {
			System.out.println("Фигню сказал и это печально.");
		} else {
			QuadSolver solver = new QuadSolver();
			solver.Calc(params.getA(), params.getB() ,params.getC());
			System.out.println("Дискриминант = "+solver.getDesc());
			if(solver.getNumberOfRoots() == 1) {
				System.out.println("Тут только один корень");
				System.out.println("Корень  = "+solver.getRoot1());
			} else if(solver.getNumberOfRoots() == 2) {
				System.out.println("Тут 2 корня");
				System.out.println("Корень 1 = "+solver.getRoot1());
				System.out.println("Корень 2 = "+solver.getRoot2());				
			}
		}
	}
}
