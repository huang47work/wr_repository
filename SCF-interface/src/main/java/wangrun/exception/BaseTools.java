package wangrun.exception;

import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

public class BaseTools {
	
	/**
	 * 
	 * @param data
	 * @param salt
	 * @return
	 */
	public static String encrypt(String data, String salt) {
		return DigestUtils.md5Hex(data + "{" + salt + "}");
	}


	/***
	 * 生成指定位数的位随机数
	 * 
	 * @return 字符串
	 * @param 生成几位
	 */
	public static String getRamdomCode(int requireNum) {
		String randomcode = "";
		String finalString = "";
		for (int i = 1; i <= requireNum; i++) {
			Random aa = new Random();
			int anum = aa.nextInt(9);
			randomcode = new Integer(anum).toString();
			finalString += randomcode;
		}
		return finalString;
	}

	/*
	 * 判断是否为整数
	 * 
	 * @param str 传入的字符串
	 * 
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	
	/**
	 * 删除指定目录的目录以及目录下的子文件
	 * @param path 基础路径如: D:\\temp\\wxcs
	 * @param userPath 需要删除的目录如: myFolder
	 * @param inputFile 初次调用时默认为空如 : new File("") 
	 */
	public static void deleteAllFiles(String path, String userPath, File inputFile) {
		File fileDir = null;
		if(inputFile.getPath().equals("")){
			fileDir = new File(path + "\\"+ userPath);
		}else{
			fileDir = inputFile;
		}
		File[] files = fileDir.listFiles();
		if(files != null){
	        for(File file : files){
	            //如果是目录则继续进入遍历
	            if(file.isDirectory()){
	            	deleteAllFiles(path,userPath,file);
	            }else{
	            	file.delete();
	            }
	        }
	        //删除当前目录
	        fileDir.delete();
		}
	}
	
	public static String PayStatus(String strStatus) {
		int n = Integer.parseInt(strStatus);
		switch(n) {
		case 0:
			return "未支付";
		case 1:
			return "已支付";
		case 2:
			return "已撤销";
		default:
			return "";
		}
	}
	public static void main(String[] args) {
		System.out.println(PayStatus("1"));
	}
}
