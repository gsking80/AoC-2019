package king.greg.aoc2019;

public class Day04 {

	char[] lower;
	char[] upper;

	public Day04(final String lower, final String upper) {
		this.lower = lower.toCharArray();
		this.upper = upper.toCharArray();
	}

	public int numPasswords(final boolean allowTriples) {
		int passwordCount = 0;

		char[] potentialPassword = new String(lower).toCharArray();

		while (true) {

			// find next never decreasing
			for (int i = 1; i < potentialPassword.length; i++) {
				if (potentialPassword[i] < potentialPassword[i - 1]) {
					for (int j = i; j < potentialPassword.length; j++) {
						potentialPassword[j] = potentialPassword[i - 1];
					}
				}
			}

			// return if past boundary
			for (int i = 0; i < potentialPassword.length; i++) {
				if (potentialPassword[i] < upper[i]) {
					break;
				} else if (potentialPassword[i] > upper[i]) {
					return passwordCount;
				}
			}

			// is valid?
			if (allowTriples) {
				for (int i = 1; i < potentialPassword.length; i++) {
					if (potentialPassword[i] == potentialPassword[i - 1]) {
						passwordCount++;
//						System.out.println(potentialPassword);
						break;
					}
				}
			} else {
				for (int i = 1; i < potentialPassword.length; i++) {
					if (potentialPassword[i] == potentialPassword[i - 1]) {
						if ((i < 2 || potentialPassword[i - 2] != potentialPassword[i]) && (i == potentialPassword.length - 1 || potentialPassword[i+1] != potentialPassword[i])) {
							passwordCount++;
//							System.out.println(potentialPassword);
							break;
						}
					}
				}
			}

			// increment
			potentialPassword[potentialPassword.length - 1]++;
			for (int i = potentialPassword.length - 1; i > 0; i--) {
				if (potentialPassword[i] > '9') {
					potentialPassword[i] = '0';
					potentialPassword[i - 1]++;
				}
			}

		}

	}

}
