package top.godder.homework.utils;

import java.net.URLEncoder;

public class StringUtils {
	public static String getUTF8String(String str) {
		StringBuilder sBuilder = new StringBuilder(str);
		String xmString, xmUTF8 = "";
		try {
			xmString = new String(sBuilder.toString().getBytes("UTF-8"));
			xmUTF8 = URLEncoder.encode(xmString, "UTF-8");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return xmUTF8;
	}
	
}
