package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day21Test {

    @Test
    public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day21/input.txt").getPath());
        final Day21 day21 = new Day21(1, fileReader);
        final List<Long> inputs = new ArrayList<>();
        final List<String> inputCommands = new ArrayList<>();
        inputCommands.add("NOT A J");
        inputCommands.add("NOT B T");
        inputCommands.add("OR T J");
        inputCommands.add("NOT C T");
        inputCommands.add("OR T J");
        inputCommands.add("AND D J");
        inputCommands.add("WALK");
        day21.addInput(0, inputCommands);
        day21.execute(inputs);
        Assertions.assertThat(day21.getFinalOutput(0)).isEqualTo(19355391L);
    }

    @Test
    public void testSolution2() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day21/input.txt").getPath());
        final Day21 day21 = new Day21(1, fileReader);
        final List<Long> inputs = new ArrayList<>();
        final List<String> inputCommands = new ArrayList<>();
        inputCommands.add("NOT A J");
        inputCommands.add("NOT B T");
        inputCommands.add("OR T J");
        inputCommands.add("NOT C T");
        inputCommands.add("OR T J");  // One of the 3 spaces we can jump is a hole
        inputCommands.add("AND D J"); // And our landing spot is not
        inputCommands.add("NOT H T");
        inputCommands.add("NOT T T"); // And we can jump again immediately
        inputCommands.add("OR E T");  // Or take the next step
        inputCommands.add("AND T J");
        inputCommands.add("RUN");
        day21.addInput(0, inputCommands);
        day21.execute(inputs);
        Assertions.assertThat(day21.getFinalOutput(0)).isEqualTo(1143770635L);
    }

}