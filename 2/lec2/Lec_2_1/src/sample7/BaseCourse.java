package sample7;

public class BaseCourse extends Course {
	private int idTeacher;

	public BaseCourse(int i, String n, int it) {

		super(i, n);

		idTeacher = it;

	}

	@Override
	public String toString() {

		/*
		 * просто toString() нельзя!!!
		 * 
		 * метод будет вызывать сам себя, что
		 * 
		 * приведет к ошибке во время выполнения
		 */

		return super.toString() + " препод.(" + idTeacher + ")";

	}
}
