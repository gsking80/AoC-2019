package king.greg.aoc2019;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day15 {
	final List<Map<Long, Long>> programs = new ArrayList<>();
	final List<List<Long>> inputs = new ArrayList<>();
	final List<List<Long>> outputs = new ArrayList<>();
	final long[] relativeBases;
	final long[] currentPositions;

	final Map<Point, Character> map = new HashMap<>();
	Point currentPoint = new Point(0, 0);
	int currentDirection = 1;

	public Day15(FileReader fileReader) {
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

	public void execute(final List<Long> input) {
		for (int i = 0; i < 1; i++) {
			final List<Long> inputI = new ArrayList<>();
			inputI.addAll(input);
			inputs.add(inputI);
			outputs.add(new ArrayList<>());
		}
		
		map.put(new Point(currentPoint), '.');

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
					if(map.get(currentPoint).equals('O')) {
						return;
					}
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
		inputs.get(program).add((long) currentDirection);
	}

	private void processOutput(final int program) {
		// Always follow the left wall -- right wall is way better!
		// Classic dungeon explore!
		switch (outputs.get(program).remove(0).intValue()) {
		case 0: // wall! Time to turn
//			turnRight();
			turnLeft();
			break;
		case 1: // keep on trucking!
			updateCurrentPoint();
			map.put(new Point(currentPoint), '.');
//			turnLeft();
			turnRight();
			break;
		case 2: // we found it!
			updateCurrentPoint();
			map.put(new Point(currentPoint), 'O');
			break;
		default:
			throw new RuntimeException();

		}

	}

	private void turnRight() {
		if (currentDirection == 1) {
			currentDirection = 4;
		} else if (currentDirection == 2) {
			currentDirection = 3;
		} else if (currentDirection == 3) {
			currentDirection = 1;
		} else if (currentDirection == 4) {
			currentDirection = 2;
		}
	}

	private void turnLeft() {
		if (currentDirection == 1) {
			currentDirection = 3;
		} else if (currentDirection == 2) {
			currentDirection = 4;
		} else if (currentDirection == 3) {
			currentDirection = 2;
		} else if (currentDirection == 4) {
			currentDirection = 1;
		}
	}

	private void updateCurrentPoint() {
		switch (currentDirection) {
		case 1:
			currentPoint.y -= 1;
			break;
		case 2:
			currentPoint.y += 1;
			break;
		case 3:
			currentPoint.x -= 1;
			break;
		case 4:
			currentPoint.x += 1;
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

	public void editProgram(final int programNumber, final Long instruction, final Long value) {
		programs.get(programNumber).put(instruction, value);
	}

	public long fillTime() {
		map.put(new Point(0,0), 'X');
		printMap();
		int fillingTime = 0;
		map.put(new Point(0,0), '.');
		while (map.containsValue('.')) {
			Set<Point> gasToSpread = map.entrySet().stream().filter(entry -> entry.getValue().equals('O')).map(Map.Entry::getKey).collect(Collectors.toSet());
			for (final Point point: gasToSpread) {
				Set<Point> pointsToCheck = new HashSet<>();
				pointsToCheck.add(new Point(point.x-1, point.y));
				pointsToCheck.add(new Point(point.x+1, point.y));
				pointsToCheck.add(new Point(point.x, point.y-1));
				pointsToCheck.add(new Point(point.x, point.y+1));
				for (final Point pointToCheck: pointsToCheck) {
					if (map.getOrDefault(pointToCheck, '#').equals('.')) {
						map.put(pointToCheck, 'O');
					}
				}
			}
			fillingTime++;
		}
		return fillingTime;
	}

	private void printMap() {
		int smallestX =  map.keySet().stream().min(Comparator.comparing(a -> a.x)).get().x;
		int largestX =  map.keySet().stream().max(Comparator.comparing(a -> a.x)).get().x;
		int smallestY =  map.keySet().stream().min(Comparator.comparing(a -> a.y)).get().y;
		int largestY =  map.keySet().stream().max(Comparator.comparing(a -> a.y)).get().y;
		for (int y = smallestY; y <= largestY; y++) {
			for (int x = smallestX; x <= largestX; x++) {
				System.out.print(map.getOrDefault(new Point(x,y), '#'));
			}
			System.out.println();
		}
	}
}
