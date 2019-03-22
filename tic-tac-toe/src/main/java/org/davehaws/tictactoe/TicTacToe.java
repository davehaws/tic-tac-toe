package org.davehaws.tictactoe;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class TicTacToe {
	public enum Mark {BLANK, X, O};
	public enum State {IN_PROGRESS, X_WON, O_WON, CATS_GAME};
	
	public State state;
	private Mark player;
	
	private Mark[][] board;
	
	public TicTacToe() {
		initializeBlankBoard();
		state = State.IN_PROGRESS;
		player = Mark.BLANK;
	}

	private void initializeBlankBoard() {
		board = new Mark[3][];
		for (int row = 0; row < board.length; row++) {
			board[row] = new Mark[3];
			Arrays.fill(board[row], Mark.BLANK);
		}
	}
	
	public Mark getMark(int row, int col) {
		validateLocationIsValid(row, col);
		row = convertToZeroBased(row);
		col = convertToZeroBased(col);
		
		return board[row][col];
	}

	public void move(int row, int col) {
		validateGameIsInProgress();
		validateLocationIsValid(row, col);
		validateLocationIsAvailable(row, col);

		row = convertToZeroBased(row);
		col = convertToZeroBased(col);
		togglePlayer();
		markBoard(row, col);
		
		setGameState();
	}

	private int convertToZeroBased(int value) {
		return value - 1;
	}

	private void markBoard(int row, int col) {
		board[row][col] = player;
	}

	private void togglePlayer() {
		player = (player == Mark.X ? Mark.O : Mark.X);
	}

	private void validateGameIsInProgress() {
		if(state != State.IN_PROGRESS) {
			throw new IllegalStateException("Game has finished. Can no longer make any moves.");
		}
	}

	private void validateLocationIsAvailable(int row, int col) {
		if (board[row-1][col-1] != Mark.BLANK) {
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
		} else if (allSpacesTaken()) {
			state = State.CATS_GAME;
		}
	}

	private boolean allSpacesTaken() {
		for (int row = 0; row < board.length; row++) {
			if (rowContainsBlanks(board[row])) {
				return false;
			}
		}
		return true;
	}

	private boolean rowContainsBlanks(Mark[] row) {
		for (int col = 0; col < row.length; col++) {
			if (row[col] == Mark.BLANK) {
				return true;
			}
		}
		return false;
	}

	private boolean boardHasWinner() {
		return rowWinnerExists() || columnWinnerExists() || diagonalWinnerExists();
	}

	private boolean diagonalWinnerExists() {
		if (player == board[0][0] && player == board[1][1] && player == board[2][2]) return true;
		if (player == board[0][2] && player == board[1][1] && player == board[2][0]) return true;
		return false;
	}

	private boolean columnWinnerExists() {
		return winnerInCol(0) || winnerInCol(1) || winnerInCol(2);
	}

	private boolean rowWinnerExists() {
		return winnerInRow(0) || winnerInRow(1) || winnerInRow(2);
	}

	private boolean winnerInCol(int col) {
		for (int row = 0; row < 3; row++) {
			if (board[row][col] != player) {
				return false;
			}
		}
		return true;
	}

	private boolean winnerInRow(int row) {
		for (int col = 0; col < 3; col++) {
			if (board[row][col] != player) {
				return false;
			}
		}
		return true;
	}

}
