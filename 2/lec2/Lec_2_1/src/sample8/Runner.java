package sample8;

public class Runner {
	public static void printFeatures(IShape f) {

		System.out.printf("название:%s площадь:%.2f периметр: %.2f%n", f.getName(),	f.getSquare(), f.getPerimeter());

	}

	public static void main(String[] args) {

		Rectangle r = new Rectangle(5, 9.95);

		Circle c = new Circle(7.01);

		printFeatures(r);

		printFeatures(c);

	}
}
