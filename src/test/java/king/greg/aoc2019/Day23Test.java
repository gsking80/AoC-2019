package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

public class Day23Test {

    @Test
    public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day23/input.txt").getPath());
        final Day23 day23 = new Day23(50, fileReader);
        day23.execute();
        // No assertions today, just System outs like a great developer!
    }

}