package image.pdf.com.util;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel工具类
 * @author wangzh
 * @date 2019年11月5日 上午11:11:19
 */
public final class ExcelUtil {
	
	private static final Log log = LogFactory.getLog(ExcelUtil.class);
	
	/**
	 * 读取excel
	 */
	@SuppressWarnings("all")
	public static boolean readExcel(String xlsPath,String sheet,String[] fieldList,String start) {
		try {
			FileInputStream fileIn = new FileInputStream(xlsPath);
			Workbook wb=null;
			 if(checkVersion(xlsPath)){
                wb=new XSSFWorkbook(fileIn);
            }else{
                wb=new HSSFWorkbook(fileIn);
            }

			Sheet sht0 = wb.getSheetAt(formatNum(sheet)); // 获取分栏数据
			int rowNum=formatNum(start);  //获取起始行
			List<String> data=new ArrayList<>();  //数据汇集
			String context;
			int row;
			for (Row r : sht0) {
				if (r.getRowNum() < rowNum) {
					continue;
				}
				
				context="";
				for(String field:fieldList){
					if(StringUtil.isNumber(field)){
						row=Integer.parseInt(field);
						if (r.getCell(row) != null) {
							r.getCell(row).setCellType(HSSFCell.CELL_TYPE_STRING); // Int类型转换String
							
							if(!context.equals("")){
								context+=",";
							}
							context+=r.getCell(row).getStringCellValue().trim();
						}
					}
				}
				
				if(!context.equals("")){
					data.add(context);
				}
				
			}
			fileIn.close();
			
			if(data!=null&&data.size()>0){
				String file=xlsPath+".txt";
				BufferedWriter write=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				for(String one:data){
					write.write(one+System.getProperty("line.separator"));
				}
				write.flush();
				write.close();
			}
			return true;
		} catch (FileNotFoundException e) {
			log.error("文件不存在或文件不可操作" + e);
		} catch (IOException e) {
			log.error("读写IO流异常" + e);
		} catch (Exception e) {
			log.error(e);
		}
		return false;
	}
	
	 /**
     * 是否为2007版本
     * @param url
     * @return
     */
    private static boolean checkVersion(String url){
        return url.matches("^.+\\.(?i)(xlsx)$");  
    }
    
    /**
     * 
     * @param num
     * @return
     */
    private static int formatNum(String num){
    	if(StringUtil.isEmpty(num)||!StringUtil.isNumber(num)){
    		return 0;
    	}
    	return Integer.parseInt(num);
    }

}
