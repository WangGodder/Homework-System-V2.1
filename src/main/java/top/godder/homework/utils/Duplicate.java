package top.godder.homework.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Duplicate {

	public static boolean sentenceCheck(String orginSentence, String otherSentence, double thresholdProp) {
		float duplicateNum = 0;
		int j = 0;
		for (int i = 0; i < orginSentence.length(); i++) {
			char c = orginSentence.charAt(i);
			if (c == ' ' || c == ',' || c == '.' || c == '，' || c == '。' || c == ';')
				continue;
			boolean isContain = false;
			for (; j < otherSentence.length(); j++) {
				if (c == otherSentence.charAt(j)) {
					isContain = true;
					duplicateNum++;
					break;
				}
			}
			if (!isContain) {
				j = 0;
			}
		}
		if ((duplicateNum / orginSentence.length()) > thresholdProp) {
			return true;
		}
		return false;
	}
	
	public static String[] readDocx(File file) {
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(fis);
            @SuppressWarnings("resource")
			XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            str = extractor.getText();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.split("\n");
	}
	
	public static double checkSentences(String[] sentences, String[] otherSentences, double thresholdProp) {
		double duplicateNum = 0;
		for (String str: sentences) {
			for (String otherStr: otherSentences) {
				if (sentenceCheck(str, otherStr, thresholdProp)) {
					duplicateNum ++;
					break;
				}
			}
		}
		return (duplicateNum/sentences.length) * 100;
	}
}
