package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day11Test {

	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day11/input.txt").getPath());
        final Day11 day11 = new Day11(fileReader);
        final List<Long> inputs = new ArrayList<>();
        Assertions.assertThat(day11.execute(inputs)).isEqualTo(2141L);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day11/input.txt").getPath());
        final Day11 day11 = new Day11(fileReader);
        final List<Long> inputs = new ArrayList<>();
        inputs.add(1L);
        Assertions.assertThat(day11.execute(inputs)).isEqualTo(249L);
	}

}
