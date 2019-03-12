package org.davehaws.tictactoe;

import java.security.InvalidParameterException;

public class TicTacToe {
	public enum Mark {BLANK, X, O};
	public enum State {NO_STATE, IN_PROGRESS, X_WON};
	
	public State state = State.NO_STATE;
	private Mark player = Mark.BLANK;
	private int last_row = 0;
	private int last_col = 0;
	
	private Mark[][] board;
	
	public TicTacToe() {
		board = new Mark[4][];
		for (int row = 1; row < board.length; row++) {
			board[row] = new Mark[4];
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = Mark.BLANK;
			}
		}
	}
	
	public Mark getMark(int row, int col) {
		return board[row][col];
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
		player = (player == Mark.X ? Mark.O : Mark.X);
		board[row][col] = player;
	}

	public State state() {
		boolean winnerIsX = true;
		for (int col = 1; col < 4; col++) {
			if (board[1][col] != Mark.X) {
				winnerIsX = false;
				break;
			}
		}
		return winnerIsX ? State.X_WON : State.IN_PROGRESS;
	}

}
