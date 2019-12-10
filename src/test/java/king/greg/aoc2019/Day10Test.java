package king.greg.aoc2019;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day10Test {

	@Test
	public void test1a() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/Test1a.txt").getPath());
        final Day10 day10 = new Day10(fileReader);
        Assertions.assertThat(day10.besteroidCount()).isEqualTo(8);
	}
	
	@Test
	public void test1b() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/Test1b.txt").getPath());
        final Day10 day10 = new Day10(fileReader);
        Assertions.assertThat(day10.besteroidCount()).isEqualTo(33);
	}
	
	@Test
	public void test1c() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/Test1c.txt").getPath());
        final Day10 day10 = new Day10(fileReader);
        Assertions.assertThat(day10.besteroidCount()).isEqualTo(35);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/input.txt").getPath());
        final Day10 day10 = new Day10(fileReader);
        Assertions.assertThat(day10.besteroidCount()).isEqualTo(267); //26,28
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/input.txt").getPath());
        final Day10 day10 = new Day10(fileReader);
        Assertions.assertThat(day10.destroy(200, new Point(26,28))).isEqualTo(1309);
	}

}
