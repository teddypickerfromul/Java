/*
 * Класс для хранения и изменения формы приложения (view)
 * TODO : Переписать в соответствии с MVC, отделив полностью модель от представления
 * TODO : Реализовать поддержку "ленивой" загрузки файлового дерева и кэширования
 * TODO : Убрать hardcoded поля и переменные  
 */

package org.fstree;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class FileTreeForm extends JFrame {

	private String windowTitle;

	FileTreeForm(FileTreeModel model) {
		super();

		MyJtree tree = new MyJtree();
		tree.setModel(model);

		JScrollPane scrollpane = new JScrollPane(tree);

		JFrame frame = new JFrame(setDefaultWindowTitle());
		ResolutionProperties resprops = new ResolutionProperties();
		resprops.setappDimensionX(400);
		resprops.setappDimensionY(600);
		frame.setLocation((int) resprops.getappDimensionX(),
				(int) resprops.getappDimensionY());
		frame.getContentPane().add(scrollpane, "Center");
		frame.setSize(400, 600);
		frame.setVisible(true);
	}

	private String setDefaultWindowTitle() {
		this.windowTitle = "File tree viewer";
		return this.windowTitle;
	}

	public void show() {
		this.setVisible(true);
	}
}