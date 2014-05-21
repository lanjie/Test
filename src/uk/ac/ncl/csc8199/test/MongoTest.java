package uk.ac.ncl.csc8199.test;

import java.net.UnknownHostException;
import java.util.Iterator;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.model.SSMTuple;
import uk.ac.ncl.csc8199.model.Tuple;
import uk.ac.ncl.csc8199.util.MongoUtil;


public class MongoTest {

	public static void main(String[] args) {
		
		MongoUtil.init1W();

		for (int i = 0; i < 30;  i++) {
			
			MongoUtil.getTupleFromMongoDB();
		}
	}
}
