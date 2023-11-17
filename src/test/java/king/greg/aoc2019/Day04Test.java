package king.greg.aoc2019;

import java.io.FileNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day04Test {

	@Test
	public void testSolution1() throws FileNotFoundException {
        final Day04 day04 = new Day04("171309", "643603");
        Assertions.assertThat(day04.numPasswords(true)).isEqualTo(1625);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final Day04 day04 = new Day04("171309", "643603");
        Assertions.assertThat(day04.numPasswords(false)).isEqualTo(1111);
	}

}
