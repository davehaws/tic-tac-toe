package org.davehaws.tictactoe;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.security.InvalidParameterException;

import org.davehaws.tictactoe.TicTacToe.Mark;
import org.davehaws.tictactoe.TicTacToe.State;
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
	
	@Test
	public void when_game_first_starts_the_game_is_in_progress() {
		assertThat(game.getGameState(), is(State.IN_PROGRESS));
	}
	
	@Test(expected=InvalidParameterException.class)
	public void when_checking_for_marks_out_of_range_should_throw_exception() {
		game.getMark(0, 1);
	}
	
	@Test
	public void when_top_row_is_all_x_then_x_wins() throws Exception {
		game.move(1, 1);
		game.move(2, 1);
		game.move(1, 2);
		game.move(2, 2);
		game.move(1, 3);
		assertThat(game.getGameState(), is(State.X_WON));
	}
	
	@Test
	public void when_middle_row_is_all_x_then_x_wins() throws Exception {
		game.move(2, 1);
		game.move(1, 1);
		game.move(2, 2);
		game.move(3, 2);
		game.move(2, 3);
		assertThat(game.getGameState(), is(State.X_WON));
	}
	
	@Test
	public void when_top_row_is_all_o_then_o_wins() throws Exception {
		game.move(2, 1);
		game.move(1, 1);
		game.move(2, 2);
		game.move(1, 2);
		game.move(3, 3);
		game.move(1, 3);
		assertThat(game.getGameState(), is(State.O_WON));
	}
	
	@Test(expected=IllegalStateException.class)
	public void when_game_is_won_and_another_move_is_made_should_throw_exception() throws Exception {
		game.move(1, 1);
		game.move(2, 1);
		game.move(1, 2);
		game.move(2, 2);
		game.move(1, 3);
		game.move(2, 3);
	}
}
