package uk.ac.ncl.csc8199.control;

import java.util.ArrayList;
import java.util.LinkedList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import uk.ac.ncl.csc8199.model.SSMTuple;
import uk.ac.ncl.csc8199.model.Tuple;
import uk.ac.ncl.csc8199.test.Test;
import uk.ac.ncl.csc8199.util.MongoUtil;

public class DK2LA extends MO2LA{
	
	private static double sum;
	private static double size;
	private static boolean flag = true;
	private static long timestamp;
	public static double count;
	public static long dbOffset = 0;
	public static LinkedList<Tuple> waitingList = new LinkedList<Tuple>();
	MO1W mo1w = new MO1W();
	MO2LA mo2la = new MO2LA();
	
	public void controlDB(SSMTuple ssmTuple) {

		if (!isFull() && dbOffset <= 0) {

			Control.SSMMemory.add(ssmTuple);
			sum += ssmTuple.getAmonut();
			size += ssmTuple.getSampleSize();
		} else {

			MongoUtil.insert2LAWithSingle(ssmTuple);
			sum += ssmTuple.getAmonut();
			size += ssmTuple.getSampleSize();
			// waitingList.add(tuple);
		}

		if (isExpired()) {

			sum -= Control.SSMMemory.getFirst().getAmonut();
			Control.SSMMemory.removeFirst();
			size -= Control.SSMMemory.getFirst().getSampleSize();
			MongoUtil.getSSMTupleFromMongoDB();

		}

	}
	
	public boolean isFull() {

		if (Control.SSMMemory.size() > 5) {

			return true;
		}

		return false;
	}
	
	public boolean isExpired() {

		if (!Control.SSMMemory.isEmpty()) {

			if (Control.SSMMemory.getFirst().getSSMtimestamp() < (getCurrentTime() - Test.windowSize)) {

				// System.out.println("Expired");

				return true;

			}
		} else {

			return false;
		}

		return false;
	}
	
	public void AVG() {

		System.out.println("Size = " + size);
		System.out.println("Amount = " + sum);
		System.out.println("AVG = " + sum / size);

	}

}
