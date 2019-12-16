package king.greg.aoc2019;

import java.util.Vector;

public class Day16 {

	static final int[] BASE_PATTERN = new int[] { 0, 1, 0, -1 };

	public static String fft(final String input, final int phases) {
		return fft(input, phases, 1, false);
	}

	public static String fft(final String input, final int phases, final int repeat, final boolean useOffset) {
		
		int offset = 0;
		
		if (useOffset) {
			offset = Integer.valueOf(input.substring(0,7));
		}

		final StringBuilder sb1 = new StringBuilder();
		for (int i = 0; i < repeat; i++) {
			sb1.append(input);
		}
		String current = sb1.toString();

		// String current = input;

		for (int phase = 0; phase < phases; phase++) {
			final Vector<Integer> partialSums = new Vector<>();
			partialSums.add(0);
			for (final Character character :  current.toCharArray()) {
				int partialSum = character - '0';
				if (!partialSums.isEmpty()) {
					partialSum += partialSums.lastElement();
				}
				partialSums.add(partialSum);
			}
			final StringBuilder sb = new StringBuilder();

			for (int element = 0; element < current.length(); element++) {
				
				int sum = 0;
				int length = element + 1;
				int pl = 0;
				for (int j=0;;j++) {
					int pos = (j+1)*length-1;
					if (pos >= current.length()) {
						sum += (partialSums.get(current.length()) - partialSums.get(pl)) * BASE_PATTERN[j%4]; 
						break;
					}
					sum += (partialSums.get(pos) - partialSums.get(pl)) * BASE_PATTERN[j%4];
					pl = pos;
				}
				sb.append(Math.abs(sum % 10));
				
				
				
//				int patternIndex = 0;
//				int timesUsed = 1;
//				int value = 0;
//				for (int characterNumber = 0; characterNumber < current.length(); characterNumber++) {
//					if (timesUsed > element) {
//						timesUsed = 0;
//						patternIndex = (patternIndex + 1) % 4;
//					}
//					value += (current.charAt(characterNumber) - '0') * BASE_PATTERN[patternIndex];
//					timesUsed++;
//				}
//				sb.append(Math.abs(value % 10));
//				System.out.println(sb.toString());

			}
			current = sb.toString();
			System.out.println((phase + 1));
		}

		return current.substring(offset, offset + 8);
	}

}
