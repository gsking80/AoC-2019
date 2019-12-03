package king.greg.aoc2019;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day03 {

	List<Set<Point>> wires = new ArrayList<>();
	List<Map<Point,Integer>> wiresSteps = new ArrayList<>();

	public Day03(FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					Map<Point, Integer> wireSteps = new HashMap<>();
					Set<Point> wire = new HashSet<Point>();
					String[] directions = lineJustFetched.split(",");
					Point currentPoint = new Point(0, 0);
					int steps = 1;
					for (final String direction : directions) {
						int length = Integer.valueOf(direction.substring(1));
						for (int i = 1; i <= length; i++) {
							switch (direction.charAt(0)) {
							case 'U':
								currentPoint.y++;
								break;
							case 'D':
								currentPoint.y--;
								break;
							case 'L':
								currentPoint.x--;
								break;
							case 'R':
								currentPoint.x++;
								break;
							}
							wire.add(new Point(currentPoint));
							if (!wireSteps.containsKey(currentPoint)) {
								wireSteps.put(new Point(currentPoint), steps);
							}
							steps++;
						}
					}
					wires.add(wire);
					wiresSteps.add(wireSteps);
				}
			}
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}

	public int closestIntersection() {
		int closestIntersection = 0; 
		wires.get(0).retainAll(wires.get(1));
		for (final Point intersection: wires.get(0)) {
			int distance = Math.abs(intersection.x) + Math.abs(intersection.y);
			if (closestIntersection == 0 || distance < closestIntersection) {
				closestIntersection = distance;
			}
		}
		return closestIntersection;
	}
	
	public int fastestIntersection() {
		int fastestIntersection = 0;
		wires.get(0).retainAll(wires.get(1));
		for (final Point intersection: wires.get(0)) {
			int totalTime = wiresSteps.get(0).get(intersection) + wiresSteps.get(1).get(intersection);
			if (fastestIntersection == 0 || totalTime < fastestIntersection) {
				fastestIntersection = totalTime;
			}
		}
		return fastestIntersection;
	}

}
