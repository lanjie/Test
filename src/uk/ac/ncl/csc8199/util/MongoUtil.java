package uk.ac.ncl.csc8199.util;




import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import uk.ac.ncl.csc8199.control.DK1W;
import uk.ac.ncl.csc8199.model.SSMTuple;
import uk.ac.ncl.csc8199.model.Tuple;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class MongoUtil {
	
	public static MongoClient mongoClient;
	public static DB db;
	public static DBCollection coll;

	public static void init1W() {
		
		try{   
			mongoClient = new MongoClient( "localhost" , 27017 );
	        db = mongoClient.getDB( "SlideM" );
	        System.out.println("Connect to database successfully");        
	        coll = db.getCollection("Tuple");
	        System.out.println("Collection mycol selected successfully");
	         

	      }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }


		
	}
	
	public static void init2LA() {
		
		try{   
			mongoClient = new MongoClient( "localhost" , 27017 );
	         // 连接到数据库
	         db = mongoClient.getDB( "SlideM" );
		 System.out.println("Connect to database successfully");        
	         coll = db.getCollection("SSMTuple");
	         System.out.println("Collection mycol selected successfully");
	         

	      }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }


		
	}
	
	public static void insert2LA(SSMTuple ssmTuple) {
		
		long timeStamp = ssmTuple.getSSMtimestamp();
		double amount = ssmTuple.getAmonut();
		double sampleSize = ssmTuple.getSampleSize();
		
		BasicDBObject doc = new BasicDBObject("TimeStamp", timeStamp).
	            append("Amount", amount).
	            append("SampleSize", sampleSize);
	         coll.insert(doc);
	         System.out.println("Document inserted successfully");
	}
	
	public static void insert1WWithList() {
		
		//ArrayList<DBObject> arraylist = new ArrayList<DBObject>();
		
		BasicDBObject updateObject = new BasicDBObject();
		ArrayList<DBObject> arrayList = mapTuples(DK1W.waitingList);
		//updateObject.append("Tuple", new BasicDBObject("Tuple", result));
		
		coll.insert(arrayList);
		DK1W.waitingList.clear();
		
		
/*		long timeStamp = tuple.getTimestamp();
		double waitingTime = tuple.getWaitingTime();
		
		BasicDBObject doc = new BasicDBObject("TimeStamp", timeStamp).
	            append("WaitingTime", waitingTime);
	         coll.insert(waitingList);
	       //System.out.println("Document inserted successfully");
*/	

	}
	
	public static void insert1WWithSingle(Tuple tuple) {
		
		long timeStamp = tuple.getTimestamp();
		double waitingTime = tuple.getWaitingTime();
		
		BasicDBObject doc = new BasicDBObject("TimeStamp", timeStamp).
	            append("WaitingTime", waitingTime);
	         coll.insert(doc);
	      // System.out.println("Document inserted successfully");
	}
	
	public static ArrayList<DBObject> mapTuples(LinkedList<Tuple> waitingList) {
		  
		ArrayList<DBObject> arraylist = new ArrayList<DBObject>();
		
		BasicDBList result = new BasicDBList();
		  for (Tuple tuple: waitingList) {
		    DBObject doc = new BasicDBObject("TimeStamp", tuple.getTimestamp()).
		    		append("WaitingTime", tuple.getWaitingTime());
		    arraylist.add(doc);
		  }
		  
		  return arraylist;
	}
	
}
