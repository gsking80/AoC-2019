package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day05Test {

	
	@Test
	public void test1a() throws FileNotFoundException {
        final Day05 day05 = new Day05("3,0,4,0,99");
        Assertions.assertThat(day05.execute(1)).isEqualTo(1);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day05/input.txt").getPath());
        final Day05 day05 = new Day05(fileReader);
        Assertions.assertThat(day05.execute(1)).isEqualTo(12896948);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day05/input.txt").getPath());
        final Day05 day05 = new Day05(fileReader);
        Assertions.assertThat(day05.execute(5)).isEqualTo(7704130);
	}

}
