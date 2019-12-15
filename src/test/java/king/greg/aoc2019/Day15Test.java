package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day15Test {

	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/input.txt").getPath());
        final Day15 day15 = new Day15(fileReader);
        final List<Long> inputs = new ArrayList<>();
        day15.execute(inputs);
        Assertions.assertThat(day15.fillTime()).isEqualTo(544L);
	}

}
