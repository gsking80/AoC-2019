package king.greg.aoc2019;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day23Test {

    @Test
    public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day23/input.txt").getPath());
        final Day23 day23 = new Day23(50, fileReader);
        final List<Long> inputs = new ArrayList<>();
        day23.execute();
        // No assertions today, just System outs like a great developer!
    }

}