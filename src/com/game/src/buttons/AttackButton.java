package com.game.src.buttons;

import java.awt.Color;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.entity.Player;
import com.game.src.entity.enemies.Enemy;
import com.game.src.main.Game;
import com.game.src.sound.MusicManager;
import com.game.src.stages.StageManager;

public class AttackButton extends Button {

	public AttackButton(int x, int y, int width, int heigth, String text) {
		super(x, y, width, heigth, text);
	}

	@Override
	public void action() {
		
		ButtonMediator.playerAttackEnemy();
//		Enemy enemy = StageManager.getCurrentStage().getCurrentField()
//				.getSelectedEnemy();
//
//		if (enemy != null) {
//
//			Player player = (Player) ButtonMediator.getPlayer();
//
//			if (player.getBounds().intersects(enemy.getBounds())) {
//
//				ButtonMediator.playerAttackEnemy(enemy);
//
//			}
//		}

	}

}
