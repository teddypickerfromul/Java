
public class QuadParams {
	private double A;
	private double B;
	private double C;
	protected boolean isComputable;
	
	QuadParams() {
		this.A = 1;
		this.B = 0;
		this.C = 0;
		this.isComputable = false;
	}
	
	QuadParams(double A, double B, double C) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.isComputable = false;
	}
	
	public double getA() {
		return this.A;
	}
	
	public double getB() {
		return this.B;
	}

	public double getC() {
		return this.C;
	}
	
	public void setA(double A) {
		if (A != 0) {
			this.A = A;
		} else {
			this.isComputable = false;
		}
		
	}
	
	public void setB(double B) {
		this.B = B;
	}
	
	public void setC(double C) {
		this.C = C;
	}
	
	public boolean check() {
		if ((B*B - 4*A*C)<0) {
			this.isComputable = false;
			return false;
		} else {
			this.isComputable = true;
			return true;
		}
	}
	
}

