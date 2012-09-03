
public class QuadSolver {
	private double root1;
	private double root2;
	private double D;
	private short numberOfRoots;
	private boolean isSquare;
	
	QuadSolver() {
		this.root1 = 0;
		this.root2 = 0;
		this.numberOfRoots = 0;
		this.isSquare = true;
	}
	
	public double getRoot1() {
		return this.root1;
	}
	
	public double getRoot2() {
		return this.root2;
	}
	
	public short getNumberOfRoots() {
		return this.numberOfRoots;
	}
	
	public double getDesc() {
		return this.D; 
	}
	
	public void calcDes(double A, double B, double C) {
		this.D = B*B - 4*A*C;
		if(A != 0) {
			this.isSquare = true;
			if (D != 0 ) {
				this.numberOfRoots = 2;
			} else if (D == 0) {
				this.numberOfRoots = 1;
			} else {
				this.numberOfRoots = 0;
			}
		} else {
			this.isSquare = false;
			this.numberOfRoots = 1;
		}
	}
	
	public boolean Calc(double A, double B, double C) {
		this.calcDes(A, B, C);
		if(this.numberOfRoots == 1) {
			if(this.isSquare) {
				this.root1 = this.root2 = (-(B/(2*A)));
				return true;
			} else {
				this.root1 = this.root2 = (-C/B);
				return true;
			}
		} else if (this.numberOfRoots == 2) {
			this.root1 = ((-B - Math.sqrt(D))/(2 * A));
			this.root2 = ((-B + Math.sqrt(D))/(2 * A));
			return true;
		} else {
			return false;
		}
	}
}
