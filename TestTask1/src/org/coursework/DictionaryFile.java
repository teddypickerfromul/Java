package org.coursework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class DictionaryFile {
	private TreeSet<Word> DictionaryTree;
	private long wordsNumber;
	private FileReader dictfile;

	public long getWordsNumber() {
		return wordsNumber;
	}

	DictionaryFile(String filename, long maxwords)
			throws FileNotFoundException, IOException, FileFormatException {
		this.clearWordsNumber();
		if (this.wordsNumber > maxwords) {
			throw new FileFormatException("Слишком большой файл словаря");
		}
		this.initDictionaryTree();
		this.loadFromFile(filename);
	}
	
	public void loadFromFile(String filename) throws FileNotFoundException,
			IOException {

		this.dictfile = new FileReader(filename);
		BufferedReader reader = new BufferedReader(this.dictfile);
		String line = null;

		while ((line = reader.readLine()) != null) {
			Word word = new Word(line.trim());
			this.addWord(word);
			this.wordsNumber = this.getSize();
			// word = null;
			// System.gc();
		}
		reader.close();
	}

	public long getLinesNumber(String filename) throws IOException {
		this.dictfile = new FileReader(filename);
		BufferedReader reader = new BufferedReader(this.dictfile);
		long lines = 0;
		while (reader.readLine() != null) {
			lines++;
		}
		reader.close();
		this.wordsNumber = lines;
		return lines;
	}

	private void clearWordsNumber() {
		this.wordsNumber = 0;
	}

	private DictionaryFile() {
		this.initDictionaryTree();
	}

	private void initDictionaryTree() {
		this.DictionaryTree = new TreeSet<Word>(new WordsComparator());
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
		return this.DictionaryTree.isEmpty();
	}

	public int getSize() {
		return this.DictionaryTree.size();
	}

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

	public boolean exists(Word item) {
		return this.DictionaryTree.contains(item);
	}

	public void printAll() {
		Iterator i = this.iterator();
		while (i.hasNext()) {
			Word element = (Word) i.next();
			System.out.println(element.getWord() + " : " + element.getLength()
					+ " : " + element.getHashcode());
		}
		/*System.out.println(this.getSize());*/
	}
}
