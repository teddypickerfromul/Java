package sample8;

public class Rectangle implements IShape {
	private double a, b;

	public Rectangle(double a, double b) {

		this.a = a;

		this.b = b;

	}

	// реализация метода из интерфейса

	public double getSquare() {// площадь прямоугольника

		return a * b;

	}

	// реализация метода из интерфейса

	public double getPerimeter() {

		return 2 * (a + b);

	}
	// реализация метода из интерфейса
	@Override
	public String getName() {
		
		return "Прямоугольник";
	}
}
