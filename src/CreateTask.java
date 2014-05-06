import java.util.TimerTask;


public class CreateTask extends TimerTask{

	Control control = new Control();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		control.memory = control.insertTuplesIntoMemery(control.createTuple());
		
	}

}
