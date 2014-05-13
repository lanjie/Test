package uk.ac.ncl.csc8199.test;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.model.SSMTuple;
import uk.ac.ncl.csc8199.util.MongoUtil;


public class MongoTest {
	public static void main( String args[] ){
	
/*		try{   
			 // 连接到 mongodb 服务
	         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	         // 连接到数据库
	         DB db = mongoClient.getDB( "SlideM" );
		 System.out.println("Connect to database successfully");
       
	         DBCollection coll = db.getCollection("SSMTuple");
	         System.out.println("Collection mycol selected successfully");
	         BasicDBObject doc = new BasicDBObject("title", "MongoDB").
	            append("description", "database").
	            append("likes", 100).
	            append("url", "http://www.w3cschool.cc/mongodb/").
	            append("by", "w3cschool.cc");
	         coll.insert(doc);
	         System.out.println("Document inserted successfully");
	      }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }*/
		SSMTuple ssmTuple = new SSMTuple(0, 0, 0);
		
		MongoUtil.insert(ssmTuple);
	
	}
}
