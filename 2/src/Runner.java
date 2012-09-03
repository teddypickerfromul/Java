import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {
	public static void main(String[] args) throws IsNegativeException {
		Pair p = new Pair();
		p.setItem1(10);
		p.setItem2(11);
		
		System.out.println("Создаю обьекты типа Pair");
		System.out.println(p.getItem1()+" "+p.getItem2());
		Pair p1 = new Pair(12,13);
		Pair p2 = new Pair(14,15);
		
		Pair result = new Pair();
		result = result.Sum(p1, p2);
		System.out.println(result.getItem1()+" "+result.getItem2());
		
		System.out.println("Создаю обьекты типа Money");
		Money m1 = new Money(11,12);
		Money m2 = new Money(0,45);		
	}

}
