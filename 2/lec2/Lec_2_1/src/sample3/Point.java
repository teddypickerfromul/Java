package sample3;

public class Point {
	/* объект инициализируется при создании и не изменяется */

	private final double x;

	private final double y;

	public Point(final double xx, final double yy) {

		super();

		x = xx;

		y = yy;

	}

	public double getX() {

		return x;

	}

	public double getY() {

		return y;

	}
}
