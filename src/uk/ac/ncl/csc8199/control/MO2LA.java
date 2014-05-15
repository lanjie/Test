package uk.ac.ncl.csc8199.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import uk.ac.ncl.csc8199.model.SSMTuple;
import uk.ac.ncl.csc8199.model.Tuple;
import uk.ac.ncl.csc8199.task.MongoTask;
import uk.ac.ncl.csc8199.test.Test;
import uk.ac.ncl.csc8199.util.MongoUtil;

public class MO2LA {
	
	public static double sum = 0;
	public static double size = 0;

	
	public Tuple createTuple() {

		Tuple tuple = new Tuple();
		Random random = new Random();
		
		tuple.setWaitingTime(Math.abs(random.nextInt() % 10));
		tuple.setTimestamp(TimeUnit.NANOSECONDS.toMicros(System.nanoTime()));

				
		return tuple;
	}
	
	public void SSM(LinkedList<Tuple> memory) {
		
		double amount = 0;
		double count = 0;
		long startTime = memory.getFirst().getTimestamp();
		
		SSMTuple ssmTuple = new SSMTuple();
		
		for(Iterator<Tuple> i = memory.iterator(); i.hasNext(); ){
			
			
			if(startTime < (startTime + TimeUnit.SECONDS.toMillis(1))) {
			
				Tuple temp = i.next();
				amount+=temp.getWaitingTime();				
				count++;
				i.remove();
			}

		}
		
		ssmTuple.setAmonut(amount);
		ssmTuple.setSSMtimestamp(startTime);
		ssmTuple.setSampleSize(count);
		Control.SSMMemory.add(ssmTuple);
		
	}
	
	public Double controlSSMTuple(SSMTuple SSMTuple) {
		
		sum += SSMTuple.getAmonut();
		size += SSMTuple.getSampleSize();
		Control.SSMMemory.add(SSMTuple);
		
		while(removeExpiredTuples()){
			
		}
				
		return sum;

		
	}
	
	public boolean removeExpiredTuples() {
		
		if(Control.SSMMemory.getFirst().getSSMtimestamp() < (getCurrentTime() - Test.windowSize)) {
			
			sum -= Control.SSMMemory.getFirst().getAmonut();
			Control.memory.removeFirst();
			
			return true;
		}
		
		return false;
	}
	
	public Long getCurrentTime() {
		
		long currentTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
		
		return currentTime;
	}

}
