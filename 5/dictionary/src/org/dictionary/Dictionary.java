/*
 * Потоко ОПАСНЫЙ!
 */

/*
 * TODO:Починить кодировку
 */

package org.dictionary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class Dictionary {
	// private static Dictionary instance;
	private TreeSet<Word> DictionaryTree;
	private char delimiter;

	private Dictionary(String dictfile, char delimiter)
			throws FileNotFoundException, IOException {
		this.delimiter = delimiter;
		this.initDictionaryTree();
		this.loadFromFile(dictfile, delimiter);
	}

	public void loadFromFile(String dictfile, char delimiter)
			throws FileNotFoundException, IOException {

		FileReader fr = new FileReader(dictfile);

		int position = 0;
		StringBuffer buffer = new StringBuffer();

		while ((position = fr.read()) != -1) {
			char symbol = (char) position;
			if (symbol != delimiter) {
				buffer.append(symbol);
			} else {
				Word word = new Word(buffer.toString());
				/* word.updateWord(buffer.toString()); */
				this.addWord(word);
				buffer.delete(0, buffer.length());
			}
		}
	}

	private Dictionary() {
		this.initDictionaryTree();
	}

	public String getDelimiter() {
		return delimiter;
	}

	private void initDictionaryTree() {
		this.DictionaryTree = new TreeSet<Word>(new WordsComparator());
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public boolean addWord(Word item) {
		return this.DictionaryTree.add(item);
	}

	public boolean removeWord(Word item) {
		return this.DictionaryTree.remove(item);
	}

	public void clearAllWords() {
		this.DictionaryTree.clear();
	}

	public boolean isContainsWord(Word item) {
		return this.DictionaryTree.contains(item);
	}

	public boolean isClear() {
		return this.isClear();
	}

	public int getSize() {
		return this.DictionaryTree.size();
	}

	// TODO: Возврат Word

	public Iterator iterator() {
		return this.DictionaryTree.iterator();
	}

	public Word wordIterator() {
		return (Word) this.DictionaryTree.iterator();
	}

	public boolean addAll(Collection c) {
		return this.DictionaryTree.addAll(c);
	}

	public boolean removeAll(Collection c) {
		return this.DictionaryTree.removeAll(c);
	}

	public Word first() {
		return this.DictionaryTree.first();
	}

	public Word last() {
		return this.DictionaryTree.last();
	}

	/*
	 * public static synchronized Dictionary getInstance() { if(instance ==
	 * null) { instance = new Dictionary(); } else { return instance; } }
	 */

	public void printAll() {
		Iterator i = this.iterator();
		while (i.hasNext()) {
			Word element = (Word) i.next();
			System.out.println(element.getWord());
		}
		System.out.println(this.getSize());
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		Dictionary dict = new Dictionary("/home/teddy/dic.txt", ';');
		dict.printAll();
	}
}
