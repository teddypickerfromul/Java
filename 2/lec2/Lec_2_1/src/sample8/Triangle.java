package sample8;

public abstract class Triangle implements IShape {

	private double a, b, c;

	public Triangle(double a, double b, double c) {

		this.a = a;

		this.b = b;

		this.c = c;

	}

	public double getPerimeter() {

		return a + b + c;

	}
}
