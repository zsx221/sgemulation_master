package com.macrochina.net.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class MessageUtils {

	private static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static final DateTimeFormatter YYYYMMDDHHMMSSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

	/**
	 * 获取金额
	 * @param min
	 * @param max
	 * @return
	 */
	public static BigDecimal getRandomDecimal(BigDecimal min, BigDecimal max){
		float minF = min.floatValue();
		float maxF = max.floatValue();
		//生成随机数
		BigDecimal db = new BigDecimal(Math.random() * (maxF - minF) + minF);
		//返回保留两位小数的随机数。不进行四舍五入
		return db.setScale(2,BigDecimal.ROUND_DOWN);
	}

	/**
	 * 世界标准时间转北京时间
	 * @param dateTime
	 * @return
	 */
	public static String strToTime(String dateTime){
//		String dateTime = "2020-01-13T16:00:00.000Z";
		dateTime = dateTime.replace("Z", " UTC");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = "";
		try {
			Date time = format.parse(dateTime);
			result = defaultFormat.format(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *将字符串格式yyyyMMdd的字符串转为日期，格式"yyyy-MM-dd"
	 *
	 * @return 返回格式化的日期
	 * 分析时意外地出现了错误异常
	 */
	public static String strToDate() {
		String format = "";
		try {
			format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}catch (Exception e){
			e.printStackTrace();
		}
		return format;
	}

	public static String str2Date(String ft) {
		String format = "";
		try {
			format = new SimpleDateFormat(ft).format(new Date());
		}catch (Exception e){
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 20110311BKAASGSGBAD0000001
	 */
	public static final String getOrgnlId(String bank) {
		StringBuilder strbuilder = new StringBuilder(26);
		strbuilder.append(LocalDateTime.now().format(YYYYMMDD)).append(bank)
				.append(RandomStringUtils.randomNumeric(7));
		return strbuilder.toString();
	}

	/**
	 * 生产随机数
	 * abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789
	 */
	public static String getRandom(int num,String str){
		String sb = new String();
		int index;
		char[] ch = str.toCharArray();
		Random r = new Random();
		int length = ch.length;
		for(int i = 0; i < num; i++){
			index = r.nextInt(length);
			sb += ch[index];
		}
		return sb;
	}

	/**
	 * 通知的id
	 * @return NOTI/HKD/+14数字
	 * 样例:NOTI/HKD/66812341115641
	 */
	public static String getNotifiId(){
		StringBuilder str = new StringBuilder(23);
		str.append("NOTI/HKD/").append(RandomStringUtils.randomNumeric(14));
		return str.toString();
	}

	/**
	 * EndToEndId
	 * @return CROPS/123/ +12数字
	 * 样例：12204199802
	 * CROPS/123/000000054878
	 */
	public static String getEndToEndId(){
		StringBuilder str = new StringBuilder(22);
		str.append("CROPS/123/").append(RandomStringUtils.randomNumeric(12));
		return str.toString();
	}

	/**
	 * TxId
	 * @return 产生一个 32位 数字+字母
	 * 样例：58932C54094B45D6BB0BA0D8F609B04A
	 */
	public static String getTxId(){
		return getRandom(32,"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
	}

	/**
	 * MsgId
	 * @return 产生一个 22位 字母+数字
	 * 样例：MICL201701050016602333
	 */
	public static final String genMsgId() {
		StringBuilder strbuilder = new StringBuilder(22);
		strbuilder.append("MICL")
		.append(LocalDateTime.now().format(YYYYMMDD))
		.append(RandomStringUtils.randomNumeric(10));
		return strbuilder.toString();
	}

	/**
	 * BtchId
	 * @return 产生一个 22位 字母+数字
	 * 样例：FPSPCRR5552017010500001001
	 */
	public static final String genBtchId() {
		StringBuilder strbuilder = new StringBuilder(22);
		strbuilder.append("FPSPCRR")
				.append(LocalDateTime.now().format(YYYYMMDD))
				.append(RandomStringUtils.randomNumeric(10));
		return strbuilder.toString();
	}

	/**
	 * ClrSysRef
	 * @return 产生一个 22位 数字+字母
	 * 样例：FRN2017010500335555224
	 */
	public static final String genClrSysRef() {
		StringBuilder strbuilder = new StringBuilder(22);
		strbuilder.append("FRN")
		.append(LocalDateTime.now().format(YYYYMMDD))
		.append(RandomStringUtils.randomNumeric(10));
		return strbuilder.toString();
	}

	/**
	 * AdrReqId
	 * 样例：M088201812010000000001
	 */
	public static final String getAdrReqId() {
		StringBuilder str = new StringBuilder(22);
		str.append("M").append(RandomStringUtils.randomNumeric(21));
		return str.toString();
	}

	/**
	 * RendId
	 * 样例：1000000000128
	 */
	public static final String getRendId() {
		return RandomStringUtils.randomNumeric(12);
	}


	/**
	 * 样例：2017010500335555224.xml
	 */
	public static final String genFileName() {
		StringBuilder strbuilder = new StringBuilder(26);
		strbuilder.append(LocalDateTime.now().format(YYYYMMDDHHMMSSS))
		.append(RandomStringUtils.randomAlphanumeric(5))
		.append(".xml");
		return  strbuilder.toString();
	}

	public static final String getXmlName() {
		StringBuilder strbuilder = new StringBuilder(26);
		strbuilder.append(LocalDateTime.now().format(YYYYMMDDHHMMSSS))
		.append(RandomStringUtils.randomAlphanumeric(5))
		.append(".xml");
		return  strbuilder.toString();
	}

	public static final String getJsonName() {
		StringBuilder strbuilder = new StringBuilder(26);
		strbuilder.append(LocalDateTime.now().format(YYYYMMDDHHMMSSS))
				.append(RandomStringUtils.randomAlphanumeric(5))
				.append(".json");
		return  strbuilder.toString();
	}

	public static final String getCTxnId (String bank) {
		StringBuilder builder = new StringBuilder(28);
		builder.append("M")
				.append(LocalDateTime.now().format(YYYYMMDD))
				.append(bank)
				.append("G")
				.append(RandomStringUtils.randomNumeric(10));
		return  builder.toString();
	}

	public static final String getCTxnId2 (String bank) {
		StringBuilder builder = new StringBuilder(26);
		builder.append(LocalDateTime.now().format(YYYYMMDD))
				.append(bank)
				.append(RandomStringUtils.randomNumeric(7));
		return  builder.toString();
	}

	public static final BigDecimal randomAmt(int count) {
		return new BigDecimal(new BigDecimal(new Random().nextInt(10000000))
				+ "." + new BigDecimal(new Random().nextInt(100)));
	}

	/**
	 * 生产不重复的4位数
	 */
	public static int random() {
		Random random = new Random(System.currentTimeMillis());
		int number = 0;
		boolean ok = true;
		do {
			ok = true;
			number = random.nextInt(9000) + 1000;
			int[] digits = {
					number / 1000 % 10,
					number / 100 % 10,
					number / 10 % 10,
					number % 10
			};
			for (int i = 0; i < 4 && ok; i++) {
				for (int j = i + 1; j < 4; j++) {
					if (digits[i] == digits[j]) {
						ok = false;
						break;
					}
				}
			}
		} while (!ok);
		return number;
	}
}
