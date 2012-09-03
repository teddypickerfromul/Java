public class Money extends Pair {
	private int rub;
	private int cop;
	
	Money() {
		this.rub = 0;
		this.cop = 0;
	}
	
	Money(int rub, int cop) throws IsNegativeException {
		this.setRub(rub);
		this.setCop(cop);
	}
	
	public int getRub() {
		return rub;
	}
	
	public void setRub(int rub) throws IsNegativeException {
		if (rub < 0 ) {
			throw new IsNegativeException("Отрицательное значение недопустимо!");
		} else {
			this.rub = rub;
		}
	}
	
	public int getCop() {
		return cop;
	}
	
	public void setCop(int cop) throws IsNegativeException {
		if (cop < 0) {
			throw new IsNegativeException("Отрицательное значение недопустимо!");
		} else {
			this.cop = cop;
		}	
	}

	public Money Sum(Money m1, Money m2) throws IsNegativeException {
		Money result = new Money();
		if ((m1.getCop() + m2.getCop()) > 99) {
			result.setRub(m1.getRub() + m2.getRub() + 1);
			result.setCop((m1.getCop() + m2.getCop()) % 100);
			return result;
		} else {
			result.setRub(m1.getRub() + m2.getRub());
			result.setCop(m1.getCop() + m2.getCop());
			return result;
		}
	}
	
	public Money Sub(Money m1, Money m2) throws IsNegativeException {
		Money result = new Money();
		if( (m1.getRub()*100 + m1.getCop()) < (m2.getRub()*100 + m2.getCop()) ) {
			throw new IsNegativeException("Отрицательное значение недопустимо!");
		} else {
				result.setRub( (m1.getRub()*100+m1.getCop()) - (m2.getCop()+m2.getRub()*100) / 100 );
				result.setCop( (m1.getRub()*100+m1.getCop()) - (m2.getCop()+m2.getRub()*100) % 100 );
				return result;
			}
	}
	
	public Money Mult(Money m1, Money m2) throws IsNegativeException {
		Money result = new Money();
		result.setRub(m1.getRub()*m2.getRub()+(m1.getCop()*m2.getCop()) / 100);
		result.setCop((m1.getCop()*m2.getCop()) % 100);
		return result;
	}
}
