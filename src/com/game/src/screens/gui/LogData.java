package com.game.src.screens.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class LogData implements Cloneable {

	private LinkedList<LogElement> list = null;

	private int maxEntries = 8;

	public LogData() {

		init();
	}
	
	public void init(){
		this.list = new LinkedList<LogElement>();
	}
	
	 @Override
	    public Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }

	public LogElement getLineFromEnd(int i) {
		if (i < list.size())
			
			return list.get(list.size() - 1 - i);
		
		else
			return null;
	}
	
	public void addElement() {
		
		list.add(new LogElement());
		
	}
	
	public int getLength() {

		return list.size();
	}

	public void addToElement(String text) {

		list.getLast().addElement(text);

	}

	public void addToElement(String text, Color color) {

		list.getLast().addElement(text, color);

	}

	public void render(Graphics g) {

		Font fnt1 = new Font("arial", Font.BOLD, 15);
		g.setFont(fnt1);
		// //g.setColor(Color.red);
		//
		// g.drawString(list.get(0).getText(0), 100, 500);

		Graphics2D g2 = (Graphics2D) g;

		int length = getLength();

		for (int i = 0; i < length && i < maxEntries; i++) {
			LogElement current = getLineFromEnd(i);

			if (current == null) {
				continue;
			}
			int posX = 10;
			for (int j = 0; j < current.getLength(); j++) {

				g2.setColor(current.getcolorFG(j));
				g2.drawString(current.getText(j),
						posX + (j == 0 ? 0 : current.getTextLength(j, g2)),
						600 - 15 * i);
			}

		}

	}

	class LogElement {

		private LinkedList<String> text = new LinkedList<String>();

		private LinkedList<Color> colorFG = new LinkedList<Color>();

		public void addElement(String text) {

			this.text.add(text);

			this.colorFG.add(Color.WHITE);
		}

		public void addElement(String text, Color color) {

			this.text.add(text);

			this.colorFG.add(color);
		}

		public Color getcolorFG(int i) {
			return this.colorFG.get(i);
		}

		public String getText(int i) {
			return this.text.get(i);
		}

		public int getLength() {

			return this.text.size();
		}

		public int getTextLength(int i, Graphics2D g2) {

			String upToNow = "";

			for (int n = 0; n < i; n++) {
				upToNow += text.get(n);
			}
			int stringLen = (int) g2.getFontMetrics()
					.getStringBounds(upToNow, g2).getWidth();
			// int stringHei = (int) g2.getFontMetrics()
			// .getStringBounds(upToNow, g2).getHeight();

			// int suma = 0;
			// for (int n = 0; n < i; n++) {
			// suma += text.get(n).length();
			// }
			return stringLen;
		}

	}

}
