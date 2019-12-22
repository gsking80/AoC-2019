package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day22Test {
	
	@Test
	public void test1a() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day22/Test1a.txt").getPath());
        final Day22 day22 = new Day22(fileReader);
        day22.shuffle(10);
        Assertions.assertThat(day22.positionOfCard(0)).isEqualTo(0); 
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day22/input.txt").getPath());
        final Day22 day22 = new Day22(fileReader);
        day22.shuffle(10007);
        Assertions.assertThat(day22.positionOfCard(2019)).isEqualTo(4086);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day22/input.txt").getPath());
        final Day22 day22 = new Day22(fileReader);
        Assertions.assertThat(day22.simulateGiantShuffle(119315717514047L,101741582076661L,2020L)).isEqualTo(4086);
	}

}
