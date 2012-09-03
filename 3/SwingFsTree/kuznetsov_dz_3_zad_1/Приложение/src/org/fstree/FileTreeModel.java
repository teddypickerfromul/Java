/*
 * Реализация интерфейса модели данных для файлового дерева
 * http://docs.oracle.com/javase/1.4.2/docs/api/javax/swing/tree/TreeModel.html
 */

package org.fstree;

import java.io.File;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

class FileTreeModel implements TreeModel {
	// Корень файлового дерева (модели)
	protected File root;

	public FileTreeModel(File root) {
		this.root = root;
	}

	// Метод, возвращающий JTree обьект - корень
	public Object getRoot() {
		return root;
	}

	// Является ли обьект листом дерева
	public boolean isLeaf(Object node) {
		return ((File) node).isFile();
	}

	// Возвращаем число узлов дерева
	public int getChildCount(Object parent) {
		String[] children = ((File) parent).list();
		if (children == null)
			return 0;
		return children.length;
	}

	// Возвращаем j, обьект(файл) узел дерева от заданного родителя и заданному
	// индексу в Jtree
	public Object getChild(Object parent, int index) {
		String[] children = ((File) parent).list();
		if ((children == null) || (index >= children.length))
			return null;
		return new File((File) parent, children[index]);
	}

	// Возвращаем индекс заданного узла
	public int getIndexOfChild(Object parent, Object child) {
		String[] children = ((File) parent).list();
		if (children == null)
			return -1;
		String childname = ((File) child).getName();
		for (int i = 0; i < children.length; i++) {
			if (childname.equals(children[i]))
				return i;
		}
		return -1;
	}

	// Дерево нередактируемое
	public void valueForPathChanged(TreePath path, Object newvalue) {
	}

	// поэтому можно забить на обработчики соответсвующих событий
	public void addTreeModelListener(TreeModelListener l) {
	}

	public void removeTreeModelListener(TreeModelListener l) {
	}
}
