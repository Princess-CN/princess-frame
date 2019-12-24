package princess.common.kit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import base.lang.StringUtil;

/**
 * @Title: StringUtil.java
 * @Description: 系统公共类库
 * 系统公共类库
 */
public class StringUtilKit {
	
	/** 字母  */
	public static String LETTER_STR = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz";
	
	/** 大写字母  */
	public static String CAPITAL_STR = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
	
	/** 小写字母  */
	public static String LOWERCASE_STE = "abcdefghigklmnopqrstuvwxyz";
	
	/**
	 * <p>生成uuid</p>
	 * 获取uuid,移除横杠-
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

	public static String toString(Object obj){
		try{
			return obj.toString();
		}catch (Throwable t){}
		return "";
	}
	
	/**
	 * <p>获取字符串前几位</p>
	 * 异常时获取空字符串
	 * @param param字符串，排除前后空
	 * @param length获取位数
	 */
	public static String getPerChar(String param, int length) {
		if (StringUtil.isEmpty(param) || length <= 0) {
			return "";
		}
		param = param.trim();
		if (param.length() > length) {
			return param.substring(0, length);
		}
		return param;
	}
	
	/**
	 * <p>获取字符串后几位</p>
	 * 异常时获取空字符串
	 * @param param字符串，排除前后空
	 * @param length获取位数
	 */
	public static String getSufChar(String param, int length) {
		if (StringUtil.isEmpty(param) || length <= 0) {
			return "";
		}
		param = param.trim();
		int length2 = param.length();
		if (length2 > length) {
			return param.substring(length2 - length, length2);
		}
		return param;
	}
	
    /**
     * 判断字符串是否为空或者空串,trim之后判断
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
    	if(null == str) return true;
    	str = str.trim();
    	if(str.isEmpty())return true;
    	if("null".equalsIgnoreCase(str))return true;
    	return false;
    }
    
    /**
     * 判断字符串是否为空或者空串,trim之后判断
     * 多参数
     * @param str
     * @return
     */
    public static boolean isEmpty(String... str) {
    	if(null == str || str.length == 0)return true;
    	for (int i = 0; i < str.length; i++) {
			if(isEmpty(str[i])) {
				return true;
			}
		}
    	return false;
    }
    
    /**
     * 判断字符串不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String... str) {
    	return !isEmpty(str);
    }
    
    /**
     * 移除字符串两端的空格，null返回null
     * @param str
     * @return
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }
    
    /**
     * 将数组拼接起来
     * <pre>
     * StringUtils.join(null, *)          = null
     * StringUtils.join([], *)            = ""
     * StringUtils.join([null], *)        = ""
     * StringUtils.join([1, 2, 3], ';')   = "1;2;3"
     * StringUtils.join([1, 2, 3], null)  = "123"
     * </pre>
     * @param array
     * @param separator分隔符
     * @return
     */
    public static String join(Object[] array, String separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }
    
    /**
     * 将数组拼接起来
     * <pre>
     * StringUtils.join(null, *)          = null
     * StringUtils.join([], *)            = ""
     * StringUtils.join([null], *)        = ""
     * StringUtils.join([1, 2, 3], ';')   = "1;2;3"
     * StringUtils.join([1, 2, 3], null)  = "123"
     * </pre>
     * @param array
     * @param separator分隔符
     * @return
     */
    public static String join(Iterable<?> iterable, String separator) {
    	return org.apache.commons.lang3.StringUtils.join(iterable, separator);
    }
    
    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String unCapitalize(String str) {
        return org.springframework.util.StringUtils.uncapitalize(str);
    }
    
    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String capitalize(String str) {
    	return org.springframework.util.StringUtils.capitalize(str);
    }
    
	/**
	 * <p>获取随机大写字母组合</p>
	 * @param length 字符串长度
	 */
	public static String getRandomChar(Integer length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = (char) (65 + random.nextInt(26));
			str += Character.toString(c);// 取得大写字母
		}
		return str;
	}
    
    /**
     * <p>获取随机字符串,大小写混合</p>
     * @param length 字符串长度
     */
    public static String getRandomLetter(int size) {
    	if(size <= 0) {
    		return null;
    	}
    	String str = "";
    	Random random = new Random();
    	for (int i = 0; i < size; i++) {
    		int c = random.nextInt(52);
    		str += LETTER_STR.charAt(c);
    	}
    	return str;
    }
    
    /**
     * <p>根据字符串，获取随机字符串</p>
     * @param size 字符串长度
     */
    public static String getRandomLetter(String letter, int size) {
    	if(size <= 0 || isEmpty(letter)) {
    		return null;
    	}
    	int length = letter.length();
    	String str = "";
    	Random random = new Random();
    	for (int i = 0; i < size; i++) {
    		int c = random.nextInt(length);
    		str += letter.charAt(c);
    	}
    	return str;
    }
    
    /**
     * <p>获取随机数字,首位不为0</p>
     * @param size 字符串长度
     */
    public static String getRandomNumber(int size) {
    	if(size <= 0) {
    		return null;
    	}
    	
    	String str = "";
    	Random random = new Random();
    	for (int i = 0; i < size; i++) {
    		int c;
    		if(i== 0) {
    			c = random.nextInt(9) + 1;
    		} else {
    			c = random.nextInt(10);
    		}
    		str += c;// 取得大写字母
    	}
    	return str;
    }
    
    /**
     * 获取两个年份之间的值 含头含尾
     * @param beginYear,endYear 年份数字
     */
    public static List<String> getSequenceNumber(String beginYear, String endYear) {
        List<String> lists = new ArrayList<>();
        if (beginYear.endsWith(endYear)) {
            lists.add(beginYear);
        } else {
            int beginYearInt = Integer.parseInt(beginYear);
            int endYearInt = Integer.parseInt(endYear);
            for (int i = 0; i <= endYearInt - beginYearInt; i++) {
                lists.add(String.valueOf(beginYearInt + i));
            }
        }
        return lists;
    }
    
    /**
     * 获取时间之间所有的年月字符串集合
     * @param beginYear
     * @param beginMonth
     * @param endYear
     * @param endMonth
     * @return
     */
    public static List<String> getSequenceNumber(String beginYear,String beginMonth, String endYear,String endMonth) {
        List<String> relists=new ArrayList<>();
        List<String> lists = getSequenceNumber(beginYear,endYear);
            for (String s:lists){
                int begin=1;
                int end=12;
                if(s.equals(beginYear)){
                    begin=Integer.parseInt(beginMonth);
                }
                if(s.equals(endYear)){
                    end=Integer.parseInt(endMonth);
                }
                for (int i = begin; i <=end ; i++) {
                    relists.add(s+"-"+i);
                }
            }
        return relists;
    }
    
    /**
     * 给数字前补零，凑齐4位,可用于生成凭证编号
     * @param args
     * @return
     */
    public static String twoChangeFour(String args) {
        String str = args;
        char[] ary1 = str.toCharArray();
        char[] ary2 = {'0','0','0','0'};
        System.arraycopy(ary1, 0, ary2, ary2.length-ary1.length, ary1.length);
        String result = new String(ary2);

        return result;
    }
    
    /**
     * 更改科目编码级次
     * @param oldCodeType
     * @param newCodeType
     * @param code
     * @return
     */
    public static String changeCode(String oldCodeType, String newCodeType, String code) {
    	if(isEmpty(oldCodeType) || isEmpty(newCodeType) || isEmpty(code)) {
    		return null;
    	}
		char[] oldCode = oldCodeType.toCharArray();
		char[] newCode = newCodeType.toCharArray();
		int[] oldCodeInt = new int[oldCode.length];
		int[] newCodeInt = new int[newCode.length];
		
		for (int i = 0; i < oldCode.length; i++) {
			oldCodeInt[i] = Character.getNumericValue(oldCode[i]);
		}
		for (int i = 0; i < newCode.length; i++) {
			newCodeInt[i] = Character.getNumericValue(newCode[i]);
		}
		String changeCode = "";
		int length = 0;
		
		for (int i = 0; i < oldCodeInt.length; i++) {
			if(code.length() >  length){
				String code0 = code.substring(length, length + oldCodeInt[i]);
				if(oldCodeInt[i] < newCodeInt[i]){
					char[] temp = new char[newCodeInt[i] - oldCodeInt[i]];
					for (int j = 0; j < temp.length; j++) {
						temp[j] = '0';
					}
					code0 = String.valueOf(temp) + code0;
				}else if(oldCodeInt[i] > newCodeInt[i]){
					String tendString = code0.substring(0, oldCodeInt[i]- newCodeInt[i]);
					char[] temp = tendString.toCharArray();
					boolean flag = true;
					for (char c : temp) {
						if(!Objects.equals(c,'0')){
							flag = false;
							break;
						}
					}
					if(flag){
						code0 = code0.substring(oldCodeInt[i]- newCodeInt[i]);
					}else{
						return null;
					}
				}
				changeCode += code0;
			}else{
				break;
			}
			length += oldCodeInt[i];
		}
		return changeCode;
    }
    
    /**
     * 移除字符串中所有空白，移除字符串前后和中间的空白
     * @param str
     * @return
     */
    public static String removeSpace(String str) {
        String result = str;
        String regEx = "[ 　]";

        try {
            result = Pattern.compile(regEx).matcher(str).replaceAll("").trim();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return result;
    }
}
