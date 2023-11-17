package king.greg.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day12 {

    final List<Moon> moons = new ArrayList<>();

    private void updateVelocities() {
        for (final Moon moon : moons) {
            for (final Moon otherMoon : moons) {
                if (moon.equals(otherMoon)) {
                    continue;
                }
                moon.applyGravity(otherMoon);
            }
        }
    }

    private void updatePositions() {
        for (final Moon moon : moons) {
            moon.updatePosition();
        }
    }

    private int calculateEnergy() {
        int energy = 0;

        for (final Moon moon : moons) {
            energy += moon.getEnergy();
        }

        return energy;
    }

    public int simulate(final int steps) {
        for (int i = 0; i < steps; i++) {
            step();
        }
        printMoons();
        return calculateEnergy();
    }

    private void printMoons() {
        for(final Moon moon: moons) {
            System.out.println(Arrays.toString(moon.locations) + " - " + Arrays.toString(moon.velocities));
        }
    }

    private void step() {
		updateVelocities();
		updatePositions();
	}

    public long firstRepeat() {
        final long[] periods = new long[3];
        final List<Set<List<Integer>>> states = new ArrayList<>();
        for (long i = 0; i < 3; i++) {
        	states.add(new HashSet<>());
		}
		int steps = 0;
        while(periods[0] == 0 || periods[1] == 0 || periods[2] == 0) {

			for (int i = 0; i < 3; i++){
				if (periods[i] != 0) {
					continue;
				}
				List<Integer> state = new ArrayList<>();
				for (final Moon moon: moons) {
					state.add(moon.locations[i]);
					state.add(moon.velocities[i]);
				}
				if (states.get(i).contains(state)) {
                        periods[i] = steps;
				} else {
					states.get(i).add(state);
				}
			}

			step();
			steps++;
		}

        return lcm(periods);
    }

    private static long lcm(final long[] array) {
        long lcm = 1;
        for (long value : array) {
            lcm = lcm(lcm, value);
        }
        return lcm;
    }

    private static long lcm(final long a, final long b) {
        if (a == 0 || b == 0) {
        	return 0;
		}
    	return ((a * b) / gcd(a, b));
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return 0;
        } else if (a < b) {
            return gcd(b, a);
        } else {
            while (b > 0) {
                long tempA = a;
                a = b;
                b = tempA % b;
            }
            return a;
        }
    }

    public void addMoon(final int x, final int y, final int z) {
        moons.add(new Moon(x, y, z));
    }

    public class Moon {

    	int[] locations = new int[3];
    	int[] velocities = new int[3];

        public Moon(final int x, final int y, final int z) {
            locations[0] = x;
            locations[1] = y;
            locations[2] = z;
        }

        public int getEnergy() {
        	final int potential = Arrays.stream(locations).map(a -> Math.abs(a)).sum();
        	final int kinetic = Arrays.stream(velocities).map(a -> Math.abs(a)).sum();
            return potential * kinetic;
        }

        public void updatePosition() {
        	for (int i = 0; i < 3; i++) {
				locations[i] += velocities[i];
			}
        }

        public void applyGravity(final Moon otherMoon) {
        	for (int i = 0; i < 3; i++) {
        		if (locations[i] < otherMoon.locations[i]) {
        			velocities[i]++;
				} else if (locations[i] > otherMoon.locations[i]) {
        			velocities[i]--;
				}
			}
        }

    }

}
