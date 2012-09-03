import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sample {

	BufferedReader br;

	public Sample() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	private double readTypeDoubleConsole() throws NumberFormatException,
			IOException {
		/** Читаем из консоли Integer */
		System.out.print("Введите число = ");
		double d = Double.parseDouble(br.readLine());
		return d;
	}

	private int readTypeIntConsole() throws NumberFormatException, IOException {
		/** Читаем из консоли Double */
		System.out.print("Введите число = ");
		return Integer.parseInt(br.readLine());
	}

	public void test_sum_int() throws NumberFormatException, IOException {
		int value1;
		int value2;
		int sum;
		value1 = readTypeIntConsole();
		value2 = readTypeIntConsole();
		sum = value1 + value2;
		System.out.print("Сумма равна = ");
		System.out.print(sum);
	}

	public void test_sum_double() throws NumberFormatException, IOException {
		double value1;
		double value2;
		double sum;
		value1 = readTypeDoubleConsole();
		value2 = readTypeDoubleConsole();
		sum = value1 + value2;
		System.out.print("Сумма равна = ");
		System.out.print(sum);
	}

	public void typeShowData() {
		// byte, short, int, long, char — целочисленные
		// float, double — дробные
		// boolean — булевый.
		System.out.println("Тип byte размерность");
		System.out.println("Минимальное - " + Byte.MIN_VALUE
				+ " Максимальное - " + Byte.MAX_VALUE);
		System.out.println("Тип short размерность");
		System.out.println("Минимальное - " + Short.MIN_VALUE
				+ " Максимальное - " + Short.MAX_VALUE);
		System.out.println("Тип int размерность");
		System.out.println("Минимальное - " + Integer.MIN_VALUE
				+ " Максимальное - " + Integer.MAX_VALUE);
		System.out.println("Тип long размерность");
		System.out.println("Минимальное - " + Long.MIN_VALUE
				+ " Максимальное - " + Long.MAX_VALUE);
		System.out.println("Тип float размерность");
		System.out.println("Минимальное - " + Float.MIN_VALUE
				+ " Максимальное - " + Float.MAX_VALUE);
		System.out.println("Тип double размерность");
		System.out.println("Минимальное - " + Double.MIN_VALUE
				+ " Максимальное - " + Double.MAX_VALUE);
		System.out.println("Тип boolean значение");
		System.out.println("Верно - " + Boolean.TRUE + " Ложь - "
				+ Boolean.FALSE);
	}

	public void max_value() throws NumberFormatException, IOException {
		int value1;
		int value2;
		int max_value;

		value1 = readTypeIntConsole();
		value2 = readTypeIntConsole();

		if (value1 > value2)
			max_value = value1;
		else
			max_value = value2;

		System.out.println("Максимальное значнеие = " + max_value);

	}

	public void max_value2() throws NumberFormatException, IOException {
		/** Короткая запись условия */

		int value1;
		int value2;
		int max_value;

		value1 = readTypeIntConsole();
		value2 = readTypeIntConsole();
		max_value = value1 > value2 ? value1 : value2;

		System.out.println("Максимальное значнеие = " + max_value);

	}

	public void test_while() {
		int i;
		i = 1;
		while (i <= 10) {
			System.out.print(i + " ");
			i = i + 1;
		}

	}

	public void test_complex() throws NumberFormatException, IOException {
		/** Проверка на простоту числа */
		int num;

		num = readTypeIntConsole();

		boolean is_complex = false;

		int div = num - 1;

		while (div >= 2) {
			if (num % div == 0) {
				is_complex = true;
				System.out.println("LOG: " + div);
			}
			div--;
		}

		if (is_complex) {
			System.out.println("Число " + num + " составное!");
		} else {
			System.out.println("Число " + num + " простое!");
		}

	}

	public void test_do_while() {

		/** факториал */
		int n = 6;
		int f = 1;
		int i = 1;

		do {
			f = f * i;
			i = i + 1;
			System.out.println("LOG: f = " + f + " i = " + i);
		} while (i <= n);

		System.out.println(n + " ! = " + f);

	}

	public void test_finish_in_zero() throws NumberFormatException, IOException {
		int sum = 0;
		int val;
		System.out.println("Окончание ввода число 0");
		do {
			val = readTypeIntConsole();
			sum += val;
		} while (val != 0);
		System.out.print("Сумма равна = " + sum);
	}

	public void test13() {
		/** степень числа 2 */
		long val = 1, prev_val = 0;
		int i = 0;

		while (val > prev_val) {
			System.out.println(val + "  " + i);
			val = val << 1;
			i++;
		}

	}

	public void test14() throws NumberFormatException, IOException {
		/* разложить на множители */
		int num;

		num = readTypeIntConsole();

		System.out.println(num + " = 1 ");

		int div = 2;
		while (num > 1) {
			if (num % div == 0) {
				System.out.println(" * " + div);
				num /= div;
			} else
				div++;

		}

	}
}
