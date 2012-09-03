package sample6;
//рядовой сотрудник
public class Employee {
	private int id;

	public Employee(int idc) {

		super();/*
				 * по умолчанию, необязательный явный вызов
				 * 
				 * конструктора суперкласса
				 */

		id = idc;

	}

	public int getId() {

		return id;

	}

	public void typeEmployee() {

		// ...

		System.out.println("Работник");

	}
}
