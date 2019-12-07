package king.greg.aoc2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 {
	final List<int[]> programs = new ArrayList<>();;
	final List<List<Integer>> inputs = new ArrayList<>(5);
	final List<List<Integer>> outputs = new ArrayList<>(5);

	public Day07(FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					for (int i = 0; i < 5; i++) {
						programs.add(Arrays.asList(lineJustFetched.split(",")).stream().mapToInt(Integer::parseInt)
								.toArray());
					}
				}
			}
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}

	public Day07(final String input) {
		for (int i = 0; i < 5; i++) {
			programs.add(Arrays.asList(input.split(",")).stream().mapToInt(Integer::parseInt).toArray());
		}
	}

	public int execute(final List<Integer> input) {
		final int[] currentPositions = new int[5];
		for (int i = 0; i < 5; i++) {
			final List<Integer> inputI = new ArrayList<>();
			inputI.add(input.get(i));
			inputs.add(inputI);
			outputs.add(new ArrayList<>());
		}
		
		inputs.get(0).add(0);

		while (programs.get(4)[currentPositions[4]] != 99) {
			for (int i = 0; i < 5; i++) {
				int a = programs.get(i)[currentPositions[i]] / 10000;
				int b = (programs.get(i)[currentPositions[i]] % 10000) / 1000;
				int c = (programs.get(i)[currentPositions[i]] % 1000) / 100;
				int de = programs.get(i)[currentPositions[i]] % 100;
				switch (de) {
				case 1:
					programs.get(
							i)[(a == 1 ? currentPositions[i] + 3 : programs.get(i)[currentPositions[i] + 3])] = programs
									.get(i)[(c == 1 ? currentPositions[i] + 1
											: programs.get(i)[currentPositions[i] + 1])]
									+ programs.get(i)[(b == 1 ? currentPositions[i] + 2
											: programs.get(i)[currentPositions[i] + 2])];
					currentPositions[i] += 4;
					break;
				case 2:
					programs.get(
							i)[(a == 1 ? currentPositions[i] + 3 : programs.get(i)[currentPositions[i] + 3])] = programs
									.get(i)[(c == 1 ? currentPositions[i] + 1
											: programs.get(i)[currentPositions[i] + 1])]
									* programs.get(i)[(b == 1 ? currentPositions[i] + 2
											: programs.get(i)[currentPositions[i] + 2])];
					currentPositions[i] += 4;
					break;
				case 3:
					if (outputs.get(i > 0 ? i - 1 : 4).size() > 0) {
						inputs.get(i).add(outputs.get(i > 0 ? i - 1 : 4).remove(0));
					}
					if (inputs.get(i).size() > 0) {
						programs.get(i)[programs.get(i)[currentPositions[i] + 1]] = inputs.get(i).remove(0);
						currentPositions[i] += 2;
					}
					break;
				case 4:
					outputs.get(i).add((c == 1 ? programs.get(i)[currentPositions[i] + 1]
							: programs.get(i)[programs.get(i)[currentPositions[i] + 1]]));
					currentPositions[i] += 2;
					break;
				case 5:
					if (programs.get(
							i)[(c == 1 ? currentPositions[i] + 1 : programs.get(i)[currentPositions[i] + 1])] != 0) {
						currentPositions[i] = programs
								.get(i)[(b == 1 ? currentPositions[i] + 2 : programs.get(i)[currentPositions[i] + 2])];
					} else {
						currentPositions[i] += 3;
					}
					break;
				case 6:
					if (programs.get(
							i)[(c == 1 ? currentPositions[i] + 1 : programs.get(i)[currentPositions[i] + 1])] == 0) {
						currentPositions[i] = programs
								.get(i)[(b == 1 ? currentPositions[i] + 2 : programs.get(i)[currentPositions[i] + 2])];
					} else {
						currentPositions[i] += 3;
					}
					break;
				case 7:
					if (programs.get(i)[(c == 1 ? currentPositions[i] + 1
							: programs.get(i)[currentPositions[i] + 1])] < programs.get(
									i)[(b == 1 ? currentPositions[i] + 2 : programs.get(i)[currentPositions[i] + 2])]) {
						programs.get(
								i)[(a == 1 ? currentPositions[i] + 3 : programs.get(i)[currentPositions[i] + 3])] = 1;
					} else {
						programs.get(
								i)[(a == 1 ? currentPositions[i] + 3 : programs.get(i)[currentPositions[i] + 3])] = 0;
					}
					currentPositions[i] += 4;
					break;
				case 8:
					if (programs.get(i)[(c == 1 ? currentPositions[i] + 1
							: programs.get(i)[currentPositions[i] + 1])] == programs.get(
									i)[(b == 1 ? currentPositions[i] + 2 : programs.get(i)[currentPositions[i] + 2])]) {
						programs.get(
								i)[(a == 1 ? currentPositions[i] + 3 : programs.get(i)[currentPositions[i] + 3])] = 1;
					} else {
						programs.get(
								i)[(a == 1 ? currentPositions[i] + 3 : programs.get(i)[currentPositions[i] + 3])] = 0;
					}
					currentPositions[i] += 4;
					break;
				case 99:
					break;
				default:
					throw new RuntimeException(i + " - " + de);
				}
			}
		}
		return outputs.get(4).remove(0);

	}

}
