public class Complex extends DoublePair {
	Complex() {
		this.item1 = 0;
		this.item2 = 0;
	}
	
	Complex(double item1, double item2) {
		this.item1 = item1;
		this.item2 = item2;
	}
	
	public Complex Mult(Complex c1, Complex c2) {
		Complex result = new Complex();
		result.setItem1(c1.getItem1()*c2.getItem1() - c1.getItem2()*c2.getItem2());
		result.setItem2(c1.getItem1()*c2.getItem2() + c1.getItem2()*c2.getItem1());
		return result;
	}
	
	public Complex Sub(Complex c1, Complex c2) {
		Complex result = new Complex();
		result.setItem1(c1.getItem1() - c1.getItem2());
		result.setItem2(c1.getItem1() - c2.getItem2());
		return result;
	}
}
