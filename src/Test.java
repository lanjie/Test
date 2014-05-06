
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Test{


	public static long windowSize = TimeUnit.SECONDS.toMillis(1);
	public static long slideSize = TimeUnit.SECONDS.toMillis(1);

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Control c = new Control();
		Timer timer = new Timer();
		
		timer.schedule(new CreateTask(), 0, 1);
		timer.schedule(new ComputeTask(), 1000, Test.slideSize);
		timer.schedule(new RemoveTask(), 1, Test.windowSize);


	
		

/*		System.out.println("-----------------------------------");
		
		
		
		for (int i = 0; i < 1; i++ ) {
			
			result = c.AVG(c.insertTuplesIntoMemery(c.createTuple()));
		}
		
		System.out.println("AVG = " + result);
		System.out.println("-----------------------------------");
		*/
	}

		

}
