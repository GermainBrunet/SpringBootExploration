package ca.gb.sf.letters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class DrawAny {

	private static String TARGET_FOLDER = "c:\\opt\\letters\\";

	// private static String SIZE = "32";

	static public void main(String args[]) throws Exception {

		Map<String, String> iconList = new HashMap<String, String>();
		
		iconList.put("speaker", "icons8-speaker-52.png");
		iconList.put("trash", "icons8-trash-52.png");
		iconList.put("up", "icons8-up-52.png");
		iconList.put("down", "icons8-down-arrow-52.png");
		
		try {

			for (String iconName : iconList.keySet()) {
				
				draw(iconName, iconList.get(iconName), 24);
				draw(iconName, iconList.get(iconName), 32);
				draw(iconName, iconList.get(iconName), 42);
				draw(iconName, iconList.get(iconName), 58);
				draw(iconName, iconList.get(iconName), 72);
				draw(iconName, iconList.get(iconName), 100);

			}

		} catch (IOException ie) {
			ie.printStackTrace();
		}

	}

	static public void draw(String iconName, String fileName, double size) throws Exception {
		
		FileInputStream fileInputStreamReader = null;
		
		try {
			int width = new Double(size).intValue();
			int height = new Double(size).intValue();

			// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
			// into integer pixels
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			BufferedImage a = ImageIO.read(new File("C:\\Users\\germa\\git\\SpringBootExploration\\src\\test\\java\\ca\\gb\\sf\\letters\\" + fileName));
			
			Graphics2D ig2 = bi.createGraphics();

			ig2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			ig2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			int roundedEdge = new Double(size / 100 * 30).intValue();

			int space = new Double(size / 10).intValue();

			ig2.setPaint(Color.gray);
			ig2.fillRoundRect(space, space, width - space, width - space, roundedEdge, roundedEdge);

			ig2.setPaint(Color.CYAN);
			ig2.fillRoundRect(0, 0, width - space, width - space, roundedEdge, roundedEdge);

			ig2.setPaint(Color.black);
			ig2.drawRoundRect(0, 0, width - space, width - space, roundedEdge, roundedEdge);

			// ig2.drawImage(a,  0,  0,  null);
			ig2.drawImage(a,  space,  space,  width - space - space - (space / 2), width - space - space - (space / 2), null);
			
			// Shape shape = new Rectangle(1,1,20,20);
			// ig2.draw(shape);

			/**
			int fontSize = new Double(size / 4 * 3).intValue();

			Font font = new Font("Calibri", Font.BOLD, fontSize);
			ig2.setFont(font);
			FontMetrics fontMetrics = ig2.getFontMetrics();
			int stringWidth = fontMetrics.stringWidth(letter);
			int stringHeight = fontMetrics.getAscent();

			int offset = 1;

			ig2.setPaint(Color.gray);
			ig2.drawString(letter, (width - stringWidth) / 2 + offset - 1, height / 2 + stringHeight / 4 + offset + 1);

			ig2.setPaint(Color.black);
			ig2.drawString(letter, (width - stringWidth) / 2 - 1, height / 2 + stringHeight / 4 + 1);
			**/

			int sizeFileName = new Double(size).intValue();

			ImageIO.write(bi, "PNG", new File(TARGET_FOLDER + iconName + sizeFileName + ".PNG"));

			File file = new File(TARGET_FOLDER + iconName + sizeFileName + ".PNG");

			fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			// String encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
			String encodedFile = Base64.getEncoder().encodeToString(bytes);
			// System.out.println(encodedFile);

			// String fullString = "var " + name + "IconURI" + sizeFileName + " = 'data:image/png;base64," + encodedFile
			// 		+ "';";
			
			String fullString = "letters.set('" + iconName + "IconURI" + sizeFileName + "', 'data:image/png;base64," + encodedFile + "');";

			System.out.println(fullString);

			// encodedfile = Base64.encodeBase64(bytes).toString();
			// ImageIO.write(bi, "JPEG", new File(TARGET_FOLDER + "yourImageName_" + letter
			// + ".JPG"));
			// ImageIO.write(bi, "gif", new File("c:\\temp\\yourImageName.GIF"));
			// ImageIO.write(bi, "BMP", new File("c:\\temp\\yourImageName.BMP"));

		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			
			fileInputStreamReader.close();
			
		}

	}

}
