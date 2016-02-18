package com.game.src.memento;

import com.game.src.entity.Player;
import com.game.src.libs.Controller;
import com.game.src.screens.HelpScreen;
import com.game.src.screens.Menu;
import com.game.src.screens.OptionsScreen;
import com.game.src.screens.gui.GuiBackground;
import com.game.src.screens.gui.PlayerStats;
import com.game.src.sprites.Textures;
import com.game.src.stages.Field;
import com.game.src.stages.StageManager;

public class Memento {

	private WrapperMemento fields;

	private Memento() {
	}

	public Memento(WrapperMemento fields) {

		this.fields = fields;

	}

	public WrapperMemento getState() {
		return this.fields;
	}

}
