package image.pdf.com.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class ImgToPdf {
	
	/**
	 * 多文件生成pdf
	 * @param imageList
	 * @param pdfFile
	 */
	public static void moreFile(List<File> imageList,String pdfFile){
		 try {
	            File pdf=new File(pdfFile);
	            if(!pdf.exists()){
	            	pdf.createNewFile();
	            }
	            
	            BufferedImage img = null;
	            Image image = null;
	            String fileString;
	            FileOutputStream fos = new FileOutputStream(pdf);
	            Document doc = new Document(null, 0, 0, 0, 0);
	            PdfWriter.getInstance(doc, fos);
	            
	            for(File imageFile:imageList){
	            	fileString=imageFile.toString();
	                img = ImageIO.read(imageFile);
	                doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
	                image = Image.getInstance(fileString);
	                doc.open();
	                doc.add(image);
	            }
	            doc.close();
                fos.flush();
                fos.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (BadElementException e) {
	            e.printStackTrace();
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	}
	
	/**
	 * 单文件生成pdf
	 * @param imageFile
	 * @param pdfFile
	 */
	public static void oneFile(List<File> imageList,String pdfUrl){
		try{
            File pdf=new File(pdfUrl);
            if(!pdf.exists()){
            	pdf.mkdirs();
            }
            
            Image image=null;
            String fileString;
            for(File imageFile:imageList){
            	fileString =imageFile.toString();
            	FileOutputStream fos = new FileOutputStream(pdfUrl+File.separator+imageFile.getName()+".pdf");
                Document doc = new Document(null, 0, 0, 0, 0);
                doc.open();
                PdfWriter.getInstance(doc, fos);
                BufferedImage img = ImageIO.read(imageFile);
                doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
                image = Image.getInstance(fileString);
                doc.open();
                doc.add(image);
                doc.close();
                fos.flush();
                fos.close();
                Thread.sleep(50);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
