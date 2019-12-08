package king.greg.aoc2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day08 {
	
	final List<String> layers = new ArrayList<>();
	
	public Day08(FileReader fileReader, final int width, final int height) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					for (int i = 0; i < lineJustFetched.length(); i += width * height) {
						layers.add(lineJustFetched.substring(i, i + (width * height)));
					}
				}
			}
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}

	public long getSolution1(final char testDigit) {
		final String wantedLayer = Collections.min(layers, Comparator.comparing(s -> s.chars().filter(ch -> ch == testDigit).count()));
		final long ones = wantedLayer.chars().filter(ch -> ch == '1').count();
		final long twos = wantedLayer.chars().filter(ch -> ch == '2').count();
		
		return ones * twos;
	}

	public void getSolution2() {
		for (int i = 0; i < 25*6; i++) {
			if (i % (25) == 0 ) {
				System.out.println();
			}
			for (final String layer: layers) {
				if (layer.charAt(i) != '2') {
					System.out.print(layer.charAt(i) == '1' ? '*' : ' ');
					break;
				}
			}
		}
	}

}
