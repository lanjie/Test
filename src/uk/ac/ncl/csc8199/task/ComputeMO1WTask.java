package uk.ac.ncl.csc8199.task;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.control.MO1W;
import uk.ac.ncl.csc8199.control.MO2LA;
import uk.ac.ncl.csc8199.data.OutputFile;
import uk.ac.ncl.csc8199.test.Test;


public class ComputeMO1WTask extends TimerTask{

	
	MO1W mo1w = new MO1W();
	OutputFile outputFile = new OutputFile();
	public long startTime;
	public long endTime;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		mo1w.AVG(Control.memory, Test.windowSize, Test.slideSize);
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024 + "MB/"+ Runtime.getRuntime().freeMemory()/1024/1024 + "MB/" + Runtime.getRuntime().totalMemory()/1024/1024 + "MB");
		startTime = Control.memory.getLast().getTimestamp();
		endTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());

		OutputFile.writeIntoReport(outputFile.getContent(startTime, endTime, Runtime.getRuntime().freeMemory()/1024/1024));
	}

}
