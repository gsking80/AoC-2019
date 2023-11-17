package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day13Test {

	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/input.txt").getPath());
        final Day13 day13 = new Day13(fileReader);
        final List<Long> inputs = new ArrayList<>();
        day13.execute(inputs);
        Assertions.assertThat(day13.numTiles(2L)).isEqualTo(236L);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/input.txt").getPath());
        final Day13 day13 = new Day13(fileReader);
        final List<Long> inputs = new ArrayList<>();
        day13.editProgram(0, 0L, 2L);
        day13.execute(inputs);
        Assertions.assertThat(day13.getScore()).isEqualTo(11040L);
	}


}
