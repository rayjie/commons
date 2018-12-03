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
}
