package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day17Test {

	@Test
	public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day17/input.txt").getPath());
        final Day17 day17 = new Day17(fileReader);
        final List<Long> inputs = new ArrayList<>();
        day17.execute(inputs);
        Assertions.assertThat(day17.alignmentParameterSum()).isEqualTo(5680);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day17/input.txt").getPath());
        final Day17 day17 = new Day17(fileReader);
        day17.editProgram(0, 0L, 2L);
        final List<Long> inputs = new ArrayList<>();
        final String mainMovement = "A,B,A,C,A,B,C,A,B,C";
        for (final char character: mainMovement.toCharArray()) {
        	inputs.add((long)character);
        }
        inputs.add(10L);
        final String functionA = "R,12,R,4,R,10,R,12";
        for (final char character: functionA.toCharArray()) {
        	inputs.add((long)character);
        }
        inputs.add(10L);
        final String functionB = "R,6,L,8,R,10";
        for (final char character: functionB.toCharArray()) {
        	inputs.add((long)character);
        }
        inputs.add(10L);
        final String functionC = "L,8,R,4,R,4,R,6";
        for (final char character: functionC.toCharArray()) {
        	inputs.add((long)character);
        }
        inputs.add(10L);
        inputs.add((long)'n');
        inputs.add(10L);
        day17.execute(inputs);
        Assertions.assertThat(day17.getFinalOutput(0)).isEqualTo(895965);
	}

}
