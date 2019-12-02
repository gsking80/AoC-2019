package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day02Test {

	@Test
	public void test1a() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day02/Test1.txt").getPath());
        final Day02 day02 = new Day02(fileReader);
        day02.execute();
        Assertions.assertThat(day02.get(0)).isEqualTo(30);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day02/input.txt").getPath());
        final Day02 day02 = new Day02(fileReader);
        day02.set(1, 12);
        day02.set(2, 2);
        day02.execute();
        Assertions.assertThat(day02.get(0)).isEqualTo(2890696);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day02/input.txt").getPath());
        final Day02 day02 = new Day02(fileReader);
        Assertions.assertThat(day02.find(19690720)).isEqualTo(8226);
	}

}
