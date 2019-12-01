package king.greg.aoc2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day01 {
	
	long fuel;
	long fuel2;

	public Day01(FileReader fileReader) {
        try {
            final BufferedReader buf = new BufferedReader(fileReader);
            fuel = 0;

            while(true) {
                final String lineJustFetched = buf.readLine();
                if(null == lineJustFetched) {
                    break;
                } else {
                    long mass = Long.valueOf(lineJustFetched);
                    long requiredFuel = Math.floorDiv(mass, 3) -2;
                    fuel += requiredFuel;
                    fuel2 += calcTotalFuel(mass);
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException();
        }
	}
	
	long calcTotalFuel(long mass) {
		long newFuel = 0;
		long currentMass = mass;

		while (true) {
			currentMass = Math.floorDiv(currentMass, 3) - 2;
			if (currentMass > 0) {
				newFuel += currentMass;
			} else {
				break;
			}
		}
		
		return newFuel;
	}
	
	public long getFuel() {
		return fuel;
	}

	public long getFuel2() {
		return fuel2;
	}
	
}
