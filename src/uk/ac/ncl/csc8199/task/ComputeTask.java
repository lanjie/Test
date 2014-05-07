package uk.ac.ncl.csc8199.task;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.TimerTask;

import uk.ac.ncl.csc8199.control.Control;
import uk.ac.ncl.csc8199.data.CPUCollector;
import uk.ac.ncl.csc8199.data.OutputFile;
import uk.ac.ncl.csc8199.model.MonitorInfoBean;


public class ComputeTask extends TimerTask{

	
	Control control = new Control();
	OutputFile outputFile = new OutputFile();
	public long startTime;
	public long endTime;
	public double cupInfo;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		control.AVG(Control.memory);
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024 + "MB/"+ Runtime.getRuntime().freeMemory()/1024/1024 + "MB/" + Runtime.getRuntime().totalMemory()/1024/1024 + "MB");
		startTime = Control.memory.getFirst().getTimestamp();
		endTime = System.currentTimeMillis();
		CPUCollector c =new CPUCollector();

	          MonitorInfoBean monitorInfo = null;
			try {
				monitorInfo = c.getMonitorInfoBean();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		OutputFile.writeIntoReport(outputFile.getContent(startTime, endTime, ManagementFactory.getRuntimeMXBean().getUptime()));
	}

}
