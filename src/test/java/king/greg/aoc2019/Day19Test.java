package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day19Test {

	@Test
	public void testSolution1() throws FileNotFoundException {
		long affectedPoints = 0;
		for (long y = 0; y < 50; y++) {
			for (long x = 0; x < 50; x++) {
				long output = checkPoint(x, y);
				System.out.print(output == 1 ? '#' : '.');
				affectedPoints += output;
			}
			System.out.println();
		}

		Assertions.assertThat(affectedPoints).isEqualTo(162L);
	}

	@Test
	public void testSolution2() throws FileNotFoundException {

		long x = 5;
		long y = 4;
		boolean found = false;
		long answer = 0;
		while(!found) {
			//follow bottom of the line
			while (checkPoint(x,y) == 0) {
				x++;
			}
			if (y - 99 >= 0 && checkPoint(x+99,y-99) == 1) {
				found = true;
				answer = (x*10000) + y - 99;
				System.out.println(x + ", " + (y - 99));
				break;
			} else {
				y++;
			}
		}

		Assertions.assertThat(answer).isEqualTo(13021056L);
	}

	private long checkPoint(final long x, final long y) {
		FileReader fileReader;
		try {
			fileReader = new FileReader(getClass().getClassLoader().getResource("Day19/input.txt").getPath());

			final Day19 day19 = new Day19(fileReader);
			final List<Long> inputs = new ArrayList<>();
			inputs.add(x);
			inputs.add(y);
			day19.execute(inputs);
			return day19.getFinalOutput(0);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
