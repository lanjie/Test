package uk.ac.ncl.csc8199.test;

import java.net.UnknownHostException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.mongodb.DBCollection;

import uk.ac.ncl.csc8199.task.*;
import uk.ac.ncl.csc8199.util.MongoUtil;



public class Test{


	public static long windowSize = TimeUnit.SECONDS.toMillis(3600);
	public static long slideSize = TimeUnit.SECONDS.toMillis(1);

	
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub

/*
 * ---------------------------This is 1W test block----------------------------------
 */
		
		
/*		Timer timer = new Timer();
		
		timer.schedule(new CreateTask(), 0, 1);
		timer.schedule(new ComputeTask(), 1000, Test.slideSize);
		timer.schedule(new RemoveTask(), 1, Test.windowSize);*/
		

/*
 * ---------------------------This is 2LA test block----------------------------------
 */
		
		Timer timer = new Timer();
		MongoUtil.init();
		
		timer.schedule(new CreateTask(), 0, 1);
		timer.schedule(new Core2LATask(), 10, 1000);
		timer.schedule(new MongoTask(), 500, 1000);
		timer.schedule(new Compute2LATask(), 1000, Test.slideSize);
		timer.schedule(new RemoveTask(), 1, Test.windowSize);
	}

	

}
