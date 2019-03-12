package org.davehaws.tictactoe;

import java.security.InvalidParameterException;

public class TicTacToe {
	public enum Mark {BLANK, X, O};
	public enum State {IN_PROGRESS, X_WON};
	
	public State state;
	private Mark player;
	
	private Mark[][] board;
	
	public TicTacToe() {
		initializeBlankBoard();
		state = State.IN_PROGRESS;
		player = Mark.BLANK;
	}

	private void initializeBlankBoard() {
		board = new Mark[4][];
		for (int row = 1; row < board.length; row++) {
			board[row] = new Mark[4];
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = Mark.BLANK;
			}
		}
	}
	
	public Mark getMark(int row, int col) {
		validateLocationIsValid(row, col);
		return board[row][col];
	}

	public void move(int row, int col) {
		if(state != State.IN_PROGRESS) {
			throw new IllegalStateException("Game has finished. Can no longer make any moves.");
		}
		validateLocationIsValid(row, col);
		validateLocationAvailable(row, col);
		
		player = (player == Mark.X ? Mark.O : Mark.X);
		board[row][col] = player;
		setGameState();
	}

	private void validateLocationAvailable(int row, int col) {
		if (board[row][col] != Mark.BLANK) {
			throw new InvalidParameterException("Location (" + row + ", " + col + ") is already taken.");
		}
	}

	private void validateLocationIsValid(int row, int col) {
		if (row < 1 || row > 3) {
			throw new InvalidParameterException("Row can only be 1, 2, or 3. Tried to use row " + row);
		}
		if (col < 1 || col > 3) {
			throw new InvalidParameterException("Column can only be 1, 2, or 3. Tried to use column " + col);
		}
	}

	public State getGameState() {
		return state;
	}

	private void setGameState() {
		boolean winnerIsX = true;
		for (int col = 1; col < 4; col++) {
			if (board[1][col] != Mark.X) {
				winnerIsX = false;
				break;
			}
		}
		if (winnerIsX) {
			state = State.X_WON;
		}
	}

}
