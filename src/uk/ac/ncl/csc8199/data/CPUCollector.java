package uk.ac.ncl.csc8199.data;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.StringTokenizer;
/*    
 * Project Name��Test   
 * Class Name��CPUCollector   
 * Author��Jie Lan  
 * Time��2014��5��6�� ����9:15:19   
 * @version    
 *    
 */

import uk.ac.ncl.csc8199.model.MonitorInfoBean;

public class CPUCollector {
	private static final int CPUTIME = 30;
	private static final int PERCENT = 100;
	private static final int FAULTLENGTH = 10;

	private static final File versionFile = new File("/proc/version");
	private static String linuxVersion = null;

	public double getCpuRatio() {
		// ����ϵͳ
		String osName = System.getProperty("os.name");
		double cpuRatio = 0;
		if (osName.toLowerCase().startsWith("windows")) {
			return cpuRatio = this.getCpuRatioForWindows();
		} else {
			return cpuRatio = this.getCpuRateForLinux();
		}
	}

	/**
	 * ��õ�ǰ�ļ�ض���.
	 * 
	 * @return ���ع���õļ�ض���
	 */
	public MonitorInfoBean getMonitorInfoBean() throws Exception {
		int kb = 1024;

		// ��ʹ���ڴ�
		long totalMemory = Runtime.getRuntime().totalMemory() / kb;
		// ʣ���ڴ�
		long freeMemory = Runtime.getRuntime().freeMemory() / kb;
		// ����ʹ���ڴ�
		long maxMemory = Runtime.getRuntime().maxMemory() / kb;
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();
		// ����ϵͳ
		String osName = System.getProperty("os.name");
		// ����߳�����
		ThreadGroup parentThread;
		for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
				.getParent() != null; parentThread = parentThread.getParent())
			;
		int totalThread = parentThread.activeCount();
		double cpuRatio = 0;
		if (osName.toLowerCase().startsWith("windows")) {
			cpuRatio = this.getCpuRatioForWindows();
		} else {
			cpuRatio = this.getCpuRateForLinux();
		}

		// ���췵�ض���
		MonitorInfoBean infoBean = new MonitorInfoBean();
		infoBean.setFreeMemory(freeMemory);
		infoBean.setMaxMemory(maxMemory);
		infoBean.setOsName(osName);
		infoBean.setTotalMemory(totalMemory);
		infoBean.setTotalThread(totalThread);
		infoBean.setCpuRatio(cpuRatio);
		return infoBean;
	}

	private static double getCpuRateForLinux() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader brStat = null;
		StringTokenizer tokenStat = null;
		try {
			System.out.println("Get usage rate of CUP , linux version: "
					+ linuxVersion);
			Process process = Runtime.getRuntime().exec("top -b -n 1");
			is = process.getInputStream();
			isr = new InputStreamReader(is);
			brStat = new BufferedReader(isr);

			if (linuxVersion.equals("2.4")) {
				brStat.readLine();
				brStat.readLine();
				brStat.readLine();
				brStat.readLine();

				tokenStat = new StringTokenizer(brStat.readLine());
				tokenStat.nextToken();
				tokenStat.nextToken();
				String user = tokenStat.nextToken();
				tokenStat.nextToken();
				String system = tokenStat.nextToken();
				tokenStat.nextToken();
				String nice = tokenStat.nextToken();

				System.out.println(user + " , " + system + " , " + nice);

				user = user.substring(0, user.indexOf("%"));
				system = system.substring(0, system.indexOf("%"));
				nice = nice.substring(0, nice.indexOf("%"));

				float userUsage = new Float(user).floatValue();
				float systemUsage = new Float(system).floatValue();
				float niceUsage = new Float(nice).floatValue();

				return (userUsage + systemUsage + niceUsage) / 100;
			} else {
				brStat.readLine();
				brStat.readLine();

				tokenStat = new StringTokenizer(brStat.readLine());
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				String cpuUsage = tokenStat.nextToken();

				System.out.println("CPU idle : " + cpuUsage);
				Float usage = new Float(cpuUsage.substring(0,
						cpuUsage.indexOf("%")));

				return (1 - usage.floatValue() / 100);
			}

		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			freeResource(is, isr, brStat);
			return 1;
		} finally {
			freeResource(is, isr, brStat);
		}
	}

	private static void freeResource(InputStream is, InputStreamReader isr,
			BufferedReader br) {
		try {
			if (is != null)
				is.close();
			if (isr != null)
				isr.close();
			if (br != null)
				br.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	/**
	 * ���CPUʹ����.
	 * 
	 * @return ����cpuʹ����
	 */
	public double getCpuRatioForWindows() {
		try {
			String procCmd = System.getenv("windir")
					+ "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,"
					+ "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
			// ȡ������Ϣ
			long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
			Thread.sleep(CPUTIME);
			long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
			if (c0 != null && c1 != null) {
				long idletime = c1[0] - c0[0];
				long busytime = c1[1] - c0[1];
				return Double.valueOf(
						PERCENT * (busytime) / (busytime + idletime))
						.doubleValue();
			} else {
				return 0.0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0.0;
		}
	}

	/**
	 * ��ȡCPU��Ϣ.
	 * 
	 * @param proc
	 */
	private long[] readCpu(final Process proc) {
		long[] retn = new long[2];
		try {
			proc.getOutputStream().close();
			InputStreamReader ir = new InputStreamReader(proc.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line = input.readLine();
			if (line == null || line.length() < FAULTLENGTH) {
				return null;
			}
			int capidx = line.indexOf("Caption");
			int cmdidx = line.indexOf("CommandLine");
			int rocidx = line.indexOf("ReadOperationCount");
			int umtidx = line.indexOf("UserModeTime");
			int kmtidx = line.indexOf("KernelModeTime");
			int wocidx = line.indexOf("WriteOperationCount");
			long idletime = 0;
			long kneltime = 0;
			long usertime = 0;
			while ((line = input.readLine()) != null) {
				if (line.length() < wocidx) {
					continue;
				}
				// �ֶγ���˳��Caption,CommandLine,KernelModeTime,ReadOperationCount,
				// ThreadCount,UserModeTime,WriteOperation
				String caption = Bytes.substring(line, capidx, cmdidx - 1)
						.trim();
				String cmd = Bytes.substring(line, cmdidx, kmtidx - 1).trim();
				if (cmd.indexOf("wmic.exe") >= 0) {
					continue;
				}
				// log.info("line="+line);
				if (caption.equals("System Idle Process")
						|| caption.equals("System")) {
					idletime += Long.valueOf(
							Bytes.substring(line, kmtidx, rocidx - 1).trim())
							.longValue();
					idletime += Long.valueOf(
							Bytes.substring(line, umtidx, wocidx - 1).trim())
							.longValue();
					continue;
				}
				kneltime += Long.valueOf(
						Bytes.substring(line, kmtidx, rocidx - 1).trim())
						.longValue();
				usertime += Long.valueOf(
						Bytes.substring(line, umtidx, wocidx - 1).trim())
						.longValue();
			}
			retn[0] = idletime;
			retn[1] = kneltime + usertime;
			return retn;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				proc.getInputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * ���Է���.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		CPUCollector c = new CPUCollector();
		System.out.println("cpuռ����1=" + c.getCpuRatio());
		System.out.println("cpuռ����2=" + c.getCpuRatioForWindows());
		MonitorInfoBean monitorInfo = c.getMonitorInfoBean();
		System.out.println("cpuռ����=" + monitorInfo.getCpuRatio());

		System.out.println("��ʹ���ڴ�=" + monitorInfo.getTotalMemory());
		System.out.println("ʣ���ڴ�=" + monitorInfo.getFreeMemory());
		System.out.println("����ʹ���ڴ�=" + monitorInfo.getMaxMemory());

		System.out.println("����ϵͳ=" + monitorInfo.getOsName());
		System.out.println("�ܵ������ڴ�=" + monitorInfo.getTotalMemorySize()
				+ "kb");
		System.out.println("ʣ��������ڴ�=" + monitorInfo.getFreeMemory()
				+ "kb");
		System.out.println("��ʹ�õ������ڴ�=" + monitorInfo.getUsedMemory()
				+ "kb");
		System.out.println("�߳�����=" + monitorInfo.getTotalThread() + "kb");
	}
}
