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
	
	static File file = new File("H:\\report.txt");

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
	
	public static boolean logfile(String content) throws IOException {
		boolean flag = false;
		byte[] buffer = new byte[]{};

		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileOutputStream fos = new FileOutputStream(file, true);
		fos.write(buffer);
		flag = true;

		return flag;
	}
}
