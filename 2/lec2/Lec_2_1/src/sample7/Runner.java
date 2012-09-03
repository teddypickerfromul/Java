package sample7;

/* Пример реализации полиморфизма : динамическое связывание методов: Course.java: BaseCourse.java: OptionalCourse.java: DynDispatcher.java */
public class Runner {
	public static void main(String[] args) {

		DynDispatcher d = new DynDispatcher();

		Course cс = new Course(7, "МА");

		d.infoCourse(cс);

		BaseCourse bc = new BaseCourse(71, "МП", 2531);

		d.infoCourse(bc);

		OptionalCourse oc = new OptionalCourse(35, "ФА", 4128, true);

		d.infoCourse(oc);

	}
}
