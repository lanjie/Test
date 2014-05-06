import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Control {
	
	public static LinkedList<Tuple> memory = new LinkedList<Tuple>();

	
	public Tuple createTuple() {
		
		Tuple t = new Tuple();
		Random random = new Random();
		
		t.setWaitingTime(Math.abs(random.nextInt() % 10));
		t.setTimestamp(System.currentTimeMillis());

		
				
		return t;
	}
	
	
	public LinkedList<Tuple> insertTuplesIntoMemery(Tuple tuple) {
		
		memory.add(tuple);
		
		return memory;
		
	}
	
	
	public boolean removeExpiredTuples(LinkedList<Tuple> memory, Long windowSize) {
		
		long latestTuple = System.currentTimeMillis();
		long oldestTuple = memory.getFirst().getTimestamp() + windowSize;
		
		if(oldestTuple < latestTuple) {
			
			Control.memory.removeFirst();
			return true;
		}
		return false;
		
	}
	
	
	
	
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
