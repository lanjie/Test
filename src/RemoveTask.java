import java.util.TimerTask;


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
