package uk.ac.ncl.csc8199.data;

/**   
 *    
 * Project Name：Test   
 * Class Name：Bytes   
 * Author：Jie Lan  
 * Time：2014年5月6日 下午9:25:18   
 * @version    
 *    
 */
public class Bytes {

	public static String substring(String src, int start_idx, int end_idx){
        byte[] b = src.getBytes();
        String tgt = "";
        for(int i=start_idx; i<=end_idx; i++){
            tgt +=(char)b[i];
        }
        return tgt;
    }
}
