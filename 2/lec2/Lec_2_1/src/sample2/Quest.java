package sample2;
/**переопределение конструктора*/

public class Quest {
	private int id;

	private String text;

	// конструктор без параметров (по умолчанию)

	public Quest() {

		super();/*
				 * если класс будет объявлен без конструктора, то
				 * 
				 * компилятор предоставит его именно в таком виде
				 */
		System.out.println("Вызван конструктор без параметров!!!");

	}

	// конструктор с параметрами

	public Quest(int idc, String txt) {

		super();/*
				 * вызов конструктора суперкласса явным образом
				 * 
				 * необязателен, компилятор вставит его автоматически
				 */		
		id = idc;
		text = txt;
		System.out.println("Вызван конструктор c параметрами!!!");
		System.out.println(id + " " + text);

	}
}
