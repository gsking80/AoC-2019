package king.greg.aoc2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day06 {
	
	Map<String, String> orbits = new HashMap<>();

	public Day06(FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					String[] bits = lineJustFetched.split("\\)");
					orbits.put(bits[1], bits[0]);
				}
			}
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}
	
	public int getOrbitCount() {
		int count = 0;
		
		for (final String planet: orbits.keySet()) {
			count += getOrbits(planet).size();
		}
		
		return count;
	}
	
	private Set<String> getOrbits(final String startPlanet){
		final Set<String> planetOrbits = new HashSet<>();
		
		String planet = startPlanet;
		while(true) {
			planet = orbits.get(planet);
			if (null == planet) {
				return planetOrbits;
			}
			planetOrbits.add(planet);
		}
	}

	public int getTransfers(final String start, final String target) {
		final Set<String> orbits1 = getOrbits(start);
		final Set<String> orbits2 = getOrbits(target);
		
		final Set<String> orbits1backup = new HashSet<>(orbits1);
		
		orbits1.removeAll(orbits2);
		orbits2.removeAll(orbits1backup);
		return orbits1.size() + orbits2.size();
	}

}
