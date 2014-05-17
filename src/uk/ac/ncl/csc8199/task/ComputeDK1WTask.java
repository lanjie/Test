package uk.ac.ncl.csc8199.task;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.control.DK1W;
import uk.ac.ncl.csc8199.control.MO1W;
import uk.ac.ncl.csc8199.data.OutputFile;
import uk.ac.ncl.csc8199.util.MongoUtil;

/**   
 *    
 * Project Name：Test   
 * Class Name：ComputeDK1WTask   
 * Author：Jie Lan  
 * Time：2014年5月15日 下午10:54:26   
 * @version    
 *    
 */
public class ComputeDK1WTask extends TimerTask{

	DK1W dk1w = new DK1W();
	OutputFile outputFile = new OutputFile();
	public long startTime;
	public long endTime;
	 /* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		dk1w.AVG();
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024 + "MB/"+ Runtime.getRuntime().freeMemory()/1024/1024 + "MB/" + Runtime.getRuntime().totalMemory()/1024/1024 + "MB");
		startTime = Control.memory.getLast().getTimestamp();
		endTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
		System.out.println(Control.memory.size());
		//System.out.println(DK1W.waitingList.size());

		OutputFile.writeIntoReport(outputFile.getContent(startTime, endTime, Runtime.getRuntime().freeMemory()/1024/1024));
	}

}
