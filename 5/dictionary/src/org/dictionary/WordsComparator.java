package org.dictionary;

import java.util.Comparator;

public class WordsComparator implements Comparator<Word> {
	public int compare(Word a, Word b) {
		String aStr, bStr;
		aStr = a.getString();
		bStr = b.getString();
		return bStr.compareTo(aStr);
	}
	public String toString(Word a) {
		return a.getWord();
	}
}
