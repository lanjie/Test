package uk.ac.ncl.csc8199.task;

import uk.ac.ncl.csc8199.control.*;
import uk.ac.ncl.csc8199.test.Test;

public class Remove2LATask {

	Control control = new Control();
	Control2LA control2la = new Control2LA();
	Test test = new Test();
	public void run() {
		// TODO Auto-generated method stub

		while(control2la.removeExpiredTuples(Control.SSMMemory, Test.windowSize)){
			
		}
		//System.out.println("-------------------");
	}
}
