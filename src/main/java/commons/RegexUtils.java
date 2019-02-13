package commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * ���򹤾���
 * @author: mmj
 * @date:2018��11��29�� ����10:36:05
 */
public class RegexUtils {
	private static final Pattern URL_SPECIAL = Pattern.compile("[+\\s/?%#&=]+");
	private static final Pattern IntegerGteZeroPattern = Pattern.compile("^\\d+$");
	private static final String NUMBER_REG="^-?\\d+(\\.\\d+)?$";
	private static final Pattern DATE_FORMAT_V1 =Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
	private static final Pattern DATE_FORMAT_V2 = Pattern.compile("\\d{4}/\\d{2}/\\d{2}");
	private static final Pattern DATE_TIME_FORMAT_V1 =Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s{1}\\d{2}:\\d{2}:\\d{2}(.\\d{1,3}){0,1}");
	private static final Pattern DATE_TIME_FORMAT_V2 = Pattern.compile("\\d{4}/\\d{2}/\\d{2}\\s{1}\\d{2}:\\d{2}:\\d{2}(.\\d{1,3}){0,1}");
	/**
	 * ���֣�������С��������
	 * @param content
	 * @return
	 */
	public static boolean isNumber(String content) {
		return Pattern.matches(NUMBER_REG, content);
	}
	
	/**
	 * �Ƿ����������Ҵ��ڵ���0
	 */
	public static boolean isIntegerGte0(String source) {
		return IntegerGteZeroPattern.matcher(source).matches();
	}
	
	/**
	 * �Ƿ����URL�����ַ�
	 */
	public static boolean ContainUrlSpecialChar(String url) {
		Matcher m = URL_SPECIAL.matcher(url);
		return m.find();
	}
	
	public static boolean dateFromatV1(String source) {
		return DATE_FORMAT_V1.matcher(source).matches();
	}
	
	public static boolean dateFromatV2(String source) {
		return DATE_FORMAT_V2.matcher(source).matches();
	}
	
	public static boolean dateTimeFromatV1(String source) {
		return DATE_TIME_FORMAT_V1.matcher(source).matches();
	}
	
	public static boolean dateTimeFromatV2(String source) {
		return DATE_TIME_FORMAT_V2.matcher(source).matches();
	}
	
	public static void main(String[] args) {
		System.out.println(dateFromatV2("2019/21/21"));
		System.out.println(dateFromatV1("2019-21-21"));
		System.out.println(dateTimeFromatV1("2019-21-21 12:00:22.011"));
		System.out.println(dateTimeFromatV2("2019/21/21 12:21:22"));
		Pattern s = Pattern.compile("1|2");
	}
}
