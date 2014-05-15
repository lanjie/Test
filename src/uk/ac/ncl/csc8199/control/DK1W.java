package uk.ac.ncl.csc8199.control;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import uk.ac.ncl.csc8199.model.SSMTuple;
import uk.ac.ncl.csc8199.model.Tuple;
import uk.ac.ncl.csc8199.test.Test;
import uk.ac.ncl.csc8199.util.MongoUtil;

public class DK1W{

	private static double sum;
	private static double size;
	private static boolean flag = true;
	private static long timestamp;

	public Tuple createTuple() {

		Tuple tuple = new Tuple();
		Random random = new Random();
		
		tuple.setWaitingTime(Math.abs(random.nextInt() % 10));
		tuple.setTimestamp(TimeUnit.NANOSECONDS.toMicros(System.nanoTime()));

				
		return tuple;
	}
	
	public void controlDB(Tuple tuple) {
		
		sum += tuple.getWaitingTime();
		size++;
		Control.memory.add(tuple);
		
		while(flag) {
			
			getOldestTimestamp();
		}
		
		while(isOldest()) {

			MongoUtil.insert1W(Control.memory.getFirst());
			Control.memory.remove();
		
		}
	}
	
	public boolean isOldest() {
		
		if(Control.memory.size() > 500000) {
			
			return true;
		}
		
		return false;
	}
	
	public void getOldestTimestamp() {
		
		timestamp = Control.memory.getFirst().getTimestamp();
		flag = false;
	}
	
	public boolean isExpiredInMongoDB() {
		
		ArrayList <Long> expiredTuples = new ArrayList<Long>();
		
		BasicDBObject field = new BasicDBObject();
		field.put("TimeStamp", timestamp);

		DBCursor cursor = MongoUtil.coll.find(field);
		while(cursor.hasNext()) {
			
			DBObject temp = cursor.next();
			expiredTuples.add((Long)temp.get("WaitingTime"));
			cursor.remove();
		}
		
		for(Long expiredTuple : expiredTuples) {
			
			sum-=expiredTuple;
			size--;
		}
		
		flag = true;
		return false;
	}
	
	public void AVG() {
		
		System.out.println("Size = " + size);
		System.out.println("Amount = " + sum);
		System.out.println("AVG = " + sum/size);

	}
	
}
