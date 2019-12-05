package king.greg.aoc2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day05 {

	int[] program;

	public Day05(FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					program = Arrays.asList(lineJustFetched.split(",")).stream().mapToInt(Integer::parseInt).toArray();
				}
			}
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}

	public Day05(final String input) {
		program = Arrays.asList(input.split(",")).stream().mapToInt(Integer::parseInt).toArray();
	}

	public int execute(final int input) {
		int currentPosition = 0;
		int output = 0;

		while (program[currentPosition] != 99) {
			int a = program[currentPosition] / 10000;
			int b = (program[currentPosition] % 10000) / 1000;
			int c = (program[currentPosition] % 1000) / 100;
			int de = program[currentPosition] % 100;
			switch (de) {
			case 1:
				program[(a == 1 ? currentPosition + 3
						: program[currentPosition + 3])] = program[(c == 1 ? currentPosition + 1
								: program[currentPosition + 1])]
								+ program[(b == 1 ? currentPosition + 2 : program[currentPosition + 2])];
				currentPosition += 4;
				break;
			case 2:
				program[(a == 1 ? currentPosition + 3
						: program[currentPosition + 3])] = program[(c == 1 ? currentPosition + 1
								: program[currentPosition + 1])]
								* program[(b == 1 ? currentPosition + 2 : program[currentPosition + 2])];
				currentPosition += 4;
				break;
			case 3:
				program[program[currentPosition + 1]] = input;
				currentPosition += 2;
				break;
			case 4:
				System.out.println((c == 1 ? program[currentPosition + 1] : program[program[currentPosition + 1]]));
				output = (c == 1 ? program[currentPosition + 1] : program[program[currentPosition + 1]]);
				currentPosition += 2;
				break;
			case 5:
				if (program[(c == 1 ? currentPosition + 1 : program[currentPosition + 1])] != 0) {
					currentPosition = program[(b == 1 ? currentPosition + 2 : program[currentPosition + 2])];
				} else {
					currentPosition += 3;
				}
				break;
			case 6:
				if (program[(c == 1 ? currentPosition + 1 : program[currentPosition + 1])] == 0) {
					currentPosition = program[(b == 1 ? currentPosition + 2 : program[currentPosition + 2])];
				} else {
					currentPosition += 3;
				}
				break;
			case 7:
				if (program[(c == 1 ? currentPosition + 1 : program[currentPosition + 1])] < program[(b == 1
						? currentPosition + 2
						: program[currentPosition + 2])]) {
					program[(a == 1 ? currentPosition + 3 : program[currentPosition + 3])] = 1;
				} else {
					program[(a == 1 ? currentPosition + 3 : program[currentPosition + 3])] = 0;
				}
				currentPosition += 4;
				break;
			case 8:
				if (program[(c == 1 ? currentPosition + 1 : program[currentPosition + 1])] == program[(b == 1
						? currentPosition + 2
						: program[currentPosition + 2])]) {
					program[(a == 1 ? currentPosition + 3 : program[currentPosition + 3])] = 1;
				} else {
					program[(a == 1 ? currentPosition + 3 : program[currentPosition + 3])] = 0;
				}
				currentPosition += 4;
				break;
			default:
				throw new RuntimeException();
			}
		}
		return output;

	}

	public int get(final int location) {
		return program[location];
	}

	public void set(final int location, final int value) {
		program[location] = value;

	}

}
