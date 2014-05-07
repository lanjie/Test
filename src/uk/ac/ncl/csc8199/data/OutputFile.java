package uk.ac.ncl.csc8199.data;
import java.io.*;


/**   
 *    
 * Project Name：Test   
 * Class Name：OutputFile   
 * Author：Jie Lan  
 * Time：2014年5月6日 下午8:10:19   
 * @version    
 *    
 */
public class OutputFile {

	public String getContent(Long startTime, Long endTime, Long cupInfo) {
		
		String content = startTime.toString() +","+ endTime.toString() +","+ cupInfo +"\n";
		
		return content;
	}
	
	public static void writeIntoReport(String content) {

		try {
 
			File file = new File("C:\\report.txt");
 

			if (!file.exists()) {
				file.createNewFile();
			}
 

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.flush();
				bw.close();
				System.out.println("Done");

 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
