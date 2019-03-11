package org.davehaws.tictactoe;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TicTacToeTest {

	@Test
	public void new_game_should_have_blank_board() {
		TicTacToe game = new TicTacToe();
		
		for (int row = 1; row < 4; row++) {
			for (int col = 1; col < 4; col++) {
				assertThat(game.getMark(row, col), is(""));
			}
		}
	}

}