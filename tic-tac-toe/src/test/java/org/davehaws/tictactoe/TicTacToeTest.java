package org.davehaws.tictactoe;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.security.InvalidParameterException;

import org.davehaws.tictactoe.TicTacToe.Mark;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeTest {
	private TicTacToe game;

	@Before
	public void testSetup() {
		game = new TicTacToe();
	}

	@Test
	public void new_game_should_have_blank_board() {
		for (int row = 1; row < 4; row++) {
			for (int col = 1; col < 4; col++) {
				assertThat(game.getMark(row, col), is(Mark.BLANK));
			}
		}
	}
	
	@Test
	public void first_move_should_be_X() {
		game.move(2, 2);

		assertThat(game.getMark(2, 2), is(Mark.X));
	}
	
	@Test
	public void second_move_should_be_O() {
		game.move(1, 1);
		game.move(2, 2);

		assertThat(game.getMark(2, 2), is(Mark.O));
	}
	
	@Test(expected=InvalidParameterException.class)
	public void when_player_tries_to_move_to_a_cell_with_a_mark_should_throw_exception() {
		game.move(1, 1);
		game.move(1, 1);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void when_player_tries_a_row_of_less_than_1_should_throw_exception() {
		game.move(0, 1);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void when_player_tries_a_column_of_less_than_1_should_throw_exception() {
		game.move(1, 0);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void when_player_tries_a_row_greater_than_3_should_throw_exception() {
		game.move(4, 1);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void when_player_tries_a_col_greater_than_3_should_throw_exception() {
		game.move(1, 4);
	}
}
