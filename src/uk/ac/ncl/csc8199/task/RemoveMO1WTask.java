package uk.ac.ncl.csc8199.task;
import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.control.MO1W;
import uk.ac.ncl.csc8199.test.Test;


public class RemoveMO1WTask extends TimerTask{

	MO1W mo1w = new MO1W();

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while(mo1w.removeExpiredTuples(Control.memory, Test.windowSize)){
			
		}

	}

}
