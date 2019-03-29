package org.davehaws.tictactoe;

import java.util.Arrays;
import java.util.stream.Stream;

public class Board {
	public enum Mark {BLANK, X, O};
	private Mark[] cells;
	
	public Board() {
		cells = new Mark[9];
		Arrays.fill(cells, Mark.BLANK);
	}

	public Stream<Mark> getCells() {
		return Arrays.stream(cells);
	}

}
