package uk.ac.ncl.csc8199.control;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import uk.ac.ncl.csc8199.model.Tuple;
import uk.ac.ncl.csc8199.test.Test;

public class MO1W {

	private static double sum = 0;

	public Tuple createTuple() {

		Tuple tuple = new Tuple();
		Random random = new Random();

		tuple.setWaitingTime(Math.abs(random.nextInt() % 10));
		tuple.setTimestamp(TimeUnit.NANOSECONDS.toMicros(System.nanoTime()));

		return tuple;
	}

	public void controlTuple(Tuple tuple) {

		sum += tuple.getWaitingTime();
		Control.memory.add(tuple);

		while (removeExpiredTuples()) {

		}

	}

	public void AVG(LinkedList<Tuple> memory) {

		System.out.println("Size = " + memory.size());
		System.out.println("Amount = " + sum);
		System.out.println("AVG = " + sum / memory.size());

	}

	public boolean isExpired() {

		if (!Control.memory.isEmpty()) {

			if (Control.memory.getFirst().getTimestamp() < (getCurrentTime() - Test.windowSize)) {

				// System.out.println("Expired");

				return true;

			}
		} else {

			return false;
		}

		return false;
	}

	public boolean isLatest(Tuple temp) {

		if (temp.getTimestamp() > (getCurrentTime() - TimeUnit.MILLISECONDS
				.toMicros(Test.slideSize))) {

			// System.out.println("Latest");

			return true;
		}

		return false;
	}

	public Long getCurrentTime() {

		long currentTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());

		return currentTime;
	}

	public boolean removeExpiredTuples() {

		if (Control.memory.getFirst().getTimestamp() < (getCurrentTime() - Test.windowSize)) {

			sum -= Control.memory.getFirst().getWaitingTime();
			Control.memory.removeFirst();

			return true;
		}

		return false;
	}

}
