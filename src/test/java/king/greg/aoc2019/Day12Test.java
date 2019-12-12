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
	public void test2a() {
		final Day12 day12 = new Day12();
		day12.addMoon(-1, 0, 2);
		day12.addMoon(2, -10, -7);
		day12.addMoon(4, -8, 8);
		day12.addMoon(3, 5, -1);
		Assertions.assertThat(day12.firstRepeat()).isEqualTo(2772);
	}

	@Test
	public void test2b() {
		final Day12 day12 = new Day12();
		day12.addMoon(-8, -10, 0);
		day12.addMoon(5, 5,10);
		day12.addMoon(2, -7, 3);
		day12.addMoon(9, -8, -3);
		Assertions.assertThat(day12.firstRepeat()).isEqualTo(4686774924L);
	}



	@Test
	public void testSolution2() {
		final Day12 day12 = new Day12();
		day12.addMoon(5, 13, -3);
		day12.addMoon(18, -7, 13);
		day12.addMoon(16, 3, 4);
		day12.addMoon(0, 8, 8);
		Assertions.assertThat(day12.firstRepeat()).isEqualTo(271442326847376L);
	}

}
