package uk.ac.ncl.csc8199.task;
import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.control.MO2LA;
import uk.ac.ncl.csc8199.data.OutputFile;


public class ComputeMO1WTask extends TimerTask{

	
	Control control = new Control();
	OutputFile outputFile = new OutputFile();
	public long startTime;
	public long endTime;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		control.AVG(Control.memory);
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024 + "MB/"+ Runtime.getRuntime().freeMemory()/1024/1024 + "MB/" + Runtime.getRuntime().totalMemory()/1024/1024 + "MB");
		startTime = Control.memory.getLast().getTimestamp();
		endTime = System.nanoTime();

		OutputFile.writeIntoReport(outputFile.getContent(startTime, endTime, Runtime.getRuntime().freeMemory()/1024/1024));
	}

}
