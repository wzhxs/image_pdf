package image.pdf.com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 获取当前时间
	 */
	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); 
		return sdf.format(new Date());
	}
}
