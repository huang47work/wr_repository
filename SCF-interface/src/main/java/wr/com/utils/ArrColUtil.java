package wr.com.utils;

import java.util.Arrays;
import java.util.List;

public class ArrColUtil {
	public static String arrToColString(Object[] arr){
		StringBuffer  a = new StringBuffer();
		System.out.println(Arrays.toString(arr));
		for (int i = 0; i < arr.length; i++) {
			a.append(arr[i].toString());
			if (i!=(arr.length-1)) {
			a.append(",");
			}
		}
		String b  = a.toString();
		return b;
	}
	public static List<String> colToList(String arrString){
		String[] a = arrString.split(",");
		List<String> userList = Arrays.asList(a);
		return userList;
	}
}
