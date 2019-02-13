package commons;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author: mmj
 * @date:2018��12��11�� ����9:22:43
 */
public class IpUtils {
	
	/**
	 * ��ȡ����IP����127.0.0.1
	 * @author: mmj
	 * @return
	 */
	public static String getHostIp(){
		try{
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()){
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()){
					InetAddress ip = (InetAddress) addresses.nextElement();
					if (ip != null 
							&& ip instanceof Inet4Address
                    		&& !ip.isLoopbackAddress() //loopback��ַ��������ַ��IPv4��loopback��Χ��127.0.0.0 ~ 127.255.255.255
                    		&& ip.getHostAddress().indexOf(":")==-1){
						return ip.getHostAddress();
					} 
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ��ȡ����IP
	 * @author: mmj
	 * @param request
	 * @return
	 */
	public static String getIPAddress(HttpServletRequest request) {
	    String ip = null;

	    //X-Forwarded-For��Squid �������
	    String ipAddresses = request.getHeader("X-Forwarded-For");

	    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        //Proxy-Client-IP��apache �������
	        ipAddresses = request.getHeader("Proxy-Client-IP");
	    }

	    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        //WL-Proxy-Client-IP��weblogic �������
	        ipAddresses = request.getHeader("WL-Proxy-Client-IP");
	    }

	    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        //HTTP_CLIENT_IP����Щ���������
	        ipAddresses = request.getHeader("HTTP_CLIENT_IP");
	    }

	    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        //X-Real-IP��nginx�������
	        ipAddresses = request.getHeader("X-Real-IP");
	    }

	    //��Щ����ͨ����������ô��ȡ����ip�ͻ��ж����һ�㶼��ͨ�����ţ�,���ָ�������ҵ�һ��ipΪ�ͻ��˵���ʵIP
	    if (ipAddresses != null && ipAddresses.length() != 0) {
	        ip = ipAddresses.split(",")[0];
	    }

	    //���ǲ��ܻ�ȡ���������ͨ��request.getRemoteAddr();��ȡ
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        ip = request.getRemoteAddr();
	    }
	    return ip;
	}
}
