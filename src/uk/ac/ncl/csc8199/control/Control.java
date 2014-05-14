package uk.ac.ncl.csc8199.control;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import uk.ac.ncl.csc8199.model.SSMTuple;
import uk.ac.ncl.csc8199.model.Tuple;


public class Control {
	
	public static LinkedList<Tuple> memory = new LinkedList<Tuple>();
	public static LinkedList<SSMTuple> SSMMemory = new LinkedList<SSMTuple>();

	public void SSM(LinkedList<Tuple> memory) {
		
		double amount = 0;
		double count = 0;
		long startTime = memory.getFirst().getTimestamp();
		
		SSMTuple ssmTuple = new SSMTuple();
		
		for(Iterator<Tuple> i = memory.iterator(); i.hasNext(); ){
			
			
			if(startTime < (startTime + TimeUnit.SECONDS.toMillis(1))) {
			
				Tuple temp = i.next();
				amount+=temp.getWaitingTime();				
				count++;
				i.remove();
			}

		}
		
		ssmTuple.setAmonut(amount);
		ssmTuple.setSSMtimestamp(startTime);
		ssmTuple.setSampleSize(count);
		SSMMemory.add(ssmTuple);
		
	}
	
	public void SSMAVG(LinkedList<SSMTuple> SSMMemory) {
		
		double amount = 0;
		double size = 0;
		
		for(Iterator<SSMTuple> i = SSMMemory.iterator(); i.hasNext(); ){
			
			SSMTuple temp = i.next();
			amount+=temp.getAmonut();
			size+=temp.getSampleSize();
			
		}
		
		System.out.println("SampleSize = " + size);
		System.out.println("Amount = " + amount);
		System.out.println("AVG = " + amount/size);
		System.out.println("------------------------");
		System.out.println("SSMMemorySize = " + SSMMemory.size());
		System.out.println("memorySize = " + memory.size());
		System.out.println("------------------------");
	}
	
	public Tuple createTuple() {
		
		Tuple t = new Tuple();
		Random random = new Random();
		
		t.setWaitingTime(Math.abs(random.nextInt() % 10));
		t.setTimestamp(System.nanoTime());


				
		return t;
	}
	
	
	public LinkedList<Tuple> insertTuplesIntoMemery(Tuple tuple) {
		
		memory.add(tuple);
		
		return memory;
		
	}
	
	
/*	public boolean removeExpiredTuples(LinkedList<Tuple> memory, Long windowSize) {
		
		long latestTuple = System.currentTimeMillis();
		long oldestTuple = memory.getFirst().getTimestamp() + windowSize;
		
		if(oldestTuple < latestTuple) {
			
			Control.memory.removeFirst();
			return true;
		}
		return false;
		
	}*/
	
	
	
	
	public void AVG(LinkedList<Tuple> memory) {
		
		double amount = 0;
		
		for(Iterator<Tuple> i = memory.iterator(); i.hasNext(); ){
			
			Tuple temp = i.next();
			amount+=temp.getWaitingTime();
		}
		
		System.out.println("Size = " + memory.size());
		System.out.println("Amount = " + amount);
		System.out.println("AVG = " + amount/memory.size());

	}
	

}
