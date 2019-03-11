package org.davehaws.tictactoe;

public class TicTacToe {
	private boolean playerMoved = false;
	public String getMark(int row, int col) {
		return playerMoved ? "X" : "";
	}

	public void move(int row, int col) {
		playerMoved = true;
	}

}
