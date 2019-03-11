package org.davehaws.tictactoe;

import java.security.InvalidParameterException;

public class TicTacToe {
	public enum Mark {BLANK, X, O};
	
	private Mark player = Mark.BLANK;
	private int last_row = 0;
	private int last_col = 0;
	
	public Mark getMark(int row, int col) {
		if (player == Mark.BLANK) {
			return Mark.BLANK;
		}
		return (player == Mark.X ? Mark.O : Mark.X);
	}

	public void move(int row, int col) {
		if (row < 1 || row > 3) {
			throw new InvalidParameterException("Row can only be 1, 2, or 3. Tried to use row " + row);
		}
		if (col < 1 || col > 3) {
			throw new InvalidParameterException("Column can only be 1, 2, or 3. Tried to use column " + col);
		}
		if (last_row == row && last_col == col) {
			throw new InvalidParameterException("Location (" + row + ", " + col + ") is already taken.");
		}
		last_row = row;
		last_col = col;
		player = (player == Mark.O ? Mark.X : Mark.O);
	}

}
