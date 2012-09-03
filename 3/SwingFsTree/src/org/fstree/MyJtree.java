/*
 * Делаем для удобства наследника класса виджета JTree c обработчиком события выделения узла дерева
 * чтобы реализовать диалоговое окно с абсолютным путём к выделенному файлу
 */

// TODO : переделать в соответсвии с MVC

package org.fstree;

import java.io.File;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class MyJtree extends JTree {
	public MyJtree() {
		super();
		// добавляем обработку события выделеня узла дерева
		this.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				// смотрим на выделенный узел дерева
				Object object = e.getPath().getLastPathComponent();
				// проверяем файловый обьект ли находится в данном узле дереве
				if (object instanceof File) {
					File fileObject = (File) object;
					System.out.println("Path :" + fileObject.getAbsolutePath());
					// Делаем разные заголовки диалоговых окон
					if (fileObject.isDirectory()) {
						PathMessage msg = new PathMessage(fileObject
								.getAbsolutePath(), "dir");
					}
					if (fileObject.isFile()) {
						PathMessage msg = new PathMessage(fileObject
								.getAbsolutePath(), "file");
					}
				} else {
					System.out.println("You selected something wrong");
				}
			}
		});
	}
}
