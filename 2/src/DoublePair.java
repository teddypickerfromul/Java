public class DoublePair {
	protected double item1;
	protected double item2;
	
	DoublePair() {
		this.item1 = 0;
		this.item2 = 0;
	}
	
	DoublePair(double item1, double item2) {
		this.item1 = item1;
		this.item2 = item2;		
	}
	
	public double getItem1() {
		return item1;
	}
	
	public void setItem1(double item1) {
		this.item1 = item1;
	}
	
	public double getItem2() {
		return item2;
	}
	
	public void setItem2(double item2) {
		this.item2 = item2;
	}
	
	public DoublePair Sum(DoublePair p1, DoublePair p2) {
		DoublePair result = new DoublePair(p1.getItem1() + p1.getItem2(), p2.getItem1() + p2.getItem2());
		return result;
	}
	
	public DoublePair Mult(double x) {
		this.item1 *= x;
		this.item2 *= x;
		return this;
	}
	
}
