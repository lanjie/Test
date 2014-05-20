package uk.ac.ncl.csc8199.data;

import java.io.*;

/**
 * 
 * Project Name：Test Class Name：OutputFile Author：Jie Lan Time：2014年5月6日
 * 下午8:10:19
 * 
 * @version
 * 
 */
public class OutputFile {

	public String getContent(Long startTime, Long endTime, Long freeMemory) {

		String content = startTime.toString() + "," + endTime.toString() + ","
				+ freeMemory.toString() + "\n";

		return content;
	}

	public static boolean writeIntoReport(String content) {

		boolean flag = false;

		try {

			File file = new File("D:\\report-3600-1-300000-128-MO-1W.csv");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
			bw.close();
			System.out.println("Done");
			flag = true;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return flag;
	}

}
