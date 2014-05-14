package uk.ac.ncl.csc8199.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import uk.ac.ncl.csc8199.model.Tuple;
import uk.ac.ncl.csc8199.task.MongoTask;
import uk.ac.ncl.csc8199.test.Test;
import uk.ac.ncl.csc8199.util.MongoUtil;

public class MO2LA {
	
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
		
		BasicDBObject query = new BasicDBObject();
		BasicDBObject field = new BasicDBObject();
		field.put("TimeStamp", (System.currentTimeMillis() - Test.windowSize));
		ArrayList <Long> tempMemory = new ArrayList<Long>();
		
		DBCursor cursor = MongoUtil.coll.find(query, field);
		while(cursor.hasNext()) {
			
			DBObject temp = cursor.next();
			tempMemory.add((Long)temp.get("WaitingTime"));
			cursor.remove();
			
			
		}

	}
	
	public void removeFromMongoDB() {
		
		
	}

}
