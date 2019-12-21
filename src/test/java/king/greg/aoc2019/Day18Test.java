package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day18Test {

	@Test
	public void test1a() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/Test1a.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps()).isEqualTo(8);
	}
	
	@Test
	public void test1b() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/Test1b.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps()).isEqualTo(86);
	}
	
	@Test
	public void test1c() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/Test1c.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps()).isEqualTo(132);
	}
	
	@Test
	public void test1d() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/Test1d.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps()).isEqualTo(136);
	}
	
	@Test
	public void test1e() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/Test1e.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps()).isEqualTo(81);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/input.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps()).isEqualTo(6098);
	}

    @Test
    public void test2a() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/Test2a.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps(true)).isEqualTo(8);
    }

    @Test
    public void test2b() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/Test2b.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps(true)).isEqualTo(32);
    }

    @Test
    public void test2c() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/Test2c.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps(true)).isEqualTo(72);
    }

    @Test
    public void test2d() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/Test2d.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps(true)).isEqualTo(8);
    }

	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/input.txt").getPath());
        final Day18 day18 = new Day18(fileReader);
        Assertions.assertThat(day18.fewestSteps(true)).isEqualTo(1698);
	}

}
