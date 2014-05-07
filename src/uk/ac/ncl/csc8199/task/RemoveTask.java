package uk.ac.ncl.csc8199.task;
import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.test.Test;


public class RemoveTask extends TimerTask{

	Control control = new Control();
	Test test = new Test();
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while(control.removeExpiredTuples(control.memory, test.windowSize)){
			
		}
		//System.out.println("-------------------");
	}

}
