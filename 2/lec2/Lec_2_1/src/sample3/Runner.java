package sample3;

public class Runner {

	public static void main(String[] args) {

		// локальные переменные не являются членами класса

		Point t1 = new Point(5, 10);

		Point t2 = new Point(2, 6);

		System.out.print("расстояние равно : " + new LocateLogic().calculateDistance(t1, t2));

	}
}
