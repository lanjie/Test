import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Control {
	
	LinkedList<Tuple> memory = new LinkedList<Tuple>();
	
	public Tuple createTuple() {
		
		Tuple t = new Tuple();
		Random random = new Random();
		
		t.setWaitingTime(Math.abs(random.nextInt() % 10));
		t.setTimestamp(System.currentTimeMillis());

		TimeUnit.MINUTES.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
				
		return t;
	}
	
	
	public LinkedList<Tuple> insertTuplesIntoMemery(Tuple tuple) {
		
		memory.add(tuple);
		
		return memory;
		
	}
	
	
	public void removeTuplesFromMemory(LinkedList<Tuple> memory) {
		
		
	}
	
	
	
	
	public Double AVG(LinkedList<Tuple> memory) {
		
		double amount = 0;
		
		for(Iterator<Tuple> i = memory.iterator(); i.hasNext(); ){
			
			Tuple temp = i.next();
			amount+=temp.getWaitingTime();
		}
		
		System.out.println("Size = " + memory.size());
		System.out.println("Amount = " + amount);
		
		return amount/memory.size();
	}
	

}
