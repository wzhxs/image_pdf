package image.pdf.com.util;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.media.jai.codec.BMPEncodeParam;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import com.sun.media.jai.codec.TIFFEncodeParam;;

public final class ImgUtil {
	
	
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
			e.printStackTrace();
		}
	}
	
	/**
	 * tif转jpg 
	 * @param tifImage
	 */
    public static void tifToJpg(String tifImage) {
        FileSeekableStream fileSeekStream = null;
        try {
            fileSeekStream = new FileSeekableStream(tifImage);
            ImageDecoder dec = ImageCodec.createImageDecoder("tiff", fileSeekStream, null);
            
            int count = dec.getNumPages();
            
            String filePathPrefix = tifImage.substring(0, tifImage.lastIndexOf("."));
            JPEGEncodeParam jpegEncodeParam = new JPEGEncodeParam();
            for (int i = 0; i < count; i++) {
                RenderedImage renderedImage = dec.decodeAsRenderedImage(i);
                File imgFile = new File(filePathPrefix + "_" + i + ".jpg");
                System.out.println("第"+i+"分别保存至： " + imgFile.getCanonicalPath());
                ParameterBlock pb = new ParameterBlock();
                pb.addSource(renderedImage);
                pb.add(imgFile.toString());
                pb.add("JPEG");
                pb.add(jpegEncodeParam);
                RenderedOp renderedOp = JAI.create("filestore",pb);
                renderedOp.dispose();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (fileSeekStream != null){
                try {
                    fileSeekStream.close();
                } catch (IOException e) {
                }
                fileSeekStream = null;
            }
        }
    }
    
    /**
     * jpg转tif
     */
    public static void jpgToTif(String jpgImage) {
    	 if (jpgImage == null || "".equals(jpgImage.trim())){
             return ;    
         }
         if (!new File(jpgImage).exists()){
         	System.out.println("系统找不到指定文件："+jpgImage);
             return ;
         }
         
        OutputStream outputStream = null;
        try {
            RenderedOp renderOp = JAI.create("fileload", jpgImage);
            String tifFilePath = jpgImage.substring(0, jpgImage.lastIndexOf("."))+".tif";
            outputStream = new FileOutputStream(tifFilePath);
            TIFFEncodeParam tiffParam = new TIFFEncodeParam();
            ImageEncoder imageEncoder = ImageCodec.createImageEncoder("TIFF", outputStream, tiffParam);
            imageEncoder.encode(renderOp);
            System.out.println("jpg2Tif 保存至： " + tifFilePath);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
                outputStream = null;
            }
        }
    }
    
    /**
     * tif 转 bmp
     * @param tifImage
     */
    public static void tifToBmp(String tifImage){
    	if (tifImage == null || "".equals(tifImage.trim())){
            return ;    
        }
        if (!new File(tifImage).exists()){
        	System.out.println("系统找不到指定文件："+tifImage);
            return ;
        }
    	RenderedOp src = JAI.create("fileload", tifImage);
    	try {
    		String bmpFilePath = tifImage.substring(0, tifImage.lastIndexOf("."))+".bmp";
			OutputStream os = new FileOutputStream(bmpFilePath);
			BMPEncodeParam param = new BMPEncodeParam();
			ImageEncoder enc = ImageCodec.createImageEncoder("BMP", os,param);
			enc.encode(src);
			os.close();//关闭流
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
    }

    public static void main(String args[]) throws Exception{
        /* tif 转 jpg 格式*/
        tifToJpg("C:\\Users\\wang\\Desktop\\合同\\2.tif");
        /* jpg 转 tif 格式*/
//	        jpgToTif("d:/1.jpg");
        
    }

}
