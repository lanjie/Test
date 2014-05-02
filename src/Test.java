import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


public class Test {




	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Control c = new Control();
		
		double result = 0;

		System.out.println("-----------------------------------");

		for (int i = 0; i < 101; i++ ) {
			
			 result = c.AVG(c.insertTuplesIntoMemery(c.createTuple()));
		}
		
		
		System.out.println(result);
		System.out.println("-----------------------------------");
		

		
	}

}
