package commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 正则工具类
 * @author: mmj
 * @date:2018年11月29日 上午10:36:05
 */
public class RegexUtils {
	private static final Pattern URL_SPECIAL = Pattern.compile("[+\\s/?%#&=]+");
	private static final Pattern IntegerGteZeroPattern = Pattern.compile("^\\d+$");
	private static final String NUMBER_REG="^-?\\d+(\\.\\d+)?$";
	
	/**
	 * 数字，正负，小数，整数
	 * @param content
	 * @return
	 */
	public static boolean isNumber(String content) {
		return Pattern.matches(NUMBER_REG, content);
	}
	
	/**
	 * 是否正整数，且大于等于0
	 */
	public static boolean isIntegerGte0(String source) {
		return IntegerGteZeroPattern.matcher(source).matches();
	}
	
	/**
	 * 是否包含URL特殊字符
	 */
	public static boolean ContainUrlSpecialChar(String url) {
		Matcher m = URL_SPECIAL.matcher(url);
		return m.find();
	}
}
