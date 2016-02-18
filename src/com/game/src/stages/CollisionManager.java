package com.game.src.stages;

import java.awt.Color;
import java.util.Random;

import com.game.src.buttons.AlertButton;
import com.game.src.buttons.Button;
import com.game.src.buttons.controlls.ButtonController;
import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.entity.*;
import com.game.src.entity.enemies.Enemy;
import com.game.src.main.Game;

public class CollisionManager {
	
	private static CollisionManager _instance = new CollisionManager();

	private CollisionManager() {
        
    }
	
	public synchronized static CollisionManager getInstance() {
        return _instance;
        
    }
	
	public boolean checkCollision(AEntity entity, AEntity player) {

		if (entity == null) {
			return false;
		}

		if (entity instanceof Mountain) {
			return false;
		}

		if (entity instanceof Chest) {
			
			if (player instanceof Enemy) {
				return false;
			}
			
			if (((Chest) entity).open == false) {
				
				Game.logData.addElement();
				Game.logData.addToElement("Zdobywasz ");
				Game.logData.addToElement("klucz", Color.YELLOW);
				Game.logData.addToElement("!");
				
				((Player) player).klucz++;
				entity.tick();
			}
			return false;
		}

		if (entity instanceof Door) {
			if (((Door) entity).open == false) {

				if (((Player) player).klucz > 0) {
					((Player) player).klucz--;
					entity.tick();
				}
				return false;
			}
			return true;
		}
		
		if (entity instanceof Princess) {
			Runnable r1 = null;
			Runnable r2 = null;
			
			r1 = new Runnable() {
				@Override
				public void run() {
					
					ButtonMediator.nextStage();
					//ButtonController.deleteButton(alertButton);
				}
			};
			
			r2 = new Runnable() {
				@Override
				public void run() {
					
					//Game.state = Game.STATE.PAUSE;
//					ButtonController.spellsList(false);
				}
			};

			Button alertButton = new AlertButton(Game.WIDTH / 2 - 200, Game.HEIGHT / 2 - 200, 400, 300, "OK",
					"Dotar³eœ do ksiê¿niczki\n Ale to nie ta której szukasz!\n Kliknij OK aby przejœæ do nastêpnej planszy!", r1, r2);

			ButtonController.addButton(alertButton);
			
			return false;
		}

		if (entity instanceof Water) {
			
			if (player instanceof Enemy) {
				return false;
			}

			if (player.getX() == entity.getX() && player.getY() == entity.getY()) {

			} else {
				if (((Player) player).getMana() > 0) {

					Game.logData.addElement();
					Game.logData.addToElement("Zu¿ywasz 1 ");
					Game.logData.addToElement("many ", Color.BLUE);
					Game.logData.addToElement("aby chodziæ po wodzie!");
					((Player) player).lowerMana(1);
					return true;
				} else {

					Game.logData.addElement();
					Game.logData.addToElement("Nie masz ");
					Game.logData.addToElement("many ", Color.BLUE);
					Game.logData.addToElement("aby chodziæ po wodzie!");
					return false;
				}
			}
		}
       if (entity instanceof Fire) {
			
//    	   System.out.println(player.getClass().toString());
			if (player instanceof Enemy) {
				return false;
			}

			if (player.getX() == entity.getX() && player.getY() == entity.getY()) {

			} else {
				if (((Player) player).getHealth() > 0) {

					Game.logData.addElement();
					Game.logData.addToElement("Zu¿ywasz 1 ");
					Game.logData.addToElement("¿ycia ", Color.red);
					Game.logData.addToElement("aby przejsc przez ogien!");
					((Player) player).addDamage(1);
					return true;
				} else {
						
					((Player) player).death();
					Game.logData.addElement();
					Game.logData.addToElement("Nie masz ");
					Game.logData.addToElement("zycia  ", Color.BLUE);
					Game.logData.addToElement("aby chodziæ po ogniu!");
					return false;
				}
			}
		}

		if (entity instanceof Forest) {
			
			if (player instanceof Enemy) {
				return false;
			}

			if (player.getX() == entity.getX() && player.getY() == entity.getY()) {

			} else {
				Random rnd = new Random();

				if (rnd.nextInt(10) > 8) {

					Game.logData.addElement();
					Game.logData.addToElement("Spotykasz elfy, zwiêkszaj¹ ci ");
					Game.logData.addToElement("manê ", Color.BLUE);
					Game.logData.addToElement("o 1!");
					((Player) player).gainMaxMana(1);
					((Player) player).lowerMana(-1);
				}
			}
			return true;
		}

		if (entity instanceof Player) {
			return false;
		}

		if (entity instanceof ScreenChange) {

			if (player instanceof Enemy) {
				return false;
			}

			ButtonMediator.getCurrentStage().changeField(((ScreenChange) entity).getTo());

			if (player.getX() == 17)
				((Player) player).moveX(-16);
			else if (player.getX() == 1)
				((Player) player).moveX(16);

			if (player.getY() == 13)
				((Player) player).moveY(-12);
			else if (player.getY() == 1)
				((Player) player).moveY(12);

			return false;
		}

		if (player instanceof Enemy) {

			if (ButtonMediator.getPlayer().getX() == entity.getX() && entity.getY() == ButtonMediator.getPlayer().getY()) {
				return false;
			}
		}

		Entity enemy = ButtonMediator.getCurrentStage().getCurrentField().getEnemy(entity.getX(), entity.getY());

		if (enemy != null) {

			return false;
		}

		return true;
	}
}
