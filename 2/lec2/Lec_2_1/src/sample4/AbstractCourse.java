package sample4;

public abstract class AbstractCourse {
	private String name;

	public AbstractCourse() {
	}

	public abstract void changeTeacher(int id);/*
												 * определение
												 * 
												 * метода отсутствует
												 */

	public void setName(String n) {
		name = n;
	}
}
