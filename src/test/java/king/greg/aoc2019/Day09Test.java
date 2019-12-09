package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day09Test {

	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day09/input.txt").getPath());
        final Day09 day09 = new Day09(fileReader);
        final List<Long> inputs = new ArrayList<>();
        inputs.add(1L);
        Assertions.assertThat(day09.execute(inputs)).isEqualTo(3280416268L);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day09/input.txt").getPath());
        final Day09 day09 = new Day09(fileReader);
        final List<Long> inputs = new ArrayList<>();
        inputs.add(2L);
        Assertions.assertThat(day09.execute(inputs)).isEqualTo(80210L);
	}

}
