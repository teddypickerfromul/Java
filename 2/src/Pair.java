public class Pair {
	private int item1;
	private int item2;
	
	Pair() {
		this.setItem1(0);
		this.setItem2(0);
	}
	
	Pair(int it1, int it2) {
		this.setItem1(it1);
		this.setItem2(it2);
	}

	public int getItem1() {
		return item1;
	}

	public void setItem1(int item1) {
		this.item1 = item1;
	}

	public int getItem2() {
		return item2;
	}

	public void setItem2(int item2) {
		this.item2 = item2;
	}
	
	public Pair multOnInt(int x) {
		this.item1 *= x;
		this.item2 *= x;
		return this;
	}
	
	public Pair Sum(Pair p1, Pair p2) {
		Pair result = new Pair(p1.getItem1() + p1.getItem2(), p2.getItem1() + p2.getItem2());
		return result;
	}
	
	public int getElemsSum() {
		return (this.item1 + this.item2);
	}
	
}
