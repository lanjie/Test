package uk.ac.ncl.csc8199.task;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.control.MO2LA;
import uk.ac.ncl.csc8199.data.OutputFile;

public class ComputeMO2LATask extends TimerTask{

	MO2LA mo2la = new MO2LA();
	OutputFile outputFile = new OutputFile();
	public long startTime;
	public long endTime;
	public double cupInfo;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		mo2la.controlSSMTuple(mo2la.createSSMTuple(Control.memory));
		mo2la.SSMAVG(Control.SSMMemory);
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024 + "MB/"+ Runtime.getRuntime().freeMemory()/1024/1024 + "MB/" + Runtime.getRuntime().totalMemory()/1024/1024 + "MB");
		System.out.println("-----------------------------");
		System.out.println("SSMMemorySize: " + Control.SSMMemory.size());
		System.out.println("--------------------------------------");
		startTime = Control.SSMMemory.getLast().getSSMtimestamp();
		endTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());

		OutputFile.writeIntoReport(outputFile.getContent(startTime, endTime, Runtime.getRuntime().freeMemory()/1024/1024));

	}

	
}
