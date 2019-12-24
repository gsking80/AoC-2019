package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day24Test {

	  @Test
	  public void testSolution1() throws FileNotFoundException {
	    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day24/input.txt").getPath());
	    final Day24 day24 = new Day24(fileReader);
	    Assertions.assertThat(day24.firstRepeatedBiodiversity()).isEqualTo(26840049L);
	  }
	  
	  @Test
	  public void testSolution2() throws FileNotFoundException {
	    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day24/input.txt").getPath());
	    final Day24 day24 = new Day24(fileReader, true);
	    Assertions.assertThat(day24.totalBugsAfter(200)).isEqualTo(1995);
	  }

}
