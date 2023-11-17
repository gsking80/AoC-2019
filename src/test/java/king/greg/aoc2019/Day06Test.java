package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day06Test {

	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day06/input.txt").getPath());
        final Day06 day06 = new Day06(fileReader);
        Assertions.assertThat(day06.getOrbitCount()).isEqualTo(241064);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day06/input.txt").getPath());
        final Day06 day06 = new Day06(fileReader);
        Assertions.assertThat(day06.getTransfers("YOU","SAN")).isEqualTo(418);
	}

}
