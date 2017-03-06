package wr.com.utils;

import java.util.UUID;

public class UUid {
	public static String getUuid(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
}
