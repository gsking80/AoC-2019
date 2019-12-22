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

	public int positionOfCard(final int cardNumber) {
		return new ArrayList<Integer>(deck).indexOf(cardNumber);
	}

	public long simulateGiantShuffle(final long deckSize, final long numberOfShuffles, final long position) {
		this.deckSize = deckSize;
		long movingPosition = position;
		Collections.reverse(shuffles);

		for (long shuffleNumber = numberOfShuffles; shuffleNumber > 0; shuffleNumber--) {
			for (final String shuffle : shuffles) {
				movingPosition = reverseProcess(shuffle, movingPosition);
			}

		}

		return movingPosition;
	}

	private long reverseProcess(final String move, final long movingPosition) {
		final String[] moveParts = move.split(" ");
		switch (moveParts[0]) {
		case "cut":
			final int number = Integer.valueOf(moveParts[1]);
				return (movingPosition + number + deckSize) % deckSize;
		case "deal":
			if (moveParts[1].equals("with")) {
				final int increment = Integer.valueOf(moveParts[3]);
/// YAY HARD MATH --- need to invert % here
			} else if (moveParts[1].equals("into")) {
				return (deckSize -1 ) - movingPosition;
			} else {
				throw new RuntimeException("Don't know move: " + move);
			}
			break;
		default:
			throw new RuntimeException("Don't know move: " + move);
		}
		return -1;
	}
	


}
