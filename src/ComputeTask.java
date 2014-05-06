import java.util.TimerTask;


public class ComputeTask extends TimerTask{

	
	Control control = new Control();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		control.AVG(control.memory);
	}

}
