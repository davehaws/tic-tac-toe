package org.davehaws.tictactoe;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;

import org.davehaws.tictactoe.TicTacToe.Mark;
import org.davehaws.tictactoe.TicTacToe.State;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TicTacToeTest {
	private TicTacToe game = new TicTacToe();

	@Nested
	class When_the_game_begins {
	
		@Test
		public void the_board_should_be_blank() {
			for (int row = 1; row < 4; row++) {
				for (int col = 1; col < 4; col++) {
					assertThat(game.getMark(row, col), is(Mark.BLANK));
				}
			}
		}
		
		@Test
		public void the_game_should_be_in_progress() {
			assertThat(game.getGameState(), is(State.IN_PROGRESS));
		}

		@Test
		public void the_first_move_should_be_X() {
			game.move(2, 2);

			assertThat(game.getMark(2, 2), is(Mark.X));
		}
		
		@Test
		public void the_second_move_should_be_O() {
			game.move(1, 1);
			game.move(2, 2);

			assertThat(game.getMark(2, 2), is(Mark.O));
		}
		
	}

	@Nested
	class A_win_is_declared_when {
		@Test
		public void the_left_column_is_all_Os() throws Exception {
			makeMoves(new int[][] {
				{2,2}, {1,1}, 
				{1,2}, {2,1}, 
				{1,3}, {3,1}
			});
			
			assertThat(game.getGameState(), is(State.O_WON));
		}
		
		@Test
		public void the_middle_column_is_all_Os() throws Exception {
			makeMoves(new int[][] {
				{2,1}, {1,2}, 
				{1,1}, {2,2}, 
				{1,3}, {3,2}
			});
			
			assertThat(game.getGameState(), is(State.O_WON));
		}
		
		@Test
		public void the_right_column_is_all_Os() throws Exception {
			makeMoves(new int[][] {
				{2,2}, {1,3}, 
				{1,2}, {2,3}, 
				{1,1}, {3,3}
			});
			
			assertThat(game.getGameState(), is(State.O_WON));
		}
		
		@Test
		public void the_top_row_is_all_Xs() throws Exception {
			makeMoves(new int[][] {
				{1,1}, {2,1}, 
				{1,2}, {2,2}, 
				{1,3}
			});
			
			assertThat(game.getGameState(), is(State.X_WON));
		}
		
		@Test
		public void the_middle_row_is_all_Xs() throws Exception {
			makeMoves(new int[][] {
				{2,1}, {1,1}, 
				{2,2}, {3,2}, 
				{2,3}
			});
			
			assertThat(game.getGameState(), is(State.X_WON));
		}
		
		@Test
		public void the_bottom_row_is_all_Xs() throws Exception {
			makeMoves(new int[][] {
				{3,1}, {1,1}, 
				{3,2}, {2,2}, 
				{3,3}
			});
			
			assertThat(game.getGameState(), is(State.X_WON));
		}
		
		@Test
		public void the_diagonal_from_topleft_to_bottomright_is_all_Xs() throws Exception {
			makeMoves(new int[][] {
				{1,1}, {1,2}, 
				{2,2}, {2,3}, 
				{3,3}
			});
			
			assertThat(game.getGameState(), is(State.X_WON));
		}
		
		@Test
		public void the_diagonal_from_bottomleft_to_topright_is_all_Os() throws Exception {
			makeMoves(new int[][] {
				{1,1}, {1,3}, 
				{2,3}, {2,2}, 
				{3,3}, {3,1}
			});
			
			assertThat(game.getGameState(), is(State.O_WON));
		}
		
		@Test
		public void the_top_row_is_all_Os() throws Exception {
			makeMoves(new int[][] {
				{2,1}, {1,1}, 
				{2,2}, {1,2}, 
				{3,3}, {1,3}
			});
			
			assertThat(game.getGameState(), is(State.O_WON));
		}
		
	}
	
	@Test
	public void A_game_is_declared_CATS_when_all_locations_are_marked_and_no_winner_exists() {
		makeMoves(new int[][] {
			{2,2}, {1,1}, 
			{1,2}, {3,2}, 
			{3,1}, {1,3},
			{2,3}, {2,1},
			{3,3}
		});
		
		assertThat(game.getGameState(), is(State.CATS_GAME));
	}
	
	@Nested
	class An_error_is_thrown_when {
		@Test
		public void the_game_is_over_and_another_move_is_made() {
			makeMoves(new int[][] {
				{1,1}, {2,1}, 
				{1,2}, {2,2}, 
				{1,3}
			});
			
			assertThrows(IllegalStateException.class, ()->{
				game.move(2, 3);
			});
		}
		
		@Test
		public void a_player_tries_to_move_to_a_cell_that_is_already_marked() {
			game.move(1, 1);
			
			assertThrows(InvalidParameterException.class, ()->{
				game.move(1, 1);
			});
			
		}
		
		@Test
		public void a_player_tries_to_mark_a_row_of_less_than_1() {
			assertThrows(InvalidParameterException.class, ()->{
				game.move(0, 1);
			});
		}
		
		@Test
		public void a_player_tries_to_mark_a_column_of_less_than_1() {
			assertThrows(InvalidParameterException.class, ()->{
				game.move(1, 0);
			});
		}
		
		@Test
		public void a_player_tries_to_mark_a_row_greater_than_3() {
			assertThrows(InvalidParameterException.class, ()->{
				game.move(4, 1);
			});
		}
		
		@Test
		public void a_player_tries_to_mark_a_col_greater_than_3() {
			assertThrows(InvalidParameterException.class, ()->{
				game.move(1, 4);
			});
		}
		
		@Test
		public void checking_for_marks_out_of_range() {
			assertThrows(InvalidParameterException.class, ()->{
				game.getMark(0, 1);
			});
		}
	}

	private void makeMoves(int[][] moves) {
		for (int[] move : moves) {
			game.move(move[0], move[1]);
		}
	}

}

