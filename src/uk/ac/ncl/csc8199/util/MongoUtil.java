package uk.ac.ncl.csc8199.util;




import uk.ac.ncl.csc8199.model.SSMTuple;
import uk.ac.ncl.csc8199.model.Tuple;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
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
	
	public static void insert1W(Tuple tuple) {
		
		long timeStamp = tuple.getTimestamp();
		double waitingTime = tuple.getWaitingTime();
		
		BasicDBObject doc = new BasicDBObject("TimeStamp", timeStamp).
	            append("WaitingTime", waitingTime);
	         coll.insert(doc);
	       //System.out.println("Document inserted successfully");
	}
	
}
