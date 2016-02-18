package com.game.src.buttons.controlls;

import java.awt.Color;
import java.util.Random;

import com.game.src.buttons.AlertButton;
import com.game.src.buttons.Button;
import com.game.src.entity.AEntity;
import com.game.src.entity.ALivingEntity;
import com.game.src.entity.Player;
import com.game.src.entity.enemies.Enemy;
import com.game.src.entity.items.Potion;
import com.game.src.entity.items.weapon.FireWeapon;
import com.game.src.entity.items.weapon.HolyWeapon;
import com.game.src.main.Game;
import com.game.src.main.Game.STATE;
import com.game.src.sound.MusicManager;
import com.game.src.stages.CollisionManager;
import com.game.src.stages.Field;
import com.game.src.stages.Stage;
import com.game.src.stages.StageManager;
import com.game.src.stages.builder.IBoardBuilder;
import com.game.src.weapon.FireEnchance;
import com.game.src.weapon.HolyEnchance;
import com.game.src.weapon.Weapon;

public class ButtonMediator {

	private static Player player;

	private static Game game;

	private static StageManager stageManager;

	private static Random rnd = new Random();
	
	private static CollisionManager collisionManager = CollisionManager.getInstance();

	public ButtonMediator(Game game, Player player, StageManager stageManager) {
		ButtonMediator.player = player;
		ButtonMediator.game = game;
		ButtonMediator.stageManager = stageManager;

	}

//	public ButtonMediator(Player player2, StageManager stageManager2) {
//		ButtonMediator.player = player;
//		ButtonMediator.game = game;
//		ButtonMediator.stageManager = stageManager;
//	}

	public static Player getPlayer() {
		return player;
	}

	public static void playerAttackEnemy() {

		Enemy enemy = stageManager.getCurrentStage().getCurrentField().getSelectedEnemy();

		if (enemy != null) {

			Player player = getPlayer();

			if (player.getBounds().intersects(enemy.getBounds())) {

				int damageAmount = player.getWeapon().attack() + player.getPower();

				enemy.addDamage(damageAmount);
				// System.out.println(StageManager.getPlayer().getBounds()+
				// "Player");
				// System.out.println(enemy.getBounds()+ "Player");

				MusicManager.play("attack");

				Game.logData.addElement();
				Game.logData.addToElement("Atakujesz " + enemy.getName() + " za ");
				Game.logData.addToElement(damageAmount + "", Color.RED);
				Game.logData.addToElement(".");

				if (enemy.getHealth() <= 0) {

					// ButtonMediator.addItem(enemy);

					enemy.death();
					// player.addExp(enemy.getExp());
					//
					// StageManager.getCurrentStage().getCurrentField().deleteEnemy(enemy.getX(),
					// enemy.getY());
					//
				} else {
					Game.logData.addToElement(" Ten oddaje za ");
					Game.logData.addToElement(enemy.getPower() + "", Color.RED);
					Game.logData.addToElement("!");

					player.addDamage(enemy.getPower());

					// kontratak
				}
			}
		}
	}

	public static void enemyAttackPlayer(ALivingEntity enemyTemp) {
		if (rnd.nextInt(10) < 4) {

			Game.logData.addElement();
			Game.logData.addToElement(enemyTemp.getName() + " atakuje za  ");
			Game.logData.addToElement(enemyTemp.getPower() + "", Color.RED);
			Game.logData.addToElement(" kiedy obok przeszed³eœ!");

			player.addDamage(((Enemy) enemyTemp).getPower());

		}

	}

	public static void addItem(ALivingEntity aLivingEntity) {

		int x = aLivingEntity.getX();
		int y = aLivingEntity.getY();

		Random rnd = new Random();

		if (rnd.nextInt(10) < 5) {// tranndom

			Field tempField = stageManager.getCurrentStage().getCurrentField();

			if (rnd.nextInt(10) < 5) {

				tempField.addItem(x, y, new Potion(x, y));

			} else {
				if (rnd.nextInt(10) < 5) {

					tempField.addItem(x, y, new HolyWeapon(x, y));

				} else {

					tempField.addItem(x, y, new FireWeapon(x, y));

				}
			}

		}

	}

	public static void healPlayer(int i) {

		Game.logData.addElement();
		Game.logData.addToElement("Leczysz siê za ");
		Game.logData.addToElement(i + " ", Color.GREEN);
		Game.logData.addToElement("punktów ¿ycia!");
		player.addDamage(-i);

	}

	public static void startGame() {
		Game.state = Game.STATE.GAME;

		Game.started = true;

	}

	public static void toggleOptions() {
		if (Game.state == STATE.MENU)
			Game.state = STATE.OPTIONS;
		else if (Game.state == STATE.GAME)
			Game.state = STATE.OPTIONS;
		else
			Game.state = STATE.MENU;
	}

	public static void exitGame() {

		Runnable r1 = new Runnable() {
			@Override
			public void run() {

				Game.startOver();
			}
		};

		Runnable r2 = new Runnable() {
			@Override
			public void run() {

				// Game.state = Game.STATE.PAUSE;
				ButtonController.spellsList(false);
			}
		};

		Button alertButton = new AlertButton(Game.WIDTH / 2 - 200, Game.HEIGHT / 2 - 200, 400, 300, "OK",
				"Wy³¹czyæ grê?", r1, r2);

		ButtonController.addButton(alertButton);

	}

	public static void SetWorld(IBoardBuilder boardBuilder) {
		stageManager.SetWorld(boardBuilder);

	}

	public static void nextStage() {
		player.setWeapon(new Weapon());
		stageManager.nextStage();

	}

	public static void playerWeaponAdd(String string) {

		Game.logData.addElement();
		Game.logData.addToElement("Broñ zostaje wzmocniona ");

		switch (string) {
		case "fire":
			player.setWeapon(new FireEnchance(player.getWeapon()));
			Game.logData.addToElement("ogniem", Color.RED);

			break;

		case "holy":
			player.setWeapon(new HolyEnchance(player.getWeapon()));
			Game.logData.addToElement("œwiêtoœci¹", Color.LIGHT_GRAY.brighter());

			break;

		default:
			break;
		}

		Game.logData.addToElement("!");
	}

	public static void useSpell(String text) {

		// player.lowerMana(i);
		if (text == "Heal") {

			if (player.getMana() >= 2) {
				player.lowerMana(2);
				Game.logData.addElement();
				Game.logData.addToElement("Leczysz siê za ");
				Game.logData.addToElement(10 + " ", Color.GREEN);
				Game.logData.addToElement("punktów ¿ycia!");
				MusicManager.play("heal");
				player.addDamage(-10);
			} else {
				Game.logData.addElement();
				Game.logData.addToElement("Nie masz tyle many!");
			}
		} else {

			if (player.getMana() >= 2) {

				Enemy enemy = ButtonMediator.getCurrentField().getSelectedEnemy();

				if (enemy != null) {
					player.lowerMana(3);
					Game.logData.addElement();
					Game.logData.addToElement("Zadajesz dla " + enemy.getName() + " ");
					Game.logData.addToElement(10 + " ", Color.RED);
					Game.logData.addToElement("punktów obra¿eñ!");

					MusicManager.play("fireball");

					enemy.addDamage(10);

					if (enemy.getHealth() <= 0) {
						enemy.death();
					}
				} else {
					Game.logData.addElement();
					Game.logData.addToElement("Nie wybra³eœ celu!");
				}
			} else {
				Game.logData.addElement();
				Game.logData.addToElement("Nie masz tyle many!");
			}
		}

	}

	public static void addExp(int exp) {
		player.addExp(exp);

	}

	public static Field getCurrentField() {
		return stageManager.getCurrentStage().getCurrentField();
	}

	public static Stage getCurrentStage() {
		return stageManager.getCurrentStage();
	}

	public static CollisionManager getCollisionManager() {
		return collisionManager.getInstance();
	}

}
