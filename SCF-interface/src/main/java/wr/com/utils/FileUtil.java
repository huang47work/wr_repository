package wr.com.utils;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	public  static void creatRootTxtFile(String roadPath,String fileName){
		System.out.println(new File("").getAbsolutePath()+roadPath);
		System.out.println(new File(new File("").getAbsolutePath()+roadPath).exists());
		String path = new File("").getAbsolutePath()+roadPath;
		if (!new File(path).exists()) {
			new File(path).mkdirs();
		}
		File file = new File(path+"/"+fileName+"txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
