package uk.ac.ncl.csc8199.test;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.task.*;



public class Test{


	public static long windowSize = TimeUnit.SECONDS.toMillis(10);
	public static long slideSize = TimeUnit.SECONDS.toMillis(1);

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

/*
 * ---------------------------This is 1W test block----------------------------------
 */
		
		
		Control c = new Control();
		Timer timer = new Timer();
		
		timer.schedule(new CreateTask(), 0, 1);
		timer.schedule(new ComputeTask(), 1000, Test.slideSize);
		timer.schedule(new RemoveTask(), 1, Test.windowSize);


/*
 * ---------------------------This is 2LA test block----------------------------------
 */
		
/*		Control c = new Control();
		Timer timer = new Timer();
		
		timer.schedule(new CreateTask(), 0, 1);
		timer.schedule(new Core2LATask(), 10, 1000);
		timer.schedule(new Compute2LATask(), 1000, Test.slideSize);
		//timer.schedule(new RemoveTask(), 1, Test.windowSize);
	}*/

	}	

}
