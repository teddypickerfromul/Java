package sample8;

public class Circle implements IShape {

	private double r;

	public Circle(double r) {

		this.r = r;

	}

	public double getSquare() {// площадь круга

		return Math.PI * Math.pow(r, 2);

	}

	public double getPerimeter() {

		return 2 * Math.PI * r;

	}

	@Override
	public String getName() {
		
		return "Круг";
	}
}
