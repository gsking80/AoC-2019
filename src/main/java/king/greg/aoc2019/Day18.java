package king.greg.aoc2019;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Day18 {

	final Map<Point, Character> map = new HashMap<>();
	final Set<Character> keys;
	final Map<Point, Map<Point, DistanceAndDoors>> distances = new HashMap<>();
	Point start;

	public Day18(FileReader fileReader) {
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
						if (character.equals('@')) {
							start = new Point(x, y);
							map.put(new Point(x, y), '0');
						} else {
							map.put(new Point(x, y), character);
						}
						x++;
					}
					y++;
				}
			}

//			keys = map.entrySet().stream().filter(entry -> (entry.getValue() >= 'a') && (entry.getValue() <= 'z'))
//					.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
			keys = map.values().stream().filter(value -> value >= 'a' && value <= 'z').collect(Collectors.toSet());
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}

	public int fewestSteps() {
		return fewestSteps(false);
	}
	
	public int fewestSteps(final boolean part2) {
		final Set<Point> starts = new HashSet<>();
		if (part2) {
			starts.add(new Point(start.x -1, start.y-1));
			starts.add(new Point(start.x -1, start.y+1));
			starts.add(new Point(start.x +1, start.y-1));
			starts.add(new Point(start.x +1, start.y+1));
			map.put(new Point(start.x -1, start.y-1), '0');
			map.put(new Point(start.x -1, start.y+1), '1');
			map.put(new Point(start.x +1, start.y-1), '2');
			map.put(new Point(start.x +1, start.y+1), '3');
			map.put(new Point(start), '#');
			map.put(new Point(start.x,start.y-1), '#');
			map.put(new Point(start.x,start.y+1), '#');
			map.put(new Point(start.x-1,start.y), '#');
			map.put(new Point(start.x+1,start.y), '#');
		} else {
			starts.add(new Point(start));
		}
		final int fastestPath = aStar(new Node(starts, new HashSet<>(keys), 0));
//		printPath(fastestPath);
//		int steps = fastestPath.size() - 1;
		return fastestPath;
	}

	public int aStar(final Node start) {

		//build our distance graph
		calculateDistances();

		// Setup for A*
//		Map<Node, Node> parentMap = new HashMap<Node, Node>();
		Set<Node> visited = new HashSet<Node>();
		Map<Node, Integer> steps = new HashMap<Node, Integer>();

		Queue<Node> priorityQueue = initQueue();
//		Deque<Node> priorityQueue = new ArrayDeque<>();

		steps.put(start, 0);
		priorityQueue.add(start);

		Node current = null;

		int maxSteps = 0;

		while (!priorityQueue.isEmpty()) {
			current = priorityQueue.remove();

			if (!visited.contains(current)) {
				if(current.stepsTaken > maxSteps) {
					System.out.println(current.stepsTaken + " steps, priorityQueue size = " + priorityQueue.size());
					maxSteps += 100;
				}
				visited.add(current);
				if (current.keysRemaining() == 0) {
//					return reconstructPath(start, current, parentMap);
					return current.stepsTaken;
				}
				// iterate over the neighbors
				for (Node neighbor : current.findNeighbors()) {
					if (!visited.contains(neighbor)) {
						// new Node
//						int totalSteps = neighbor.getEstimatedTotalSteps();
//						if (steps.get(neighbor) == null || totalSteps < steps.get(neighbor)) {
//							steps.put(neighbor, totalSteps);
//							parentMap.put(neighbor, current);
							priorityQueue.add(neighbor);
//						}
					}
				}
			}
		}

		return -1;
	}

	private void calculateDistances() {
		final Map<Point, Character> placesOfInterest = map.entrySet().stream().filter(entry -> (entry.getValue() >= '0') && (entry.getValue() <= 'z'))
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		for (Map.Entry<Point, Character> placeOfInterest : placesOfInterest.entrySet()) {
			final Map<Point,DistanceAndDoors> distanceMap = new HashMap<>();
			Set<Point> visited = new HashSet<>();
			Map<Point, DistanceAndDoors> steps = new HashMap<>();
			Deque<Point> queue = new ArrayDeque<>();

			steps.put(placeOfInterest.getKey(), new DistanceAndDoors(0, new HashSet<>()));
			queue.add(placeOfInterest.getKey());

			Point current = null;

			while (!queue.isEmpty()) {
				current = queue.remove();

				if (!visited.contains(current)) {
					visited.add(current);
					final DistanceAndDoors dndCurrent = steps.get(current);

					// iterate over the neighbors
					for (Point neighbor : getNeighborPoints(current)) {
						if (!visited.contains(neighbor)) {
							final Character point = map.get(neighbor);
							if (point.equals('.')) {
								queue.add(neighbor);
								steps.put(neighbor, new DistanceAndDoors(dndCurrent.distance+1,dndCurrent.doors));
							} else if (point >= 'A' && point <= 'Z') {
								queue.add(neighbor);
								final HashSet<Character> doors = new HashSet<>(dndCurrent.doors);
								doors.add(point);
								steps.put(neighbor, new DistanceAndDoors(dndCurrent.distance+1, doors));
							} else if (point >= 'a' && point <= 'z') {
								distanceMap.put(neighbor, new DistanceAndDoors(dndCurrent.distance+1,dndCurrent.doors));
							}
						}
					}
				}
			}
			distances.put(placeOfInterest.getKey(), distanceMap);
		}
		System.out.println(distances.toString());
	}

	private class DistanceAndDoors{
		final int distance;
		final Set<Character> doors;

		DistanceAndDoors(final int distance, final Set<Character> doors){
			this.distance = distance;
			this.doors = doors;
		}
	}

	private PriorityQueue<Node> initQueue() {
		return new PriorityQueue<>(10, new Comparator<Node>() {

			@Override
			public int compare(Node arg0, Node arg1) {
//				return Comparator.comparing(Node::getEstimatedTotalSteps).thenComparing(Node::keysRemaining)
//						.compare(arg0, arg1);

				return Comparator.comparing(Node::getStepsTaken).thenComparing(Node::keysRemaining).compare(arg0, arg1);

			}

		});
	}

	class Node {

		@Override
		public String toString() {
			return "Node [hashCode=" + hashCode() + ", location=" + locations.toString() + ", remainingKeys="
					+ remainingKeys + ", stepsTaken=" + stepsTaken + "]";
		}

		final Set<Point> locations;
		final Set<Character> remainingKeys;
		final int stepsTaken;

		public Node(Set<Point> locations, Set<Character> remainingKeys, int stepsTaken) {
			this.locations = locations;
			this.remainingKeys = remainingKeys;
			this.stepsTaken = stepsTaken;
		}

		public int keysRemaining() {
			return remainingKeys.size();
		}

//		public int getEstimatedStepsRemaining() {
//			int remaining = 0;
//			if (remainingKeys.size() == 0) {
//				return remaining;
//			}
//			int smallestX = remainingKeys.keySet().stream().min(Comparator.comparing(a -> a.x)).get().x;
//			int largestX = remainingKeys.keySet().stream().max(Comparator.comparing(a -> a.x)).get().x;
//			if (location.x <= smallestX) {
//				remaining += largestX - location.x;
//			} else if (location.x >= largestX) {
//				remaining += location.x - largestX;
//			} else {
//				remaining += (largestX - smallestX) + (location.x - smallestX);
//			}
//			int smallestY = remainingKeys.keySet().stream().min(Comparator.comparing(a -> a.y)).get().y;
//			int largestY = remainingKeys.keySet().stream().max(Comparator.comparing(a -> a.y)).get().y;
//			if (location.y <= smallestY) {
//				remaining += largestY - location.y;
//			} else if (location.y >= largestY) {
//				remaining += location.y - largestY;
//			} else {
//				remaining += (largestY - smallestY) + (location.y - smallestY);
//			}
//			return remaining;
//		}

		public int getStepsTaken() {
			return stepsTaken;
		}

//		public int getEstimatedTotalSteps() {
//			return stepsTaken + getEstimatedStepsRemaining();
//		}

		public Set<Node> findNeighbors() {
			final Set<Node> neighbors = new HashSet<>();
			for (final Point location : locations) {
				final Map<Point, DistanceAndDoors> neighborPoints = distances.get(location);
				for (final Map.Entry<Point,DistanceAndDoors> potentialNeighbor : neighborPoints.entrySet()) {
					boolean canPass = true;
					for (final Character door : potentialNeighbor.getValue().doors) {
						final char requiredKey = (char) (door + ('a' - 'A'));
						if (remainingKeys.contains(requiredKey)) {
							canPass = false;
						}
					}
					if (!canPass) {
						continue;
					}
					final Character value = map.get(potentialNeighbor.getKey());
					if (value >= 'a' && value <= 'z') {
						Set<Character> newRemainingKeys = remainingKeys.stream()
								.filter(entry -> !entry.equals(value))
								.collect(Collectors.toSet());
						final Set<Point> potentialNeighbors = new HashSet<>();
						for (final Point otherLocation: locations) {
							if (!otherLocation.equals(location)) {
								potentialNeighbors.add(otherLocation);
							}
						}
						potentialNeighbors.add(potentialNeighbor.getKey());
						neighbors.add(new Node(potentialNeighbors, newRemainingKeys, stepsTaken + potentialNeighbor.getValue().distance));
					} else if (value >= '0' && value <= '9') {
						final Set<Point> potentialNeighbors = new HashSet<>();
						for (final Point otherLocation: locations) {
							if (!otherLocation.equals(location)) {
								potentialNeighbors.add(otherLocation);
							}
						}
						potentialNeighbors.add(potentialNeighbor.getKey());
						neighbors.add(new Node(potentialNeighbors, new HashSet<>(remainingKeys), stepsTaken + potentialNeighbor.getValue().distance));
					} else {
						throw new RuntimeException("Don't know what to do with value -- " + value);
					}
				}
			}
			return neighbors;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((locations == null) ? 0 : Arrays.deepHashCode(locations.toArray()));
			result = prime * result
					+ ((remainingKeys == null) ? 0 : Arrays.deepHashCode(remainingKeys.toArray()));
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
			Node other = (Node) obj;
			if (locations == null) {
				if (other.locations != null)
					return false;
			} else if (!Arrays.deepEquals(locations.toArray(),other.locations.toArray()))
				return false;
			if (remainingKeys == null) {
				if (other.remainingKeys != null)
					return false;
			} else if (!Arrays.deepEquals(remainingKeys.toArray(), other.remainingKeys.toArray()))
				return false;
			return true;
		}

	}

	private static Set<Point> getNeighborPoints(Point location) {
		final Set<Point> neighborPoints = new HashSet<>();
		neighborPoints.add(new Point(location.x, location.y - 1));
		neighborPoints.add(new Point(location.x, location.y + 1));
		neighborPoints.add(new Point(location.x - 1, location.y));
		neighborPoints.add(new Point(location.x + 1, location.y));
		return neighborPoints;
	}

//	private List<Node> reconstructPath(Node mouth, Node target, Map<Node, Node> parentMap) {
//		LinkedList<Node> path = new LinkedList<Node>();
//		Node currNode = target;
//		while (!currNode.equals(mouth)) {
//			path.addFirst(currNode);
//			currNode = parentMap.get(currNode);
//		}
//		path.addFirst(mouth);
//		return path;
//	}

}
