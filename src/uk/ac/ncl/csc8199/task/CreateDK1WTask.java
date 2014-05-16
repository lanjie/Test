package uk.ac.ncl.csc8199.task;

import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.DK1W;
import uk.ac.ncl.csc8199.control.MO1W;

/**   
 *    
 * Project Name��Test   
 * Class Name��CreateDK1WTask   
 * Author��Jie Lan  
 * Time��2014��5��15�� ����10:50:39   
 * @version    
 *    
 */
public class CreateDK1WTask extends TimerTask{

	DK1W dk1w = new DK1W();

	
	 /* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 200; i++) {
			dk1w.controlDB((dk1w.createTuple()));
		}
	}
	
}
