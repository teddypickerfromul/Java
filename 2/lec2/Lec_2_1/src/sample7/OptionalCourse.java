package sample7;

public class OptionalCourse extends BaseCourse {
	private boolean required;

	public OptionalCourse(int i, String n, int it, boolean r) {

		super(i, n, it);

		required = r;

	}

	@Override
	public String toString() {
		return super.toString() + " required->" + required;

	}
}
