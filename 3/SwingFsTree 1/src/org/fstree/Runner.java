/*
 * TODO : Реализовать проверку корректности пути к корню дерева и сделать обработку ошибочного пути
 * TODO : Реализовать поддержку других механизмов передачи параметра в модель данных дерева
 * типа системных переменных и прочего 
 */

package org.fstree;

import java.io.File;

public class Runner {
	public static void main(String[] args) {
		// Обьект - корень файлового дерева 
		File root;
		if (args.length > 0)									// Если запущено с аргументами, то используем первый как корень файлового дерева
			root = new File(args[0]);
		else
			root = new File(System.getProperty("user.home"));	// Иначе используем системный домашний каталог

		// Создаём TreeModel обьект для представления модели данных файлового дерева
		// используемого для реализации виджета JTree
		FileTreeModel model = new FileTreeModel(root);
		FileTreeForm form = new FileTreeForm(model);
	}

}
