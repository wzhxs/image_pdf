package image.pdf.com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类
 * 
 * @author wangzh 2016年9月18日 上午9:12:04
 */
public final class StringUtil {

	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否不为空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 字符串截取
	 */
	public static String[] splitString(String str, String split) {
		return str.split(split);
	}
	
	/**
	 * 判断是否为数字，正负数和 小数
	 */
	public static boolean isNumber(String context) {
		Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
		Matcher isNum = pattern.matcher(context);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 字符创反转
	 */
	public static String toReversal(String context) {
		return new StringBuffer(context).reverse().toString();
	}

	/**
	 * 高效分隔符实例
	 * 
	 * @param context
	 */
	public static List<String> model(String context) {
		StringTokenizer work = new StringTokenizer(context, ",");// 可设定第三个参数，第三个参数设定时，返回分隔符
		List<String> result=new ArrayList<String>();
		while (work.hasMoreTokens()) {
			String te = work.nextToken();
			result.add(te);
		}
		return result;
	}
	
}
