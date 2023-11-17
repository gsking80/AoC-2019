package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day20Test {
	
	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/input.txt").getPath());
        final Day20 day20 = new Day20(fileReader);
        Assertions.assertThat(day20.fewestSteps("AA","ZZ")).isEqualTo(442); 
	}

	@Test
	public void testSolution2a() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/Test1a.txt").getPath());
        final Day20 day20 = new Day20(fileReader);
        day20.setRecursive(true);
        Assertions.assertThat(day20.fewestSteps("AA","ZZ")).isEqualTo(26);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/input.txt").getPath());
        final Day20 day20 = new Day20(fileReader);
        day20.setRecursive(true);
        Assertions.assertThat(day20.fewestSteps("AA","ZZ")).isEqualTo(5208);
	}
	
}
