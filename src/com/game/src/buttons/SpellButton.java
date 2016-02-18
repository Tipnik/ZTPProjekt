package com.game.src.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.main.Game;
import com.game.src.sound.MusicManager;
import com.game.src.sprites.Textures;
import com.game.src.stages.StageManager;

public class SpellButton extends Button {

	BufferedImage texture = null;
	boolean over = false;

	public SpellButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.texture = Textures.spellHeal;
		// TODO Auto-generated constructor stub
	}

	public SpellButton(int x, int y, int width, int height, String text, BufferedImage texture) {
		super(x, y, width, height, text);
		this.texture = texture;
	}

	@Override
	public void action() {

		if (text == "Heal") {
			ButtonMediator.useSpell(text);
		} else {
			ButtonMediator.useSpell(text);
		}

	}

	@Override
	public void render(Graphics2D g2d) {
		int stringLen = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
		int stringHei = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getHeight();
		if (clicked) {
			g2d.setColor(backgroundClickedColor);
		} else {
			g2d.setColor(backgroundColor);
		}
		g2d.fillRect(x, y, width, height);
		g2d.setColor(borderColor);
		// g2d.drawString(text, x + (-stringLen + width) / 2, y
		// + (stringHei + height) / 2 - 5);
		g2d.draw(rectangle);

		g2d.drawImage(this.texture, x + 1, y + 1, null);

		if (Game.mouseX > x && Game.mouseX < x + width) {
			if (Game.mouseY > y && Game.mouseY < y + height) {
				over = true;
				g2d.fillRect(Game.WIDTH / 2 + 218, y + 40, 180, height);

				Font current = g2d.getFont();
				Font fnt1 = new Font("arial", Font.BOLD | Font.ITALIC, 15);
				g2d.setFont(fnt1);

				g2d.setColor(Color.BLACK);
				g2d.drawString(text, Game.WIDTH / 2 + 218, y + 40 - 15 + (stringHei + height) / 2 - 5);
				if (text == "Heal")
					g2d.drawString("2 many", Game.WIDTH / 2 + 218, y + 40 + (stringHei + height) / 2 - 5);
				else
					g2d.drawString("3 many, wymaga celu", Game.WIDTH / 2 + 218, y + 40 + (stringHei + height) / 2 - 5);

				g2d.setFont(current);
			} else {
				over = false;
			}
		} else {
			over = false;
		}

	}

}
