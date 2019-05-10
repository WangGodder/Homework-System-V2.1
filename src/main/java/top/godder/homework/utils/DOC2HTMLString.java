package top.godder.homework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;

public class DOC2HTMLString {
	
	public static String docx2html(String fileName, String cache, String imgSrc) throws IOException {
		 InputStream is = new FileInputStream(fileName);  
	     XWPFDocument doc = new XWPFDocument(is);  
	     if (imgSrc == null) {
	    	 imgSrc = "../cache/";
	     }
	     cacheImage(doc, cache);
	     String html = "";
	     for (IBodyElement element: doc.getBodyElements()) {
	    	 switch (element.getElementType()) {
			case PARAGRAPH:
				XWPFParagraph paragraph = (XWPFParagraph)element;
				html += paragraphConvert(paragraph, imgSrc);
				break;
			case TABLE:
				XWPFTable table = (XWPFTable)element;
				html += tableConvert(table, imgSrc);
			default:
				break;
			}
	     }
	     return html;
	}
	
	private static String paragraphConvert(XWPFParagraph paragraph, String imgSrc) {
		String html = "<p ";
		CTPPr pr = paragraph.getCTP().getPPr();
		if (pr != null) {
			html += "style=\"";
			try {
				if (pr.getJc() != null) {
					html += ("text-align:" + pr.getJc().getVal() + ";");
				}
				if (pr.getRPr() != null) {
					int fontSize = pr.getRPr().getSz().getVal().intValue();
					html += ("font-size:" + fontSize/1.5 + "px;");
					if (pr.getRPr().getColor() != null) {
						String colorStr = pr.getRPr().getColor().toString();
						String color = "#" +  colorStr.substring(colorStr.indexOf("w:val=") + 7, colorStr.indexOf("w:val=") + 13);
						html += ("color:" + color + ";");
					}
				}
			}
			catch (NullPointerException e) {
			}

			html += ("\"");
		}
		html += (">");
		html += (paragraph.getText());
		for (XWPFRun run : paragraph.getRuns()) {
			 if(run.getCTR().xmlText().indexOf("<w:drawing>")!=-1){  
                String runXmlText = run.getCTR().xmlText();  
                String block = runXmlText.substring(runXmlText.indexOf("r:embed"), runXmlText.indexOf("/>", runXmlText.indexOf("r:embed")));
                String name = block.split("\"")[1];
                block = runXmlText.substring(runXmlText.indexOf("<pic:cNvPr"), runXmlText.indexOf("/>", runXmlText.indexOf("<pic:cNvPr")));
                String format = "png";
                try {
               	 format = block.substring(block.indexOf("name") + 6,	block.length() - 1).split("\\.")[1];
                } catch(Exception e) {}
                name += ".";
                html += ("<img src=\"" + imgSrc + name + format + "\"/>");
			 }
		}
		html += ("</p>");
		return html;
	}
	
	private static String tableConvert(XWPFTable table, String imgSrc) {
		String html = "<table ";
		CTTblPr pr = table.getCTTbl().getTblPr();
		if (pr != null) {
			html += ("style=\"");
			try {
				if (pr.getJc() != null) {
					html += ("text-align:" + pr.getJc().getVal() + ";");
				}
				if (pr.getTblBorders() != null) {
					html += ("border:solid black " + pr.getTblBorders().getTop().getSz() + "px;");
				}
			} catch (NullPointerException e) {
			}
			html += ("\"");
		}
		html += (">");
	    List<XWPFTableCell> cells;  
		int maxCol = 1;
		for (XWPFTableRow row: table.getRows()) {
			html += ("<tr>");
			cells = row.getTableCells();
			if (cells.size() > maxCol)
				maxCol = cells.size();
			for (XWPFTableCell cell: cells) {
				if (cells.size() == 1) {
					html += ("<td colspan=\"" + maxCol + "\">");
				} else {
					html += "<td>";
				}
				for (XWPFParagraph paragraph: cell.getParagraphs()) {
					html += paragraphConvert(paragraph, imgSrc);
				}
				html += "</td>";
			}
			html += ("</tr>");
		}
		html += ("</table>");
		return html;
	}
	
	private static void cacheImage(XWPFDocument doc, String cacheUrl) throws IOException {
		File folder = new File(cacheUrl);
		if (!folder.exists()) 
			folder.mkdirs();
		for (XWPFPictureData picture: doc.getAllPictures()) {
			String id = picture.getParent().getRelationId(picture);
			String name = id + "." + picture.getFileName().split("\\.")[1];
			File savepath = new File(cacheUrl, name);
			FileOutputStream fileOutputStream = new FileOutputStream(savepath);
			fileOutputStream.write(picture.getData());
			fileOutputStream.close();
		}
	}
	
}
