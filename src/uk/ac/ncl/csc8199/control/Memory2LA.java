package uk.ac.ncl.csc8199.control;

import com.mongodb.DB;

import uk.ac.ncl.csc8199.model.Tuple;
import uk.ac.ncl.csc8199.task.MongoTask;
import uk.ac.ncl.csc8199.test.Test;
import uk.ac.ncl.csc8199.util.MongoUtil;

public class Memory2LA {
	
	public void insertToMongoDB() {
		
		Tuple tuple = Control.memory.getFirst();
		long startTime = tuple.getTimestamp();
		long endTime = Control.memory.getLast().getTimestamp();
		boolean flag = true;
		
		while(flag) {
		
			if (startTime + Test.windowSize > endTime) {
				
				for (Tuple t : Control.memory)
				
				MongoUtil.insert1W(t);
			}
			
			flag = false;
			
		}
	}
	

	
	public void getFromMongoDB() {
		

	}
	
	public void removeFromMongoDB() {
		
		
	}

}
