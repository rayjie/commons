package commons;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.net.util.URLUtil;

public class UrlUtils {
	/**
	 * 在指定url后追加参数
	 * @param url
	 * @param data 参数集合 key = value
	 * @return
	 */
	private static String appendUrl(String url, Map<String, Object> data) {
	    String newUrl = url;
	    StringBuffer param = new StringBuffer();
	    for (String key : data.keySet()) {
	        param.append(key + "=" + data.get(key).toString() + "&");
	    }
	    String paramStr = param.toString();
	    paramStr = paramStr.substring(0, paramStr.length() - 1);
	    if (newUrl.indexOf("?") >= 0) {
	        newUrl += "&" + paramStr;
	    } else {
	        newUrl += "?" + paramStr;
	    }
	    return newUrl;
	}

	/**
	 * 获取指定url中的某个参数
	 * @param url
	 * @param name
	 * @return
	 */
	public static String getParamByUrl(String url, String name) {
	    url += "&";
	    String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";

	    Pattern r = Pattern.compile(pattern);

	    Matcher m = r.matcher(url);
	    if (m.find( )) {
//	        System.out.println(m.group(0));
	        return m.group(0).split("=")[1].replace("&", "");
	    } else {
	        return null;
	    }
	}
	/**
	 * 正则替换
	 * @param url
	 * @param name
	 * @param accessToken
	 * @return
	 */
	public static String replaceAccessTokenReg(String url, String name, String accessToken) {
//		if (StringUtils.isNotBlank(url) && StringUtils.isNotBlank(accessToken)) {
			url = url.replaceAll("(" + name + "=[^&]*)", name + "=" + accessToken);
//		}
		return url;
	}

	public static void main(String[] args) throws Exception {
	     Map<String, Object> param = new HashMap<String, Object>();
	     param.put("id", 1);
	     param.put("age", 18);
	    // System.out.println(appendUrl("http://test.com", param));
//	     System.out.println(appendUrl("http://test.com?name=a&id=", param));
	    // String url = "http://test.com?name=abd&id=1&age=18";
//	    String url = "http://www.xxxxx.com/login.do?#access_token=xxxx&express_id=yyyyy";
//	    System.out.println(getParamByUrl(url, "express_id"));
	     System.out.println("m?".indexOf("?"));
	}
}
