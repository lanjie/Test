package uk.ac.ncl.csc8199.task;
import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.data.OutputFile;
import uk.ac.ncl.csc8199.model.Tuple;


public class CreateTask extends TimerTask{

	Control control = new Control();	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1; i < 2; i++) {
			Control.memory = control.insertTuplesIntoMemery(control.createTuple());
		}
		

		
	}

}
