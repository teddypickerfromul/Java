package org.coursework;

public class WordHighlighter {

	Word word;

	public WordHighlighter(Word w) {
		this.word = w;
	}

	public WordHighlighter(String w) {
		this.word = new Word(w);

	}

	public String getHighlighted() {
		String hw = new String();
		hw = "<b><i>" + this.word.getString() + "</i></b>";
		return hw;
	}

}
