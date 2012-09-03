package sample5;

public class Department {
	{

		System.out.println("logic");
	};

	static {

		System.out.println("static logic");

	}

	private int id = 7;

	public Department(int d) {

		id = d;

		System.out.println("конструктор");
	}

	int getId() {

		return id;

	}

	{

		id = 10;
		System.out.println("logic");
	}

}
