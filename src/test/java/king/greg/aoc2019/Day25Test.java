package king.greg.aoc2019;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

public class Day25Test {

    @Test
    public void testSolution1() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day25/input.txt").getPath());
        final Day25 day25 = new Day25(1, fileReader);
        day25.execute();
        // No assertions today, just System outs like a great developer!
    }

}
