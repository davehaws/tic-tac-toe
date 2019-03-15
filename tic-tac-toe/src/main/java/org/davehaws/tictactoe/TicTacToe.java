package org.davehaws.tictactoe;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class TicTacToe {
	public enum Mark {BLANK, X, O};
	public enum State {IN_PROGRESS, X_WON, O_WON};
	
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
			Arrays.fill(board[row], Mark.BLANK);
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
		if (boardHasWinner()) {
			state = (player == Mark.X) ? State.X_WON : State.O_WON;
		}
	}

	private boolean boardHasWinner() {
		if (winnerInRow(1)) return true;
		if (winnerInRow(2)) return true;
		if (winnerInRow(3)) return true;
		
		if (winnerInCol(1)) return true;
		if (winnerInCol(2)) return true;
		if (winnerInCol(3)) return true;

		return false;
	}

	private boolean winnerInCol(int col) {
		for (int row = 1; row < 4; row++) {
			if (board[row][col] != player) {
				return false;
			}
		}
		return true;
	}

	private boolean winnerInRow(int row) {
		for (int col = 1; col < 4; col++) {
			if (board[row][col] != player) {
				return false;
			}
		}
		return true;
	}

}
