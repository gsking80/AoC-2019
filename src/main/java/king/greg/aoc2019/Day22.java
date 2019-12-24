package king.greg.aoc2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collector;

public class Day22 {

	Deque<Integer> deck;
	Long deckSize;
	final List<String> shuffles;

	public Day22(final FileReader fileReader) {

		shuffles = new ArrayList<>();
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					shuffles.add(lineJustFetched);
				}
			}

			System.out.println(deck);

		} catch (IOException ioe) {
			throw new RuntimeException();
		}

	}

	public void shuffle(final int deckSize) {
		deck = new ArrayDeque<>(deckSize);

		// Initialize Deck
		for (int i = 0; i < deckSize; i++) {
			deck.add(i);
		}

		System.out.println(deck);
		for (final String shuffle : shuffles) {
			processMove(shuffle);
		}
	}
	
	public int positionOfCard(final int cardNumber) {
		return new ArrayList<>(deck).indexOf(cardNumber);
	}

	private void processMove(final String move) {
		final String[] moveParts = move.split(" ");
		switch (moveParts[0]) {
		case "cut":
			final int number = Integer.valueOf(moveParts[1]);
			if (number > 0) {
				for (int i = 0; i < number; i++) {
					deck.add(deck.remove());
				}
			} else {
				for (int i = 0; i > number; i--) {
					deck.addFirst(deck.removeLast());
				}
			}
			break;
		case "deal":
			if (moveParts[1].equals("with")) {
				final int increment = Integer.valueOf(moveParts[3]);
				final int size = deck.size();
				final Integer[] newDeck = new Integer[size];
				int position = 0;
				while (!deck.isEmpty()) {
					newDeck[position] = deck.remove();
					position = (position + increment) % size;
				}
				deck.addAll(Arrays.asList(newDeck));
			} else if (moveParts[1].equals("into")) {
				deck = deck.stream().collect(Collector.of(ArrayDeque::new, (deq, t) -> deq.addFirst(t), (d1, d2) -> {
					d2.addAll(d1);
					return d2;
				}));
			} else {
				throw new RuntimeException("Don't know move: " + move);
			}
			break;
		default:
			throw new RuntimeException("Don't know move: " + move);
		}
	}

	public BigInteger seekPosition(BigInteger deckSize, BigInteger timesShuffled, int position) {
		BigInteger[] calc = new BigInteger[] { num(1), num(0) };
		Collections.reverse(shuffles);
		for (String shuffle : (shuffles)) {
			reverseProcess(calc, shuffle, deckSize);
			for (int i = 0; i < calc.length; i++)
				calc[i] = calc[i].mod(deckSize);
		}
		BigInteger pow = calc[0].modPow(timesShuffled, deckSize);
		return pow.multiply(num(position))
				.add(calc[1].multiply(pow.add(deckSize).subtract(num(1)))
						.multiply(calc[0].subtract(num(1)).modPow(deckSize.subtract(num(2)), deckSize)))
				.mod(deckSize);
	}

	private BigInteger num(long n) {
		return new BigInteger(Long.toString(n));
	}

	private void reverseProcess(BigInteger[] input, final String move, final BigInteger deckSize) {
		final String[] moveParts = move.split(" ");
		switch (moveParts[0]) {
		case "cut":
			final int number = Integer.valueOf(moveParts[1]);
			input[1] = input[1].add(num(number));
			break;
		case "deal":
			if (moveParts[1].equals("with")) {
				final int increment = Integer.valueOf(moveParts[3]);
				BigInteger p = num(increment).modPow(deckSize.subtract(num(2)), deckSize);
				for (int i = 0; i < input.length; i++) {
					input[i] = input[i].multiply(p);
				}
			} else if (moveParts[1].equals("into")) {
				input[0] = input[0].multiply(num(-1));
				input[1] = input[1].add(num(1)).multiply(num(-1));
			} else {
				throw new RuntimeException("Don't know move: " + move);
			}
			break;
		default:
			throw new RuntimeException("Don't know move: " + move);
		}
	}

}
