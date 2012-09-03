public class Solution {
	private int numberOfRoots;
	private double x1;
	private double x2;

	final static int ANY_NUMBER = -1;
	final static int NOT_EXIST_ROOTS = 0;
	final static int ONE_ROOT = 1;
	final static int TWO_ROOTS = 2;

	public Solution(boolean anyRoots) {
		numberOfRoots = (anyRoots) ? ANY_NUMBER : NOT_EXIST_ROOTS;
	}

	public Solution(double x1, double x2) {
		numberOfRoots = TWO_ROOTS;
		this.x1 = x1;
		this.x2 = x2;
	}

	public Solution(double x1) {
		numberOfRoots = ONE_ROOT;
		this.x1 = x1;
	}

	public int getNumberOfRoots() {
		return numberOfRoots;
	}

	public double getX1() {
		if (numberOfRoots == ONE_ROOT || numberOfRoots == TWO_ROOTS) {
			return x1;
		} else {
			throw new Error("Sorry, X1 IS NOT EXIST OR ANY NUMBER.");
		}
	}

	public double getX2() {
		if (numberOfRoots == TWO_ROOTS) {
			return x2;
		} else {
			throw new Error("Sorry, X2 IS NOT EXIST OR ANY NUMBER.");
		}
	}
}
