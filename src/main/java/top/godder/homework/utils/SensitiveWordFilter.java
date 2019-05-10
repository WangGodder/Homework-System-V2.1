package top.godder.homework.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class SensitiveWordFilter {
	private static HashMap<String, String> sensitiveWordMap;
	private static char[] specialChar = {' ', ',', '.', '\n', '\t', '*', '&', '#', '%', '￥', '$', '`', '\'', '\"', '-', '+', '=', '@'};
	
	public SensitiveWordFilter(List<String> words) {
		Set<String> keyWordMap = new HashSet<>(words);
		addSensitiveWord(keyWordMap);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addSensitiveWord(Set<String> keyWordMap) {
		sensitiveWordMap = new HashMap<>(keyWordMap.size());
		String key = null;
		Map nowMap = null;
		Map<String, String> newWordMap = null;
		Iterator<String> iterator = keyWordMap.iterator();
		while (iterator.hasNext()) {
			key = iterator.next();
			nowMap = sensitiveWordMap;
			for (int i = 0 ; i < key.length(); i++) {
				char keyChar = key.charAt(i);
				Object wordMap = nowMap.get(keyChar);
				if (wordMap != null) {
					nowMap = (Map)wordMap;
				}
				else {
					newWordMap = new HashMap<>();
					newWordMap.put("isEnd", "0");	// not the end of string word
					nowMap.put(keyChar, newWordMap);
					nowMap = newWordMap;
				}
				if (i == key.length() - 1) {
					nowMap.put("isEnd", "1");		// the end of string word
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public int CheckSensitiveWord(String txt, int beginIndex) {
		boolean flag = false;		// using when sensitive word only has one word
		int matchFlag = 0;			// the number of matching char
		char word = 0;
		Map nowMap = sensitiveWordMap;
		for (int i = beginIndex; i < txt.length(); i++) {
			word = txt.charAt(i);
			boolean isContinue = false;
			for (char c : specialChar) {
				if (word == c) {
					isContinue = true;
					break;
				}
			}
			if (isContinue && matchFlag > 0) {
				continue;
			}
			nowMap = (Map)nowMap.get(word);
			if (nowMap != null) {
				matchFlag ++;
				if ("1".equals(nowMap.get("isEnd"))) {
					flag = true;
				}
			}
			else {
				break;
			}
		}
		if (matchFlag < 2 && flag) {
			matchFlag = 0;
		}
		return matchFlag;
	}
	
	public boolean containSensitiveWord(String txt) {
		boolean flag = false;
		for (int i = 0; i < txt.length(); i++) {
			int matchFlag = CheckSensitiveWord(txt, i);
			if (matchFlag > 0) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
