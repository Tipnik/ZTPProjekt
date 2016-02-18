package com.game.src.sound;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MusicManager {

	static Clip attack = null;
	static Clip spellFireball = null;
	static Clip theme = null;
	static Clip hurt = null;
	static Clip heal = null;
	static private boolean  muteSounds = false;

	public static boolean isMuteSounds() {
		return muteSounds;
	}

	public static void setMuteSounds(boolean muteSounds) {
		MusicManager.muteSounds = muteSounds;
	}

	public void init() {

		String attackLoc = "./res/attack.wav";
		String spellFireballLoc = "./res/fireball.wav";
		String themeLoc = "./res/theme2.wav";
		String hurtLoc = "./res/hurt.wav";
		String healLoc = "./res/heal.wav";

		try {
			attack = AudioSystem.getClip();
			spellFireball = AudioSystem.getClip();
			theme = AudioSystem.getClip();
			hurt = AudioSystem.getClip();
			heal = AudioSystem.getClip();

		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// getAudioInputStream() also accepts a File or InputStream
		AudioInputStream ais = null;
		// AudioInputStream ais2 = null;
		try {
			ais = AudioSystem.getAudioInputStream(new File(attackLoc));
			attack.open(ais);

			ais = AudioSystem.getAudioInputStream(new File(spellFireballLoc));
			spellFireball.open(ais);

			ais = AudioSystem.getAudioInputStream(new File(themeLoc));
			theme.open(ais);

			ais = AudioSystem.getAudioInputStream(new File(hurtLoc));
			hurt.open(ais);

			ais = AudioSystem.getAudioInputStream(new File(healLoc));
			heal.open(ais);

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public void reset(){
		muteSounds = false;
	}

	public static void play(String sound) {

		if (sound == "theme") {
			play(theme, attack.LOOP_CONTINUOUSLY);
		}
		if (!muteSounds)
			switch (sound) {
			case "attack":

				play(attack, 0);
				break;

			case "fireball":
				play(spellFireball, 0);
				break;

			// case "theme":
			// play(theme, attack.LOOP_CONTINUOUSLY);
			// break;

			case "hurt":
				play(hurt, 0);
				break;

			case "heal":
				play(heal, 0);
				break;

			default:
				break;
			}

	}

	private static void play(Clip c, int i) {
		if (c.isRunning()) {
			c.stop();
		}
		c.setFramePosition(0);

		if (i == Clip.LOOP_CONTINUOUSLY) {
			c.loop(Clip.LOOP_CONTINUOUSLY);
			return;
		}
		if (i > 0)
			c.loop(i - 1);
		else
			c.start();
	}

	public static void stop(String sound) {

		switch (sound) {
		case "attack":

			if (attack.isRunning()) {
				attack.stop();
			}

			break;

		case "fireball":
			if (spellFireball.isRunning()) {
				spellFireball.stop();
			}
			break;

		case "theme":
			if (theme.isRunning()) {
				theme.stop();
			}
			break;

		case "hurt":
			if (hurt.isRunning()) {
				hurt.stop();
			}
			break;

		default:
			break;
		}

	}

	public static void toggle(String sound) {
		if (theme != null)
			if (theme.isRunning()) {
				theme.stop();
			} else {
				theme.start();
			}
	}

	public static void toggleStopSounds() {
		muteSounds = !muteSounds;
	}

}
