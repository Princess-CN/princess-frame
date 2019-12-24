package princess.common.kit;

import java.util.Stack;

/**
 * 数字工具类
 * 提供各种对数字的处理
 */
public class NumericalUtil {
	
	public static void main(String[] args) {
		String binaryChange = binaryChange(387398787498348L,36);
		System.out.println(binaryChange);
	}
	
	/** 42个基本字符 */
	private static final String C_CODES_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$.`";
	
	/***
	 * 将10进制转换为任意进制
	 * @param intVal
	 * @param base < = 42
	 * @return
	 */
	public static String binaryChange(long intVal, int base)  {
		
		int w_code_len = C_CODES_STRING.length();
		if (base >w_code_len){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		Stack<String> s=new Stack<String>();
		while (intVal!=0){
			s.push(C_CODES_STRING.charAt((int)(intVal%base))+"");
			intVal/=base;
		}
		while (!s.empty()){
			sb.append(s.pop());
		}
		return sb.length()==0?"0":sb.toString();
	}
	
	/***
	 * 将10进制转换为任意进制
	 * @param intVal
	 * @param base <=36
	 * @return
	 */
	public static String binaryChange(int intVal, int base)  {
		int w_code_len = C_CODES_STRING.length();
		if (base >w_code_len){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		Stack<String> s=new Stack<String>();
		while (intVal!=0){
			s.push(C_CODES_STRING.charAt((int)(intVal%base))+"");
			intVal/=base;
		}
		while (!s.empty()){
			sb.append(s.pop());
		}
		return sb.length()==0?"0":sb.toString();
	}
	
	/***
	 * 任何进制转换
	 * @param s
	 * @param srcBase s的进制（2-42）
	 * @param destBase 要转换为的进制（2-42）
	 * @return
	 */
	public static String binaryChange(String s, int srcBase, int destBase) {
		if (srcBase == destBase) {
			return s;
		}
		char[] chars = s.toCharArray();
		int len = chars.length;
		if (destBase != 10) {// 目标进制不是十进制 先转化为十进制
			s = binaryChange(s, srcBase, 10);
		} else {
			long n = 0;
			for (int i = len - 1; i >= 0; i--) {
				n += C_CODES_STRING.indexOf(chars[i]) * Math.pow(srcBase, len - i - 1);
			}
			return String.valueOf(n);
		}
		return binaryChange(Integer.valueOf(s), destBase);
	}
	
}
