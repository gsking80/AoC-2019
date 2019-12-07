package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day07Test {

	@Test
	public void testSolution1() throws FileNotFoundException {
		int output = 0;
		for (int a = 0; a < 5; a++) {
			for (int b = 0; b < 5; b++) {
				if (b == a) {
					continue;
				}
				for (int c = 0; c < 5; c++) {
					if (c == a || c == b) {
						continue;
					}
					for (int d = 0; d < 5; d++) {
						if (d == a || d == b || d == c) {
							continue;
						}
						for (int e = 0; e < 5; e++) {
							if (e == a || e == b || e == c || e == d) {
								continue;
							}
							final FileReader fileReaderE = new FileReader(getClass().getClassLoader().getResource("Day07/input.txt").getPath());
							final Day07 day07E = new Day07(fileReaderE);
							final List<Integer> inputE = new ArrayList<>();
							inputE.add(a);
							inputE.add(b);
							inputE.add(c);
							inputE.add(d);
							inputE.add(e);
							int outputE = day07E.execute(inputE);
							if (outputE > output) {
								output = outputE;
							}
						}
					}
				}
			}
		}
		
        Assertions.assertThat(output).isEqualTo(51679);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
		int output = 0;
		for (int a = 5; a < 10; a++) {
			for (int b = 5; b < 10; b++) {
				if (b == a) {
					continue;
				}
				for (int c = 5; c < 10; c++) {
					if (c == a || c == b) {
						continue;
					}
					for (int d = 5; d < 10; d++) {
						if (d == a || d == b || d == c) {
							continue;
						}
						for (int e = 5; e < 10; e++) {
							if (e == a || e == b || e == c || e == d) {
								continue;
							}
							final FileReader fileReaderE = new FileReader(getClass().getClassLoader().getResource("Day07/input.txt").getPath());
							final Day07 day07E = new Day07(fileReaderE);
							final List<Integer> inputE = new ArrayList<>();
							inputE.add(a);
							inputE.add(b);
							inputE.add(c);
							inputE.add(d);
							inputE.add(e);
							int outputE = day07E.execute(inputE);
							if (outputE > output) {
								output = outputE;
							}
						}
					}
				}
			}
		}
		
        Assertions.assertThat(output).isEqualTo(19539216);
	}

}
