package king.greg.aoc2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day25 {
	final List<Map<Long, Long>> programs = new ArrayList<>();
	final List<List<Long>> inputs = new ArrayList<>();
	final List<List<Long>> outputs = new ArrayList<>();
	final long[] relativeBases;
	final long[] currentPositions;

	public Day25(final int programCount, FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					for (int i = 0; i < programCount; i++) {
						String[] codes = lineJustFetched.split(",");
						final Map<Long, Long> program = new HashMap<>();
						for (long j = 0; j < codes.length; j++) {
							program.put(j, Long.valueOf(codes[(int) j]));
						}
						programs.add(program);
						final List<Long> input = new ArrayList<>();
						inputs.add(input);
						outputs.add(new ArrayList<>());
					}
				}
			}

			relativeBases = new long[programCount];
			currentPositions = new long[programCount];
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}

	public void execute() {

		while (programs.get(0).getOrDefault(currentPositions[0], 0L) != 99) {
			for (int i = 0; i < programs.size(); i++) {
				int a = (int) (programs.get(i).getOrDefault(currentPositions[i], 0L) / 10000);
				int b = (int) ((programs.get(i).getOrDefault(currentPositions[i], 0L) % 10000) / 1000);
				int c = (int) ((programs.get(i).getOrDefault(currentPositions[i], 0L) % 1000) / 100);
				int de = (int) (programs.get(i).getOrDefault(currentPositions[i], 0L) % 100);
				switch (de) {
				case 1:
					programs.get(i).put((address(i, a, currentPositions[i] + 3)),
							registerOrValue(i, c, currentPositions[i] + 1)
									+ registerOrValue(i, b, currentPositions[i] + 2));
					currentPositions[i] += 4;
					break;
				case 2:
					programs.get(i).put((address(i, a, currentPositions[i] + 3)),
							registerOrValue(i, c, currentPositions[i] + 1)
									* registerOrValue(i, b, currentPositions[i] + 2));
					currentPositions[i] += 4;
					break;
				case 3:
					determineInput(i);
					if (inputs.get(i).size() > 0) {
						programs.get(i).put((address(i, c, currentPositions[i] + 1)), inputs.get(i).remove(0));
						currentPositions[i] += 2;
					}
					break;
				case 4:
					outputs.get(i).add(registerOrValue(i, c, currentPositions[i] + 1));
					currentPositions[i] += 2;
					processOutput(i);
					break;
				case 5:
					if (!registerOrValue(i, c, currentPositions[i] + 1).equals(0L)) {
						currentPositions[i] = registerOrValue(i, b, currentPositions[i] + 2);
					} else {
						currentPositions[i] += 3;
					}
					break;
				case 6:
					if (registerOrValue(i, c, currentPositions[i] + 1).equals(0L)) {
						currentPositions[i] = registerOrValue(i, b, currentPositions[i] + 2);
					} else {
						currentPositions[i] += 3;
					}
					break;
				case 7:
					if (registerOrValue(i, c, currentPositions[i] + 1) < registerOrValue(i, b,
							currentPositions[i] + 2)) {
						programs.get(i).put((address(i, a, currentPositions[i] + 3)), 1L);
					} else {
						programs.get(i).put((address(i, a, currentPositions[i] + 3)), 0L);
					}
					currentPositions[i] += 4;
					break;
				case 8:
					if (registerOrValue(i, c, currentPositions[i] + 1)
							.equals(registerOrValue(i, b, currentPositions[i] + 2))) {
						programs.get(i).put((address(i, a, currentPositions[i] + 3)), 1L);
					} else {
						programs.get(i).put((address(i, a, currentPositions[i] + 3)), 0L);
					}
					currentPositions[i] += 4;
					break;
				case 9:
					relativeBases[i] += registerOrValue(i, c, currentPositions[i] + 1);
					currentPositions[i] += 2;
					break;
				case 99:
					break;
				default:
					throw new RuntimeException(i + " - " + de);
				}
			}
		}

	}

	private void determineInput(final int program) {
		if (inputs.get(program).size() == 0) {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			try {
				String inputString = input.readLine();
				addInput(program, inputString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void processOutput(final int program) {
		int output = outputs.get(program).remove(0).intValue();
		if (output > 127) { // this is our final answer
			outputs.get(program).add((long) output);
			return;
		}
		System.out.print((char) output);
	}

	private Long registerOrValue(final int program, final int mode, final long register) {
		switch (mode) {
		case 0:
			return programs.get(program).getOrDefault((programs.get(program).getOrDefault(register, 0L)), 0L);
		case 1:
			return programs.get(program).getOrDefault(register, 0L);
		case 2:
			return programs.get(program)
					.getOrDefault((programs.get(program).getOrDefault(register, 0L) + relativeBases[program]), 0L);
		default:
			throw new RuntimeException();
		}
	}

	private Long address(final int program, final int mode, final long register) {
		switch (mode) {
		case 0:
			return programs.get(program).getOrDefault(register, 0L);
		case 1:
			throw new UnsupportedOperationException();
		case 2:
			return (programs.get(program).getOrDefault(register, 0L) + relativeBases[program]);
		default:
			throw new RuntimeException();
		}
	}

	public void editProgram(final int programNumber, final Long instruction, final Long value) {
		programs.get(programNumber).put(instruction, value);
	}

	public long getFinalOutput(final int programNumber) {
		if (outputs.get(programNumber).size() == 0) {
			throw new RuntimeException("no output");
		}
		return outputs.get(programNumber).remove(0);
	}

	public void addInput(final int programNumber, final String input) {
		for (final char character : input.toCharArray()) {
			inputs.get(programNumber).add((long) character);
		}
		inputs.get(programNumber).add(10L);

	}
}
