package uk.ac.ncl.csc8199.control;

import java.util.Iterator;
import java.util.LinkedList;

import uk.ac.ncl.csc8199.model.Tuple;

public class MO1W {
	
	public static double amount = 0;
	
	public void AVG(LinkedList<Tuple> memory) {
			
		for(Iterator<Tuple> i = memory.iterator(); i.hasNext(); ){
			
			Tuple temp = i.next();
			amount+=temp.getWaitingTime();
		}
		
		System.out.println("Size = " + memory.size());
		System.out.println("Amount = " + amount);
		System.out.println("AVG = " + amount/memory.size());

	}
	
	public boolean removeExpiredTuples(LinkedList<Tuple> memory, Long slideSize, Long windowSize) {
		
		long latestTuple = System.nanoTime() - windowSize;
		long oldestTuple = memory.getFirst().getTimestamp();
		
		if(oldestTuple < latestTuple) {
			
			Control.memory.removeFirst();
			return true;
		}
		return false;
		
	}

}
