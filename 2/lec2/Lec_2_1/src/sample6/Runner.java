package sample6;

/**
 * наследование класса и переопределение метода:
 * 
 * Employee.java: Manager.java
 */
public class Runner {
	public static void main(String[] args) {

		Employee b1 = new Employee(7110);

		Employee b2 = new Manager(9251, 31);

		b1.typeEmployee();// вызов версии из класса Employee

		b2.typeEmployee();// вызов версии из класса Manager

		// b2.getIdProject();// ошибка компиляции!!!

		((Manager) b2).getIdProject();

		Manager b3 = new Manager(9711, 35);

		System.out.println(b3.getIdProject());// 35

		System.out.println(b3.getId());// 9711

	}
}
