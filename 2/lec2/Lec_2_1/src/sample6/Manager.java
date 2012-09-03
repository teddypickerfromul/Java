package sample6;

//сотрудник с проектом, за который он отвечает
public class Manager extends Employee {
	private int idProject;

	public Manager(int idc, int idp) {

		super(idc); /*
					 * вызов конструктора суперкласса
					 * 
					 * с параметром
					 */

		idProject = idp;

	}

	public int getIdProject() {

		return idProject;

	}

	@Override
	public void typeEmployee() {
		// ...

		System.out.println("Менеджер");

	}
}
