package king.greg.aoc2019;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

import king.greg.aoc2019.Day18.Node;

public class Day20 {

	final Map<Point, Character> map = new HashMap<>();
	int maxX;
	int maxY;
	boolean recursive = false;

	public Day20(FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			int y = 0;
			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					int x = 0;
					for (final Character character : lineJustFetched.toCharArray()) {
						map.put(new Point(x, y), character);
						x++;
					}
					maxX = x;
					y++;
				}
			}
			maxX -= 3;
			maxY = y - 3;

		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}
	
	public void setRecursive(final boolean recursive) {
		this.recursive = recursive;
	}

	public int fewestSteps(final String startPortal, final String endPortal) {
		// find starting point
		final Point start = getPortalLocations(startPortal).get(0);
		final Point end = getPortalLocations(endPortal).get(0);
		return bfs(new Point3d(start,0), new Point3d(end, 0));
	}

	private int bfs(final Point3d start, final Point3d end) {

		Set<Point3d> visited = new HashSet<>();
		Map<Point3d, Integer> steps = new HashMap<>();
		final Deque<Point3d> queue = new ArrayDeque<>();

		steps.put(start, 0);
		queue.add(start);
		Point3d current = null;

		while (!queue.isEmpty()) {
			current = queue.remove();

			if (!visited.contains(current)) {
				visited.add(current);
				if (current.equals(end)) {
					return steps.get(current);
				}
				int stepsTaken = steps.get(current) + 1;
				// iterate over the neighbors
				for (Point3d neighbor : findNeighbors(current)) {
					if (!visited.contains(neighbor)) {
						// new Node
						if (steps.get(neighbor) == null || stepsTaken < steps.get(neighbor)) {
							steps.put(neighbor, stepsTaken);
							queue.add(neighbor);
						}
					}
				}
			}
		}
		throw new RuntimeException("Unreachable");
	}
	
	private Set<Point3d> findNeighbors(final Point3d current) {
		final Set<Point3d> neighbors = new HashSet<>();
		final Set<Point> potentialNeighbors = new HashSet<>();
		potentialNeighbors.add(new Point(current.x,current.y-1));
		potentialNeighbors.add(new Point(current.x,current.y+1));
		potentialNeighbors.add(new Point(current.x-1,current.y));
		potentialNeighbors.add(new Point(current.x+1,current.y));
		for (final Point potentialNeighbor : potentialNeighbors) {
			final Character value = map.getOrDefault(potentialNeighbor, ' ');
			if (value.equals('.')) {
				neighbors.add(new Point3d(potentialNeighbor, current.z));
			} else if (value >= 'A' && value <= 'Z') { // portal time!
				int level = current.z;
				final StringBuilder portalSB = new StringBuilder();
				if (current.x > potentialNeighbor.x) {
					portalSB.append(map.get(new Point(current.x-2,current.y))).append(value); 
				} else if (current.x < potentialNeighbor.x) {
					portalSB.append(value).append(map.get(new Point(current.x+2,current.y))); 
				} else if (current.y < potentialNeighbor.y) {
					portalSB.append(value).append(map.get(new Point(current.x, current.y+2)));
				} else if (current.y > potentialNeighbor.y){
					portalSB.append(map.get(new Point(current.x, current.y-2))).append(value);
				}
				final String portal = portalSB.toString();
				for (final Point jumpNeighbor: getPortalLocations(portal)) {
					if (!(jumpNeighbor.x == current.x && jumpNeighbor.y == current.y)) {
						if (recursive) {
							if (current.x == 2 || current.x == maxX || current.y == 2 || current.y == maxY) {
								level = current.z - 1;
							} else {
								level = current.z + 1;
							}
							if ((portal.equals("AA") || portal.equals("ZZ")) && level != 0) {
								continue;
							} else if (level < 0) {
								continue;
							}
						}
						neighbors.add(new Point3d(jumpNeighbor, level));
//						System.out.println(portal + " - " + current + " -> " + new Point3d(jumpNeighbor, level));
					}
				}
			}
		}
		
		return neighbors;
	}

	private List<Point> getPortalLocations(final String portal) {
		final Set<Point> firstLetterOptions = map.entrySet().stream()
				.filter(entry -> entry.getValue().equals(portal.charAt(0))).map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		final Set<Point> secondLetterOptions = map.entrySet().stream()
				.filter(entry -> entry.getValue().equals(portal.charAt(1))).map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		final List<Point> portalLocations = new ArrayList<>();
		for (final Point firstLetter : firstLetterOptions) {
			for (final Point secondLetter : secondLetterOptions) {
				if (firstLetter.equals(secondLetter)) {
					continue;
				}
				if (firstLetter.x == secondLetter.x && (secondLetter.y - firstLetter.y) == 1) {
					if (map.getOrDefault(new Point(firstLetter.x, firstLetter.y + 2), ' ').equals('.')) {
						portalLocations.add(new Point(firstLetter.x, firstLetter.y + 2));
					} else {
						portalLocations.add(new Point(firstLetter.x, firstLetter.y - 1));
					}
				} else if ((secondLetter.x - firstLetter.x) == 1 && firstLetter.y == secondLetter.y) {
					if (map.getOrDefault(new Point(firstLetter.x + 2, firstLetter.y), ' ').equals('.')) {
						portalLocations.add(new Point(firstLetter.x + 2, firstLetter.y));
					} else {
						portalLocations.add(new Point(firstLetter.x - 1, firstLetter.y));
					}
				}
			}
		}
		return portalLocations;
	}
	
	private class Point3d {

		@Override
		public String toString() {
			return "Point3d [x=" + x + ", y=" + y + ", z=" + z + "]";
		}

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
		
		public Point3d(final Point point, final int z) {
			this.x = point.x;
			this.y = point.y;
			this.z = z;
		}

		private Day20 getOuterType() {
			return Day20.this;
		}
	}

}
