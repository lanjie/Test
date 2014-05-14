package uk.ac.ncl.csc8199.control;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import uk.ac.ncl.csc8199.model.Tuple;

public class MO1W {
	
	public static double amount = 0;
	
	public void AVG(LinkedList<Tuple> memory, Long windowSize, Long slideSize) {
			
		long headEnd = getCurrentTime() - windowSize;
		long headStart = memory.getFirst().getTimestamp();
		long tailStart = memory.getFirst().getTimestamp() + windowSize;
		long tailEnd = getCurrentTime();
		
		for(Iterator<Tuple> i = memory.iterator(); i.hasNext(); ){
			
			Tuple temp = i.next();
			long tempStamp = temp.getTimestamp();
			
			if(tempStamp < headEnd) {
			
				amount-=temp.getWaitingTime();
				System.out.println("HEAD");
				

			}
			
			else if(tailStart < tempStamp && tempStamp < tailEnd) {
				
				amount+=temp.getWaitingTime();
				System.out.println("TAIL");
				

			}
			
			else if(tempStamp < tempStamp + windowSize) {
				
				amount+=temp.getWaitingTime();
				System.out.println("M");
			}
			 

		}
		
		System.out.println("Size = " + memory.size());
		System.out.println("Amount = " + amount);
		System.out.println("AVG = " + amount/memory.size());

	}
	
	public Long getCurrentTime() {
		
		long currentTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
		
		return currentTime;
	}
	
	public boolean removeExpiredTuples(LinkedList<Tuple> memory,Long windowSize) {
		
		long latestTuple = TimeUnit.NANOSECONDS.toMicros(System.nanoTime()) - windowSize;
		long oldestTuple = memory.getFirst().getTimestamp();
		
		if(oldestTuple < latestTuple) {
			
			Control.memory.removeFirst();
			return true;
		}
		return false;
		
	}

}
