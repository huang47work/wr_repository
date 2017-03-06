package wr.com.utils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机数
 * 
 * @author 郭杰
 * @since Dec 23,2016
 * @version 1.0.1
 *
 */
public class RandomUtils {

	static Random random = new Random();

	public static int getRandom() {
		return random.nextInt(1000);
	}

	/**
	 * 日期 + 随机数
	 * 
	 * @param number
	 * @return
	 */
	public static String getRandom32(int number) {
		return (DateUtil.dateToString(new Date(), "yyyy-MM-dd") + UUID.randomUUID().toString().substring(0, number))
				.replaceAll("\\-", "");
	}
	
	/**
	 * 生成9位单号
	 * 
	 * @see 年月日 yyMMdd
	 * @see Random 3
	 * @see
	 * @return
	 */
	public static String generateRandomNum(){
		return DateUtils.formatDate(new Date(), "yyMMdd") + UUid.getUuid().substring(0, 3).toUpperCase();
	}
}
