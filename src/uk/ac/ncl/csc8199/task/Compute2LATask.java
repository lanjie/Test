package uk.ac.ncl.csc8199.task;

import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.Control;

public class Compute2LATask extends TimerTask{

	Control control = new Control();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		control.SSMAVG(Control.SSMMemory);
	}

	
}
