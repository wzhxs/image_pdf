package image.pdf.com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Http 工具类
 * 
 * @author wangzh
 * 2018年1月2日 下午3:34:38
 */
public final class HttpUtil {

	private static final Log log = LogFactory.getLog(HttpUtil.class);

	/**
	 * get请求 设置属性
	 * url 请求路径
	 * param 请求参数（可为空），样式 name1=value1&name2=value2 
	 */
	public static String sendGet(String url, String param) {
		StringBuffer buf=new StringBuffer();
		BufferedReader in = null;
		try {
			String urlNameString;
			if(StringUtil.isNotEmpty(param)){
				urlNameString = url + "?" + param;
			}else{
				urlNameString = url;
			}
			
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				buf.append(line);
			}
		} catch (Exception e) {
			log.error("发送GET请求出现异常！" + e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				log.error("发送GET请求关闭异常！" + e);
			}
		}
		return buf.toString();
	}

	/**
	 * post请求 设置属性
	 * url 请求路径
	 * param 请求参数
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer buf=new StringBuffer();
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				buf.append(line+System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			log.error("发送GET请求出现异常！" + e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				log.error("发送GET请求关闭异常！" + e);
			}
		}
		return buf.toString();
	}
	
	/**
	 * get请求
	 * 非设置属性
	 */
	public static String getContext(String url){
		 try {
	            URL ur = new URL(url);
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(ur.openStream()));
	            StringBuffer buf=new StringBuffer();
	            String line;
	            while((line = reader.readLine()) != null){
	                buf.append(line+System.getProperty("line.separator"));
	            }
	            reader.close();
	            
	            return buf.toString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return "";
	}
	
	/**
	 * get请求
	 * 非设置属性
	 */
	public static String getContext(String url,String Encode){
		 try {
	            URL ur = new URL(url);
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(ur.openStream(),Encode));
	            StringBuffer buf=new StringBuffer();
	            String line;
	            while((line = reader.readLine()) != null){
	                buf.append(line);
	            }
	            reader.close();
	            
	            return buf.toString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return "";
	}
}
