public class SquareEquation {
	private double a;
	private double b;
	private double c;

	public SquareEquation(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Solution solve() {
		if (a != 0) {
			double d = b * b - 4 * a * c;
			if (d < 0) {
				return new Solution(false);
			} else if (d == 0) {
				return new Solution(-b / (2 * a));
			} else if (d > 0) {
				return new Solution((-b - Math.sqrt(d)) / (2 * a),
						(-b + Math.sqrt(d)) / (2 * a));
			}
		} else if (a == 0) {
			if (b == 0 && c == 0) {
				return new Solution(true);
			} else if (b == 0 && c != 0) {
				return new Solution(false);
			} else if (b != 0 && c != 0) {
				return new Solution(-c / b);
			}
		}
		return new Solution(false);
	}
}
