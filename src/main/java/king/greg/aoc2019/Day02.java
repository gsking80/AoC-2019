package king.greg.aoc2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day02 {

	int[] program;
	
	public Day02(FileReader fileReader) {
        try {
            final BufferedReader buf = new BufferedReader(fileReader);

            while(true) {
                final String lineJustFetched = buf.readLine();
                if(null == lineJustFetched) {
                    break;
                } else {
                	program = Arrays.asList(lineJustFetched.split(",")).stream().mapToInt(Integer::parseInt).toArray();
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException();
        }
	}

	public void execute() {
		int currentPosition = 0;
		
		while(program[currentPosition] != 99) {
			switch(program[currentPosition]) {
			case 1:
				program[program[currentPosition + 3]] = program[program[currentPosition + 1]] + program[program[currentPosition + 2]];
				currentPosition += 4;
				break;
			case 2:
				program[program[currentPosition + 3]] = program[program[currentPosition + 1]] * program[program[currentPosition + 2]];
				currentPosition += 4;
				break;
			}
		}
		
	}

	public int get(final int location) {
		return program[location];
	}

	public void set(final int location, final int value) {
		program[location] = value;
		
	}

	public int find(final int output) {
		int[] programCopy = Arrays.copyOf(program, program.length);
		for (int noun = 0; noun < 100; noun++) {
			for (int verb = 0; verb < 100; verb++) {
				program = Arrays.copyOf(programCopy, programCopy.length);
				set(1, noun);
				set(2, verb);
				execute();
				if (get(0) == output) {
					return (100 * noun) + verb;
				}
			}
		}
		
		return -1;
	}

}
