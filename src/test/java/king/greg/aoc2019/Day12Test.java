package king.greg.aoc2019;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day12Test {

	@Test
	public void testSolution1() {
		final Day12 day12 = new Day12();
		day12.addMoon(5, 13, -3);
		day12.addMoon(18, -7, 13);
		day12.addMoon(16, 3, 4);
		day12.addMoon(0, 8, 8);
		Assertions.assertThat(day12.simulate(1000)).isEqualTo(10198);
	}
	
	@Test
	public void testSolution2() {
		final Day12 day12 = new Day12();
		day12.addMoon(5, 13, -3);
		day12.addMoon(18, -7, 13);
		day12.addMoon(16, 3, 4);
		day12.addMoon(0, 8, 8);
		Assertions.assertThat(day12.firstRepeat()).isEqualTo(10198);
	}

}
