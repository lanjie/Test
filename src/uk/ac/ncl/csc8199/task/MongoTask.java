package uk.ac.ncl.csc8199.task;

import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.model.SSMTuple;
import uk.ac.ncl.csc8199.util.MongoUtil;

public class MongoTask extends TimerTask{

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		SSMTuple ssmTuple = new SSMTuple();
		ssmTuple = Control.SSMMemory.getLast();

		
		if (ssmTuple != null) {
			
			MongoUtil.insert(ssmTuple);
		}


		
	}

	
}
