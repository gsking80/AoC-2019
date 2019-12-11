package king.greg.aoc2019;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {
	final List<Map<Long, Long>> programs = new ArrayList<>();
	final List<List<Long>> inputs = new ArrayList<>();
	final List<List<Long>> outputs = new ArrayList<>();
	final long[] relativeBases;
	final long[] currentPositions;

	final Map<Point, Long> panels = new HashMap<>();
	Point currentLocation = new Point(0, 0);
	int currentDirection = 0;
	boolean paint = true;
	int minX = 0;
	int maxX = 0;
	int minY = 0;
	int maxY = 0;

	public Day11(FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					for (int i = 0; i < 1; i++) {
						String[] codes = lineJustFetched.split(",");
						final Map<Long, Long> program = new HashMap<>();
						for (long j = 0; j < codes.length; j++) {
							program.put(j, Long.valueOf(codes[(int) j]));
						}
						programs.add(program);
					}
				}
			}

			relativeBases = new long[1];
			currentPositions = new long[1];
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}

	public long execute(final List<Long> input) {
		for (int i = 0; i < 1; i++) {
			final List<Long> inputI = new ArrayList<>();
			inputI.addAll(input);
			inputs.add(inputI);
			outputs.add(new ArrayList<>());
		}

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
					if (inputs.get(i).size() < 1) {
						inputs.get(i).add(panels.getOrDefault(currentLocation, 0L));
					}
					if (inputs.get(i).size() > 0) {
						programs.get(i).put((address(i, c, currentPositions[i] + 1)), inputs.get(i).remove(0));
						currentPositions[i] += 2;
					}
					break;
				case 4:
					outputs.get(i).add(registerOrValue(i, c, currentPositions[i] + 1));
					currentPositions[i] += 2;
					processOutput(outputs.get(i).remove(0));
					break;
				case 5:
					if (registerOrValue(i, c, currentPositions[i] + 1) != 0) {
						currentPositions[i] = registerOrValue(i, b, currentPositions[i] + 2);
					} else {
						currentPositions[i] += 3;
					}
					break;
				case 6:
					if (registerOrValue(i, c, currentPositions[i] + 1) == 0) {
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
					if (registerOrValue(i, c, currentPositions[i] + 1) == registerOrValue(i, b,
							currentPositions[i] + 2)) {
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
		print();
		return panels.size();

	}

	private void print() {
		for (int y = minY; y <= maxY; y++) {
			final StringBuilder sb = new StringBuilder();
			for (int x = minX; x <= maxX; x++) {
				sb.append(panels.getOrDefault(new Point(x,y), 0L).equals(1L) ? 'X' : ' ');
			}
			System.out.println(sb.toString());
		}
		
	}

	private void processOutput(final Long output) {
		if (paint) { // paint
			panels.put(currentLocation, output);
			paint = false;
		} else { // move
			if (output.equals(1L)) {
				currentDirection = (currentDirection + 1) % 4;
			} else if (output.equals(0L)) {
				currentDirection = (currentDirection + 3) % 4;
			} else {
				throw new RuntimeException();
			}
			move(1);
			paint = true;
		}

	}

	private void move(final int distance) {
		switch (currentDirection) {
		case 0:
			currentLocation = new Point(currentLocation.x, currentLocation.y - 1);
			if (currentLocation.y < minY) {
				minY--;
			}
			break;
		case 1:
			currentLocation = new Point(currentLocation.x + 1, currentLocation.y);
			if (currentLocation.x > maxX) {
				maxX++;
			}
			break;
		case 2:
			currentLocation = new Point(currentLocation.x, currentLocation.y + 1);
			if (currentLocation.y > maxY) {
				maxY++;
			}
			break;
		case 3:
			currentLocation = new Point(currentLocation.x - 1, currentLocation.y);
			if (currentLocation.x < minX) {
				minX--;
			}
			break;
		default:
			throw new RuntimeException();
		}

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
}
