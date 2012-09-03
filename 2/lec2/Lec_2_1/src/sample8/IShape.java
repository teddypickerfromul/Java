package sample8;

public interface IShape extends ILineGroup {
	// int id; // ошибка, если нет инициализации

	// void method(){} /* ошибка, так как абстрактный метод не может иметь тела!*/

	double getSquare(); // объявление метода
	String getName(); // объявление метода
}
