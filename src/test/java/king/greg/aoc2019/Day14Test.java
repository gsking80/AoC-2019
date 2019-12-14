package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day14Test {

	@Test
	public void test1a() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day14/test1a.txt").getPath());
        final Day14 day14 = new Day14(fileReader);
        Assertions.assertThat(day14.necessaryOre(1L)).isEqualTo(31);
	}
	
	@Test
	public void test1b() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day14/test1b.txt").getPath());
        final Day14 day14 = new Day14(fileReader);
        Assertions.assertThat(day14.necessaryOre(1L)).isEqualTo(165);
	}
	
	@Test
	public void test1c() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day14/test1c.txt").getPath());
        final Day14 day14 = new Day14(fileReader);
        Assertions.assertThat(day14.necessaryOre(1L)).isEqualTo(13312);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day14/input.txt").getPath());
        final Day14 day14 = new Day14(fileReader);
        Assertions.assertThat(day14.necessaryOre(1L)).isEqualTo(365768);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day14/input.txt").getPath());
        final Day14 day14 = new Day14(fileReader);
        Assertions.assertThat(day14.fuelFrom(1000000000000L)).isEqualTo(3756877);
	}

}
