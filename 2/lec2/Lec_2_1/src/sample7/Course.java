package sample7;

public class Course {
	private int id;

	private String name;

	public Course(int i, String n) {

		id = i;

		name = n;

	}

	@Override
	public String toString() {
		return "Название: " + name + "(" + id + ")";
	}
}
