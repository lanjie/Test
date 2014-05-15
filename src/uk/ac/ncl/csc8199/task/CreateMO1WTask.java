package uk.ac.ncl.csc8199.task;
import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.control.MO1W;



public class CreateMO1WTask extends TimerTask{

	MO1W mo1w = new MO1W();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//for(int i = 0; i < 1; i++) {
			mo1w.controlTuple((mo1w.createTuple()));
		//}
		

		
	}

}
