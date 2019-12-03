package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day03Test {

	@Test
	public void testsolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day03/input.txt").getPath());
        final Day03 day03 = new Day03(fileReader);
        Assertions.assertThat(day03.closestIntersection()).isEqualTo(5319);
	}
	
	@Test
	public void testsolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day03/input.txt").getPath());
        final Day03 day03 = new Day03(fileReader);
        Assertions.assertThat(day03.fastestIntersection()).isEqualTo(122514);
	}

}
