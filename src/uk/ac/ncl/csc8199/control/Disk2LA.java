package uk.ac.ncl.csc8199.control;

import uk.ac.ncl.csc8199.test.Test;

public class Disk2LA {
	
	public void Disk2LARemove() {
					
		long startTime = Control.SSMMemory.getFirst().getSSMtimestamp();
		long endTime = Control.SSMMemory.getLast().getSSMtimestamp();
		
		if (startTime + Test.windowSize > endTime) {
			
			Control.SSMMemory.removeFirst();
		}
	}
}
