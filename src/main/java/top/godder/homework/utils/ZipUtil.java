package top.godder.homework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	public static File ZipFloder(String folderUrl, String zipName, String zipUrl) throws IOException {
		File floder = new File(folderUrl);
		File zipFloder = new File(zipUrl);
		if (!zipFloder.exists()) {
			zipFloder.mkdirs();
		}
		zipName += ".zip";
		ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipUrl + zipName));
		InputStream inputStream;
		for (File file: floder.listFiles()) {
			inputStream = new FileInputStream(file);
			zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
			int len = 0;
			while ((len = inputStream.read()) != -1) {
				zipOutputStream.write(len);
			}
			zipOutputStream.closeEntry();
		}
		zipOutputStream.close();
		return new File(zipUrl, zipName);
	}
}
