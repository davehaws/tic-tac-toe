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
		if (row < 1) {
			throw new InvalidParameterException("Row should be at least 1. Tried to move to row " + row);
		}
		if (last_row == row && last_col == col) {
			throw new InvalidParameterException("Location (" + row + ", " + col + ") is already taken.");
		}
		last_row = row;
		last_col = col;
		player = (player == Mark.O ? Mark.X : Mark.O);
	}

}
