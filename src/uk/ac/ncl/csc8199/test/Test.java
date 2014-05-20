package uk.ac.ncl.csc8199.test;

import java.net.UnknownHostException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.mongodb.DBCollection;

import uk.ac.ncl.csc8199.task.*;
import uk.ac.ncl.csc8199.util.MongoUtil;

public class Test {

	public static long windowSize = TimeUnit.SECONDS.toMicros(10);
	public static long slideSize = TimeUnit.SECONDS.toMillis(1);

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub

		/*
		 * ---------------------------This is MO-1W test
		 * block----------------------------------
		 */

		/*
		 * Timer timer = new Timer(); //AtomicReference<Double> sum = new
		 * AtomicReference<Double>(); //AtomicReference<Integer> count = new
		 * AtomicReference<Integer>(); timer.schedule(new CreateMO1WTask(), 0,
		 * 1); timer.schedule(new ComputeMO1WTask(), 0, Test.slideSize);
		 * //timer.schedule(new RemoveMO1WTask(), 10, Test.slideSize);
		 */

		/*
		 * ---------------------------This is MO-2LA test
		 * block----------------------------------
		 */

		/*
		 * Timer timer = new Timer(); //AtomicReference<Double> sum = new
		 * AtomicReference<Double>(); //AtomicReference<Integer> count = new
		 * AtomicReference<Integer>(); timer.schedule(new CreateMO2LATask(), 0,
		 * 1); timer.schedule(new ComputeMO2LATask(), 1, Test.slideSize);
		 * //timer.schedule(new RemoveMO1WTask(), 10, Test.slideSize);
		 */
		/*
		 * ---------------------------This is DISK-1W test
		 * block----------------------------------
		 */

		Timer timer = new Timer();
		MongoUtil.init1W();

		timer.schedule(new CreateDK1WTask(), 0, 1);
		timer.schedule(new ComputeDK1WTask(), 0, Test.slideSize);

	}

}
