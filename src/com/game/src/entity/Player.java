package com.game.src.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.buttons.AlertButton;
import com.game.src.buttons.Button;
import com.game.src.buttons.controlls.ButtonController;
import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.entity.enemies.Enemy;
import com.game.src.main.Game;
import com.game.src.main.Game.STATE;
import com.game.src.sound.MusicManager;
import com.game.src.sprites.Textures;
import com.game.src.sprites.flyweight.ImageFactory;
import com.game.src.stages.CollisionManager;
import com.game.src.stages.Field;
import com.game.src.stages.StageManager;
import com.game.src.weapon.FireEnchance;
import com.game.src.weapon.HolyEnchance;
import com.game.src.weapon.IWeapon;
import com.game.src.weapon.Weapon;

public class Player extends ALivingEntity implements Cloneable{

	private int velX = 0;
	private int velY = 0;

//	private String name = "Player0";
//	private int level = 1;
//	protected int power = 2;
//	protected int defence = 10;
//	protected int maxHealth = 10;
//	protected int health = maxHealth;
//	public int maxMana = 10;
//	public int mana = maxMana;
//	protected int exp = 0;
	private int maxExp;
	private String[] spells = new String[4];
//	protected String type;
	public int klucz;
	private int skarb;
	
	private IWeapon weapon;
	
	private Random rnd = new Random();

//	private Textures tex;

	long timer;
	 @Override
	    public Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }
	

	public Player() {
		super(0, 0);
		
		init();
		
		this.timer = System.currentTimeMillis();
	}
	
	
	public void init(){
		name = "JeJu";
		
		level = 1;
		
		power = 1;
		
		defence = 2;
		
		maxHealth = 10;
		health = maxHealth;
		
		maxMana = 10;
		mana = maxMana;
		
		exp = 0;
		
		maxExp = 30;
		
		klucz = 0;
		
		skarb = 0;
		
		sprite = ImageFactory.getImage("player");
		
		
		//---------------------TEST BRONI --------------------//
		
		this.weapon = new Weapon();
		
//		weapon = new FireWeapon(this.weapon);
		
//		weapon = new HolyWeapon(weapon);
	}

	public void setWeapon(IWeapon weapon) {
		this.weapon = weapon;
	}


	public void tick() {

		if (getHealth() > 0)
			if (System.currentTimeMillis() - timer > 500) {

				{
					timer += 550;

					Field currentField = ButtonMediator.getCurrentField();

					if (x >= 0 && x <= 18) {

						if (y >= 0 && y <= 14 ) {

							if (collisionManager.checkCollision((AEntity) currentField.getObject(x + velX, y + velY),
									this)) {
								x += velX;
								y += velY;
							}

							if (x > 18)
								x--;
							if (x < 0)
								x++;

							if (y > 14)
								y--;
							if (y < 0)
								y++;
						}
					}

					currentField = ButtonMediator.getCurrentField();

					// Monster Action
					if (velY != 0 || velX != 0) {
						currentField.tick();
					}
				}
			}
	}

	public void render(Graphics g) {
		g.drawImage(sprite, (int) x * 32, (int) y * 32, null);
	}

	public void moveX(int x) {
		this.x += x;
	}

	public void moveY(int y) {
		this.y += y;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public void addDamage(int amount) {
		
		if (amount > 0){
			MusicManager.play("hurt");
		}
		
		this.health -= amount;
	}
	
	public void lowerMana(int i){
		this.mana-=i;
	}
	
	public void gainMaxMana(int i){
		this.maxMana+=1;
	}

	public int getHealth() {
		if (health <= 0) {
			health = 0;
			death();
		}

		if (health > maxHealth) {
			health = maxHealth;
		}

		return health;
	}

	public void death() {

		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				Game.startOver();
			}
		};

		Runnable r2 = new Runnable() {
			@Override
			public void run() {

//				ButtonController.spellsList(false);
				Game.state = Game.STATE.PAUSE;
			}
		};
		
		Button alertButton = new AlertButton(Game.WIDTH / 2 - 200, Game.HEIGHT / 2 - 200, 400, 400, "OK",
				"Umar³eœ\n       Leszczu", r1, r2);

		ButtonController.addButton(alertButton);
	}

	public String[] getSpells() {
		return spells;
	}

	public String getKlucz() {
		String key = "" + klucz;
		return key;
	}

	public int getSkarb() {
		return skarb;
	}

	public void addExp(int exp) {
		this.exp += exp;
		checkLevel();
	}

	private void checkLevel() {
		if (exp >= maxExp) {

			Game.logData.addElement();
			Game.logData.addToElement("Otrzymujesz ");
			Game.logData.addToElement(this.level + 1 + " ", Color.YELLOW);
			Game.logData.addToElement("poziom!");

			exp -= maxExp;
			maxExp += 10;
			this.level++;
			this.health++;
			this.maxHealth++;
			this.mana++;
			this.maxMana++;
			this.power++;
		}
	}
//
//	public void useSpell(String text) {
//		
//	}
	
	public IWeapon getWeapon() {
		return weapon;
	}
}
