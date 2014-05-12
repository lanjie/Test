package uk.ac.ncl.csc8199.control;

import java.util.LinkedList;

import uk.ac.ncl.csc8199.model.SSMTuple;

public class Control2LA {

	public boolean removeExpiredTuples(LinkedList<SSMTuple> SSMMemory, Long windowSize) {
		
		long latestTuple = System.currentTimeMillis();
		long oldestTuple = SSMMemory.getFirst().SSMtimestamp + windowSize;
		
		if(oldestTuple < latestTuple) {
			
			Control.SSMMemory.removeFirst();
			return true;
		}
		return false;
		
	}
}
