package org.davehaws.tictactoe;

public class TicTacToe {
	public enum Mark {BLANK, X, O};
	
	private Mark player = Mark.BLANK;
	public Mark getMark(int row, int col) {
		if (player == Mark.BLANK) {
			return Mark.BLANK;
		}
		return (player == Mark.X ? Mark.O : Mark.X);
	}

	public void move(int row, int col) {
		player = (player == Mark.O ? Mark.X : Mark.O);
	}

}
