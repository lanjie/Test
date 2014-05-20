package uk.ac.ncl.csc8199.task;

import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.control.MO1W;
import uk.ac.ncl.csc8199.control.MO2LA;

public class CreateMO2LATask extends TimerTask {

	MO2LA mo2la = new MO2LA();

	@Override
	public void run() {
		// TODO Auto-generated method stub

		mo2la.insertTuplesIntoMemery(mo2la.createTuple());

	}

}
