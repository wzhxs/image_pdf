package image.pdf.com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

/**
 * 文件工具类
 * @author wangzh
 * @date 2018年9月21日 下午2:22:52
 */
public final class FileUtil {
	
	/**
	 * 获取当前用户桌面路径
	 */
	public static String getDesktop(){
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		return desktopDir.toString();
	}
	
	/**
	 * 替换和过滤内容方法
	 * @param fileUrl
	 * @param filterList
	 * @param spaceMap
	 */
	public static void filterFile(String fileUrl,String[] filterList,Map<String,String> spaceMap){
		try {
			FileInputStream inputStream  = new FileInputStream(fileUrl);
			
			BufferedReader buf=new BufferedReader(new InputStreamReader(inputStream));
			
			BufferedWriter write=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileUrl.replace(".txt", "_new.txt"))));
			String context="";
			int state=0;
			while((context=buf.readLine())!=null){
				
				state=0;
				for(String filter:filterList){
					if(context.contains(filter)){
						state=1;
						break;
					}
				}
				if(state==1){
					continue;
				}
				
				for(String space:spaceMap.keySet()){
					context=context.replace(space, spaceMap.get(space));
				}
				write.write(context+System.getProperty("line.separator"));
			}
			
			write.flush();
			write.close();
			buf.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
