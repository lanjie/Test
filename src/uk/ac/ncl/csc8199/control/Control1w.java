package uk.ac.ncl.csc8199.control;

import java.util.Iterator;
import java.util.LinkedList;

import uk.ac.ncl.csc8199.model.Tuple;

public class Control1w {
	
	public static double amount = 0;
	
	public void AVG(LinkedList<Tuple> memory) {
			
		for(Iterator<Tuple> i = memory.iterator(); i.hasNext(); ){
			
			Tuple temp = i.next();
			amount+=temp.getWaitingTime();
		}
		
		System.out.println("Size = " + memory.size());
		System.out.println("Amount = " + amount);
		System.out.println("AVG = " + amount/memory.size());

	}

}
