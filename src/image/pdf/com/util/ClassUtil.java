package image.pdf.com.util;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import image.pdf.com.core.inter.PanelInter;
import javafx.scene.control.MenuBar;

/**
 * 加载类工具类
 * 
 * @author wangzh
 * 2018年7月3日 上午10:39:25
 */
public class ClassUtil {

	  public static ClassLoader getClassLoader(){
	    return Thread.currentThread().getContextClassLoader();
	  }
	  
	  /**
	   * 自定义类
	   * @param menuBar
	   * @param menuClass
	   */
	  public static MenuBar load(MenuBar menuBar, Class<?>[] panelClass) {
		  for(Class<?> clazz:panelClass){
				PanelInter panel;
				try {
					panel = (PanelInter) clazz.newInstance();
					menuBar.getMenus().add(panel.init());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		  return menuBar;
	  }
	  
	  /**
	   * 自动扫描类，缺点，无法指定顺序
	   * @param menuBar
	   */
	  public static void load(MenuBar menuBar){
		  List<Class<?>> fileList=getClassListByInterface("image.pdf.com",PanelInter.class);
		  Class<?>[] panelClass = new Class<?>[fileList.size()];
		  fileList.toArray(panelClass);
		  
		  load(menuBar,panelClass);
	  }

	  public static String getClassPath(){
	    String classpath = "";
	    URL resource = getClassLoader().getResource("");
	    if (resource != null) {
	      classpath = resource.getPath();
	    }
	    return classpath;
	  }
	  
	  /**
	   * @param className 带包名类名，不带后缀.java,实例com.tool.cn.util.ByteUtil
	   * @return
	   */
	  public static Class<?> loadClass(String className){
	    return loadClass(className, true);
	  }

	  public static Class<?> loadClass(String className, boolean isInitialized){
	    Class<?> cls;
	    try{
	      cls = Class.forName(className, isInitialized, getClassLoader());
	    } catch (ClassNotFoundException e) {
	      System.err.println("加载类出错！");
	      throw new RuntimeException(e);
	    }
	    return cls;
	  }

	  public static boolean isInt(Class<?> type){
	    return (type.equals(Integer.TYPE)) || (type.equals(Integer.class));
	  }

	  public static boolean isLong(Class<?> type){
	    return (type.equals(Long.TYPE)) || (type.equals(Long.class));
	  }

	  public static boolean isDouble(Class<?> type){
	    return (type.equals(Double.TYPE)) || (type.equals(Double.class));
	  }

	  public static boolean isString(Class<?> type){
	    return type.equals(String.class);
	  }
	  
	  // 获取指定包名下的所有类
	   public static List<Class<?>> getClassList(String packageName, boolean isRecursive) {
	       List<Class<?>> classList = new ArrayList<Class<?>>();
	       try {
	           Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
	           while (urls.hasMoreElements()) {
	               URL url = urls.nextElement();
	               if (url != null) {
	                   String protocol = url.getProtocol();
	                   if (protocol.equals("file")) {
	                       String packagePath = url.getPath();
	                       addClass(classList, packagePath, packageName, isRecursive);
	                   } else if (protocol.equals("jar")) {
	                       JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
	                       JarFile jarFile = jarURLConnection.getJarFile();
	                       Enumeration<JarEntry> jarEntries = jarFile.entries();
	                       while (jarEntries.hasMoreElements()) {
	                           JarEntry jarEntry = jarEntries.nextElement();
	                           String jarEntryName = jarEntry.getName();
	                           if (jarEntryName.endsWith(".class")) {
	                               String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
	                               if (isRecursive || className.substring(0, className.lastIndexOf(".")).equals(packageName)) {
	                                   classList.add(Class.forName(className));
	                               }
	                           }
	                       }
	                   }
	               }
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return classList;
	   }

	   // 获取指定包名下指定注解的所有类
	   public static List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
	       List<Class<?>> classList = new ArrayList<Class<?>>();
	       try {
	           Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
	           while (urls.hasMoreElements()) {
	               URL url = urls.nextElement();
	               if (url != null) {
	                   String protocol = url.getProtocol();
	                   if (protocol.equals("file")) {
	                       String packagePath = url.getPath();
	                       addClassByAnnotation(classList, packagePath, packageName, annotationClass);
	                   } else if (protocol.equals("jar")) {
	                       JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
	                       JarFile jarFile = jarURLConnection.getJarFile();
	                       Enumeration<JarEntry> jarEntries = jarFile.entries();
	                       while (jarEntries.hasMoreElements()) {
	                           JarEntry jarEntry = jarEntries.nextElement();
	                           String jarEntryName = jarEntry.getName();
	                           if (jarEntryName.endsWith(".class")) {
	                               String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
	                               Class<?> cls = Class.forName(className);
	                               if (cls.isAnnotationPresent(annotationClass)) {
	                                   classList.add(cls);
	                               }
	                           }
	                       }
	                   }
	               }
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return classList;
	   }

	   // 获取指定包名下指定父类的所有类
	   public static List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
	       List<Class<?>> classList = new ArrayList<Class<?>>();
	       try {
	           Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
	           while (urls.hasMoreElements()) {
	               URL url = urls.nextElement();
	               if (url != null) {
	                   String protocol = url.getProtocol();
	                   if (protocol.equals("file")) {
	                       String packagePath = url.getPath();
	                       addClassBySuper(classList, packagePath, packageName, superClass);
	                   } else if (protocol.equals("jar")) {
	                       JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
	                       JarFile jarFile = jarURLConnection.getJarFile();
	                       Enumeration<JarEntry> jarEntries = jarFile.entries();
	                       while (jarEntries.hasMoreElements()) {
	                           JarEntry jarEntry = jarEntries.nextElement();
	                           String jarEntryName = jarEntry.getName();
	                           if (jarEntryName.endsWith(".class")) {
	                               String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
	                               Class<?> cls = Class.forName(className);
	                               if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
	                                   classList.add(cls);
	                               }
	                           }
	                       }
	                   }
	               }
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return classList;
	   }

	   // 获取指定包名下指定接口的所有实现类
	   public static List<Class<?>> getClassListByInterface(String packageName, Class<?> interfaceClass) {
	       List<Class<?>> classList = new ArrayList<Class<?>>();
	       try {
	           Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
	           while (urls.hasMoreElements()) {
	               URL url = urls.nextElement();
	               if (url != null) {
	                   String protocol = url.getProtocol();
	                   if (protocol.equals("file")) {
	                       String packagePath = url.getPath();
	                       addClassByInterface(classList, packagePath, packageName, interfaceClass);
	                   } else if (protocol.equals("jar")) {
	                       JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
	                       JarFile jarFile = jarURLConnection.getJarFile();
	                       Enumeration<JarEntry> jarEntries = jarFile.entries();
	                       while (jarEntries.hasMoreElements()) {
	                           JarEntry jarEntry = jarEntries.nextElement();
	                           String jarEntryName = jarEntry.getName();
	                           if (jarEntryName.endsWith(".class")) {
	                               String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
	                               Class<?> cls = Class.forName(className);
	                               if (interfaceClass.isAssignableFrom(cls) && !interfaceClass.equals(cls)) {
	                                   classList.add(cls);
	                               }
	                           }
	                       }
	                   }
	               }
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return classList;
	   }

	   private static void addClass(List<Class<?>> classList, String packagePath, String packageName, boolean isRecursive) {
	       try {
	           File[] files = getClassFiles(packagePath);
	           if (files != null) {
	               for (File file : files) {
	                   String fileName = file.getName();
	                   if (file.isFile()) {
	                       String className = getClassName(packageName, fileName);
	                       classList.add(Class.forName(className));
	                   } else {
	                       if (isRecursive) {
	                           String subPackagePath = getSubPackagePath(packagePath, fileName);
	                           String subPackageName = getSubPackageName(packageName, fileName);
	                           addClass(classList, subPackagePath, subPackageName, isRecursive);
	                       }
	                   }
	               }
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }

	   private static File[] getClassFiles(String packagePath) {
	       return new File(packagePath).listFiles(new FileFilter() {
	           @Override
	           public boolean accept(File file) {
	               return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
	           }
	       });
	   }

	   private static String getClassName(String packageName, String fileName) {
	       String className = fileName.substring(0, fileName.lastIndexOf("."));
	       if (packageName!=null) {
	           className = packageName + "." + className;
	       }
	       return className;
	   }

	   private static String getSubPackagePath(String packagePath, String filePath) {
	       String subPackagePath = filePath;
	       if (packagePath!=null) {
	           subPackagePath = packagePath + "/" + subPackagePath;
	       }
	       return subPackagePath;
	   }

	   private static String getSubPackageName(String packageName, String filePath) {
	       String subPackageName = filePath;
	       if (packageName!=null) {
	           subPackageName = packageName + "." + subPackageName;
	       }
	       return subPackageName;
	   }

	   private static void addClassByAnnotation(List<Class<?>> classList, String packagePath, String packageName, Class<? extends Annotation> annotationClass) {
	       try {
	           File[] files = getClassFiles(packagePath);
	           if (files != null) {
	               for (File file : files) {
	                   String fileName = file.getName();
	                   if (file.isFile()) {
	                       String className = getClassName(packageName, fileName);
	                       Class<?> cls = Class.forName(className);
	                       if (cls.isAnnotationPresent(annotationClass)) {
	                           classList.add(cls);
	                       }
	                   } else {
	                       String subPackagePath = getSubPackagePath(packagePath, fileName);
	                       String subPackageName = getSubPackageName(packageName, fileName);
	                       addClassByAnnotation(classList, subPackagePath, subPackageName, annotationClass);
	                   }
	               }
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }

	   private static void addClassBySuper(List<Class<?>> classList, String packagePath, String packageName, Class<?> superClass) {
	       try {
	           File[] files = getClassFiles(packagePath);
	           if (files != null) {
	               for (File file : files) {
	                   String fileName = file.getName();
	                   if (file.isFile()) {
	                       String className = getClassName(packageName, fileName);
	                       Class<?> cls = Class.forName(className);
	                       if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
	                           classList.add(cls);
	                       }
	                   } else {
	                       String subPackagePath = getSubPackagePath(packagePath, fileName);
	                       String subPackageName = getSubPackageName(packageName, fileName);
	                       addClassBySuper(classList, subPackagePath, subPackageName, superClass);
	                   }
	               }
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }

	   private static void addClassByInterface(List<Class<?>> classList, String packagePath, String packageName, Class<?> interfaceClass) {
	       try {
	           File[] files = getClassFiles(packagePath);
	           if (files != null) {
	               for (File file : files) {
	                   String fileName = file.getName();
	                   if (file.isFile()) {
	                       String className = getClassName(packageName, fileName);
	                       Class<?> cls = Class.forName(className);
	                       if (interfaceClass.isAssignableFrom(cls) && !interfaceClass.equals(cls)) {
	                           classList.add(cls);
	                       }
	                   } else {
	                       String subPackagePath = getSubPackagePath(packagePath, fileName);
	                       String subPackageName = getSubPackageName(packageName, fileName);
	                       addClassByInterface(classList, subPackagePath, subPackageName, interfaceClass);
	                   }
	               }
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }

}
