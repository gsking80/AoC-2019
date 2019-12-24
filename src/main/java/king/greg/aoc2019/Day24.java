package king.greg.aoc2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day24 {

	Map<Point3d, Character> map = new HashMap<>();
	Map<Point3d, Character> nextMap;
	boolean recurse = false;

	public Day24(final FileReader fileReader) {
		this(fileReader, false);
	}

	public Day24(final FileReader fileReader, final boolean recurse) {
		this.recurse = recurse;
		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			int y = 0;
			while (true) {
				final String line = buf.readLine();
				if (null == line) {
					break;
				} else {
					for (int x = 0; x < line.length(); x++) {
						final Character character = line.charAt(x);
						if (character.equals('#')) {
							map.put(new Point3d(x, y, 0), line.charAt(x));
						}
					}
					y++;
				}
			}
		} catch (IOException ioe) {

		}
	}

	public long firstRepeatedBiodiversity() {
		Set<Long> biodiversities = new HashSet<>();
		long biodiversity = currentBiodiversity();
		while (!biodiversities.contains(biodiversity)) {
			biodiversities.add(biodiversity);
			nextMinute();
			biodiversity = currentBiodiversity();
		}
		return biodiversity;
	}

	private void nextMinute() {
		nextMap = new HashMap<>();
		final Set<Point3d> nextPoints = newPointsToCheck();
		for (final Point3d nextPoint : nextPoints) {
			final Character value = map.getOrDefault(nextPoint, '.');
			final int score = scorePoint(nextPoint);
			if (value.equals('#') && score == 1) {
				nextMap.put(nextPoint, '#');
			} else if (value.equals('.') && ((score == 1) || (score == 2))) {
				nextMap.put(nextPoint, '#');
			}

		}
		map = nextMap;
	}

	private Set<Point3d> newPointsToCheck() {
		final Set<Point3d> newPoints = new HashSet<>();
		for (final Point3d existingPoint : map.keySet()) {
			newPoints.add(existingPoint);
			newPoints.addAll(neighbors(existingPoint));
		}
		return newPoints;
	}

	private Set<Point3d> neighbors(final Point3d center) {
		final Set<Point3d> neighbors = new HashSet<>();
		// leftNeighbor
		if (center.x == 0) {
			if (recurse) {
				neighbors.add(new Point3d(1, 2, center.z - 1));
			}
		} else if (center.x == 3 && center.y == 2 && recurse) {
			for (int y = 0; y < 5; y++) {
				neighbors.add(new Point3d(4, y, center.z + 1));
			}
		} else {
			neighbors.add(new Point3d(center.x - 1, center.y, center.z));
		}
		// topNeighbor
		if (center.y == 0) {
			if (recurse) {
				neighbors.add(new Point3d(2, 1, center.z - 1));
			}
		} else if (center.y == 3 && center.x == 2 && recurse) {
			for (int x = 0; x < 5; x++) {
				neighbors.add(new Point3d(x, 4, center.z + 1));
			}
		} else {
			neighbors.add(new Point3d(center.x, center.y - 1, center.z));
		}
		// rightNeighbor
		if (center.x == 4) {
			if (recurse) {
				neighbors.add(new Point3d(3, 2, center.z - 1));
			}
		} else if (center.x == 1 && center.y == 2 && recurse) {
			for (int y = 0; y < 5; y++) {
				neighbors.add(new Point3d(0, y, center.z + 1));
			}
		} else {
			neighbors.add(new Point3d(center.x + 1, center.y, center.z));
		}
		// bottomNeighbor
		if (center.y == 4) {
			if (recurse) {
				neighbors.add(new Point3d(2, 3, center.z - 1));
			}
		} else if (center.y == 1 && center.x == 2 && recurse) {
			for (int x = 0; x < 5; x++) {
				neighbors.add(new Point3d(x, 0, center.z + 1));
			}
		} else {
			neighbors.add(new Point3d(center.x, center.y + 1, center.z));
		}
		return neighbors;
	}

	private int scorePoint(final Point3d point) {
		int score = 0;
		for (final Point3d neighbor : neighbors(point)) {
			score += '#' == map.getOrDefault(neighbor, '.') ? 1 : 0;
		}
		return score;
	}

	private long currentBiodiversity() {
		long biodiversity = 0;
		int power = 0;
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				final Character value = map.getOrDefault(new Point3d(x, y, 0), '.');
				if (value.equals('#')) {
					biodiversity += Math.pow(2, power);
				}
				power++;
			}
		}
		return biodiversity;
	}

	public int totalBugsAfter(final int minutes) {
		for (int i = 0; i < minutes; i++) {
			nextMinute();
		}
		return map.size();
	}

	class Point3d {
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			result = prime * result + z;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point3d other = (Point3d) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			if (z != other.z)
				return false;
			return true;
		}

		int x;
		int y;
		int z;

		Point3d(final int x, final int y, final int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		private Day24 getOuterType() {
			return Day24.this;
		}
	}

}
