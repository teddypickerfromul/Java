import java.util.*;

/** пример # 1 : типы атрибутов и переменных: Sample.java */

public class Sample {

	private int x; // переменная экземпляра класса

	private int y = 71; // переменная экземпляра класса

	public final int CURRENT_YEAR = 2007; // константа

	protected static int bonus; // переменная класса

	static String version = "Java SE 6"; // переменная класса

	protected Calendar now;

	public int method(int z) { // параметр метода

		z++;

		int a; // локальная переменная метода

		// a++; // ошибка компиляции, значение не задано

		a = 4; // инициализация

		a++;

		now = Calendar.getInstance();// инициализация

		return a + x + y + z;

	}

	public static void main(String[] args) {
		Sample s = new Sample();
		System.out.print(s.method(10));
	}

}
