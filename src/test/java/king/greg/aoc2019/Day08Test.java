package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day08Test {

	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day08/input.txt").getPath());
        final Day08 day08 = new Day08(fileReader, 25, 6);
        Assertions.assertThat(day08.getSolution1('0')).isEqualTo(1677);
	}

	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day08/input.txt").getPath());
        final Day08 day08 = new Day08(fileReader, 25, 6);
        day08.getSolution2();
	}
	
}
