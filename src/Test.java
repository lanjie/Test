
import java.util.concurrent.TimeUnit;


public class Test {




	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Control c = new Control();

		long windowSize = TimeUnit.MILLISECONDS.toMillis(1);
		System.out.println(windowSize);
		
		double result = 0;

		System.out.println("-----------------------------------");

		for (int i = 0; i < 1; i++ ) {
			
			 result = c.AVG(c.insertTuplesIntoMemery(c.createTuple()));
		}
		
		
		System.out.println(result);
		System.out.println("-----------------------------------");


		
	}

}
