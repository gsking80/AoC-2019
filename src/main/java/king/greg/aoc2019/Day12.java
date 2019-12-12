package king.greg.aoc2019;

import java.util.HashSet;
import java.util.Set;
import java.util.function.IntPredicate;

public class Day12 {
	
	final Set<Moon> moons = new HashSet<>();
	
	private void updateVelocities() {
		for (final Moon moon: moons) {
			for (final Moon otherMoon: moons) {
				if (moon.equals(otherMoon)) {
					continue;
				}
				moon.applyGravity(otherMoon);
			}
		}
	}
	
	private void updatePositions() {
		for (final Moon moon: moons) {
			moon.updatePosition();
		}
	}
	
	private int calculateEnergy() {
		int energy = 0;
		
		for (final Moon moon: moons) {
			energy += moon.getEnergy();
		}
		
		return energy;
	}
	
	public int simulate(final int steps) {
		for (int i = 0; i < steps; i++) {
			updateVelocities();
			updatePositions();
		}
		return calculateEnergy();
	}

	public IntPredicate firstRepeat() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addMoon(final int x, final int y, final int z) {
		moons.add(new Moon(x,y,z));
	}
	
	public class Moon {

		int xLoc;
		int yLoc;
		int zLoc;
		int xVel;
		int yVel;
		int zVel;
		
		public Moon(final int x, final int y, final int z) {
			xLoc = x;
			yLoc = y;
			zLoc = z;
		}

		public int getEnergy() {
			return (Math.abs(xLoc) + Math.abs(yLoc) + Math.abs(zLoc)) * (Math.abs(xVel) + Math.abs(yVel) + Math.abs(zVel));
		}

		public void updatePosition() {
			xLoc += xVel;
			yLoc += yVel;
			zLoc += zVel;
		}

		public void applyGravity(final Moon otherMoon) {
			if (xLoc < otherMoon.xLoc) {
				xVel++;
			} else if (xLoc > otherMoon.xLoc) {
				xVel--;
			}
			
			if (yLoc < otherMoon.yLoc) {
				yVel++;
			} else if (yLoc > otherMoon.yLoc) {
				yVel--;
			}
			
			if (zLoc < otherMoon.zLoc) {
				zVel++;
			} else if (zLoc > otherMoon.zLoc) {
				zVel--;
			}
		}
		
	}

}
