package uk.ac.ncl.csc8199.control;

import uk.ac.ncl.csc8199.model.Tuple;
import uk.ac.ncl.csc8199.test.Test;
import uk.ac.ncl.csc8199.util.MongoUtil;

public class Memory2LA {
	
	public void insertToMongoDB() {
		
		Tuple tuple = Control.memory.getFirst();
		long startTime = tuple.getTimestamp();
		long endTime = Control.memory.getLast().getTimestamp();
		
		if (startTime + Test.windowSize > endTime) {
			
			MongoUtil.insert1W(tuple);
		}
	}
	
	public void removeFromMongoDB() {
		
		
	}

}
