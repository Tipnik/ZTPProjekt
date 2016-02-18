package com.game.src.screens.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.entity.Player;
import com.game.src.entity.enemies.Enemy;
import com.game.src.main.GameMediator;
import com.game.src.sprites.flyweight.ImageFactory;
import com.game.src.stages.StageManager;
import com.game.src.weapon.*;

public class PlayerStats implements Cloneable {

	private Player player;

	int statsXOffet = 613;

	private Color defaultColor = Color.WHITE;

	public PlayerStats() {
		this.player = GameMediator.getPlayer();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public void render(Graphics g) {

		// writer.setBackgroundColor(Terminal.Color.MAGENTA);
		// writer.setForegroundColor(Terminal.Color.BLACK);

		int statsYOffset = 16;
		int yJump = 32;

		Font fnt1 = new Font("arial", Font.PLAIN, 15);
		g.setFont(fnt1);

		g.setColor(defaultColor);

		g.drawString(player.getName() + " LVL:" + player.getLevel() + " EXP:" + player.getExp(), statsXOffet,
				statsYOffset);

		statsYOffset += yJump;

		g.drawString("Zycie: " + player.getHealth() + "/" + player.getMaxHealth(), statsXOffet, statsYOffset);

		// statsYOffset += yJump/2;
		// Draw player health bar

		g.setColor(Color.GREEN);
		g.fillRect(statsXOffet, statsYOffset + 1, (int) (player.getHealth() / (double) player.getMaxHealth() * 190),
				16);

		statsYOffset += yJump;

		g.setColor(defaultColor);

		g.drawString("Mana: " + player.getMana() + "/" + player.getMaxMana(), statsXOffet, statsYOffset);
		g.setColor(Color.BLUE);
		g.fillRect(statsXOffet, statsYOffset + 1, (int) (player.getMana() / (double) player.getMaxMana() * 190), 16);

		g.setColor(defaultColor);

		statsYOffset += yJump;

		g.drawString("Klucze: " + player.getKlucz(), statsXOffet, statsYOffset);

		drawEnemy(g);

		drawWeapon(g);

	}

	private void drawWeapon(Graphics g) {

		int statsYOffset = 200;

		IWeapon wp = player.getWeapon();
		//
		// FireWeapon fwp = new FireWeapon(wp);
		//
		// HolyWeapon hwp = new HolyWeapon(wp);
		//
		// HolyWeapon hfwp = new HolyWeapon(fwp);
		//
		// hfwp.attack();
		
//		String str = "This is String , split by StringTokenizer, created by mkyong";
//		StringTokenizer st = new StringTokenizer(str);

		g.setColor(defaultColor);

		g.drawImage(wp.getImage(), statsXOffet, statsYOffset, null);

		g.drawString(wp.getName().length()>20?wp.getName().substring(0, 20)+"...":wp.getName(), statsXOffet + 32, statsYOffset + 16 + 6);

	}

	private void drawEnemy(Graphics g) {

		int statsYOffset = 144;
		int yJump = 32;

		Enemy enemy = ButtonMediator.getCurrentField().getSelectedEnemy();

		if (enemy != null) {

			g.drawString(enemy.getName() + " " + enemy.getType() + " Lvl: " + enemy.getLevel(), statsXOffet,
					statsYOffset);

			statsYOffset += yJump / 2;

			g.drawString("HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth(), statsXOffet, statsYOffset);

			// statsYOffset += yJump/2;
			// Draw player health bar

			g.setColor(Color.GREEN);
			g.fillRect(statsXOffet, statsYOffset + 1, (int) ((enemy.getHealth() / (double) enemy.getMaxHealth()) * 190),
					16);

			g.setColor(defaultColor);

			g.setColor(Color.RED);
			g.drawRect(statsXOffet - 2, 120, 194, 70);

		}

	}

	// Dzielenie za d³ugich Stringów
	void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

}
