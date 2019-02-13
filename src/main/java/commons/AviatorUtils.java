package commons;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Options;

public class AviatorUtils {
	static class A{
		public boolean repate;
	}
	public static void main(String[] args) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("结算金额", new BigDecimal(3.232d));
//		params.put("可结算金额", new BigDecimal(5.8434d));
//		AviatorEvaluator.setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);
////		AviatorEvaluator.setOption(Options.TRACE_EVAL, true);
//		BigDecimal rs = (BigDecimal)AviatorEvaluator.execute("结算金额 / 可结算金额", params);
//		System.out.println(rs.stripTrailingZeros());
		System.out.println(new BigDecimal(String.valueOf(new Double(73.5d).toString())).stripTrailingZeros().setScale(2, BigDecimal.ROUND_FLOOR));
		System.out.println(new A().repate);
	}
}
