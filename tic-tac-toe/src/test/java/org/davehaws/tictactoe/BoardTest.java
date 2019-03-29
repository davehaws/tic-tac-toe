package org.davehaws.tictactoe;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.stream.Stream;

import org.davehaws.tictactoe.Board.Mark;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BoardTest {

	@Nested
	public class When_a_board_is_created {
		@Test
		void it_should_be_blank() {
			Board board = new Board();
			
			Stream<Mark> stream = board.getCells();
			
			assertThat(stream.allMatch(mark -> mark == Mark.BLANK), is(true));
		}
		
	}

}
