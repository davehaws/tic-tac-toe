package org.davehaws.tictactoe;

import static org.junit.Assert.*;

import org.davehaws.tictactoe.TicTacToe.Mark;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TicTacToeTest {

	@Test
	public void new_game_should_have_blank_board() {
		TicTacToe game = new TicTacToe();
		
		for (int row = 1; row < 4; row++) {
			for (int col = 1; col < 4; col++) {
				assertThat(game.getMark(row, col), is(Mark.BLANK));
			}
		}
	}
	
	@Test
	public void first_move_should_be_X() {
		TicTacToe game = new TicTacToe();
		
		game.move(2, 2);
		assertThat(game.getMark(2, 2), is(Mark.X));
	}
	
	@Test
	public void second_move_should_be_O() {
		TicTacToe game = new TicTacToe();
		
		game.move(1, 1);
		game.move(2, 2);
		assertThat(game.getMark(2, 2), is(Mark.O));
		
	}
}
