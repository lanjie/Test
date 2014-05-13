package uk.ac.ncl.csc8199.util;




import uk.ac.ncl.csc8199.model.SSMTuple;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class MongoUtil {
	
	private static MongoClient mongoClient;
	private static DB db;
	private static DBCollection coll;

	public static void init() {
		
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
	
	public static void insert(SSMTuple ssmTuple) {
		
		long timeStamp = ssmTuple.getSSMtimestamp();
		double amount = ssmTuple.getAmonut();
		double sampleSize = ssmTuple.getSampleSize();
		
		BasicDBObject doc = new BasicDBObject("TimeStamp", timeStamp).
	            append("Amount", amount).
	            append("SampleSize", sampleSize);
	         coll.insert(doc);
	         System.out.println("Document inserted successfully");
	}
	
}
