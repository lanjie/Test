package uk.ac.ncl.csc8199.task;

import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.Control;

public class Core2LATask extends TimerTask {

	Control control = new Control();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		control.SSM(Control.memory);
	}

}
