package king.greg.aoc2019;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day10 {

	Set<Point> asteroids = new HashSet<>();
	int sizeX;
	int sizeY;

	public Day10(FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			int y = 0;
			int x = 0;
			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					for (x = 0; x < lineJustFetched.length(); x++) {
						if (lineJustFetched.charAt(x) == '#') {
							asteroids.add(new Point(x, y));
						}
					}
				}
				y++;
			}
			sizeX = x;
			sizeY = y;
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}

	public int besteroidCount() {
		int bestCount = 0;
		for (final Point testeroid : asteroids) {
			int count = 0;
			for (final Point otheroid : asteroids) {
				if (testeroid.equals(otheroid)) {
					continue;
				}
				int xDiff = otheroid.x - testeroid.x;
				int yDiff = otheroid.y - testeroid.y;
				boolean found = false;
				if (xDiff == 0) {
					for (int y = Math.min(otheroid.y, testeroid.y) + 1; y < Math.max(otheroid.y, testeroid.y); y++) {
						if (asteroids.contains(new Point(testeroid.x, y))) {
							found = true;
							break;
						}
					}
				} else if (yDiff == 0) {
					for (int x = Math.min(otheroid.x, testeroid.x) + 1; x < Math.max(otheroid.x, testeroid.x); x++) {
						if (asteroids.contains(new Point(x, testeroid.y))) {
							found = true;
							break;
						}
					}
				} else { // the slope...
					float slope = (float) yDiff / (float) xDiff;
					if (xDiff > 0) {
						for (int x = testeroid.x + 1; x < otheroid.x; x++) {
							if (yDiff > 0) {
								for (int y = testeroid.y + 1; y < otheroid.y; y++) {
									if (slope == (float) (y - testeroid.y) / (float) (x - testeroid.x)
											&& asteroids.contains(new Point(x, y))) {
										found = true;
										break;
									}
								}
							} else {
								for (int y = testeroid.y - 1; y > otheroid.y; y--) {
									if (slope == (float) (y - testeroid.y) / (float) (x - testeroid.x)
											&& asteroids.contains(new Point(x, y))) {
										found = true;
										break;
									}
								}
							}
						}
					} else {
						for (int x = testeroid.x - 1; x > otheroid.x; x--) {
							if (yDiff > 0) {
								for (int y = testeroid.y + 1; y < otheroid.y; y++) {
									if (slope == (float) (y - testeroid.y) / (float) (x - testeroid.x)
											&& asteroids.contains(new Point(x, y))) {
										found = true;
										break;
									}
								}
							} else {
								for (int y = testeroid.y - 1; y > otheroid.y; y--) {
									if (slope == (float) (y - testeroid.y) / (float) (x - testeroid.x)
											&& asteroids.contains(new Point(x, y))) {
										found = true;
										break;
									}
								}
							}
						}
					}

				}
				if (!found) {
					count++;
				}
			}
//			System.out.println(testeroid.toString() + " - " + count);
			if (count > bestCount) {
				bestCount = count;
			}
		}

		return bestCount;
	}

	public int destroy(final int nth, final Point laserLocation) {

		final List<Point> asteroidsToDestroy;
		asteroidsToDestroy = asteroids.stream().filter(a -> !a.equals(laserLocation))
				.sorted(Comparator
						.comparing((Point a) -> (Math.atan2(a.x - laserLocation.x, laserLocation.y - a.y)) >= 0
								? (Math.atan2(a.x - laserLocation.x, laserLocation.y - a.y))
								: (Math.atan2(a.x - laserLocation.x, laserLocation.y - a.y)) + 2 * Math.PI)
						.thenComparing((Point a) -> Math.abs(a.x - laserLocation.x) + Math.abs(laserLocation.y - a.y)))
				.collect(Collectors.toList());
		final Set<Point> destroyederoids = new HashSet<>();
		while(true) {
			double angle = 1000;
			for(final Point asteroid: asteroidsToDestroy) {
				if ((Math.atan2(asteroid.x - laserLocation.x, laserLocation.y - asteroid.y)) != angle && !destroyederoids.contains(asteroid)) {
					angle = (Math.atan2(asteroid.x - laserLocation.x, laserLocation.y - asteroid.y));
					destroyederoids.add(asteroid);
					if (destroyederoids.size() == 200) {
						return asteroid.x *100 + asteroid.y;
					}
				}
			}
		}
	}
}
