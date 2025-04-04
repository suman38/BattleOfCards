package com.suman.game.screens;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.suman.game.Game;

public abstract class Screen implements KeyListener, MouseListener, MouseMotionListener {

	protected Game game;

	private static Screen currentScreen;

	public Screen(Game game) {
		this.game = game;
	}

	public static void setScreen(Screen s) {
		currentScreen = s;
	}

	public static Screen getScreen() {
		return currentScreen;
	}

	public abstract void tick();

	public abstract void render(Graphics2D g2);
	
	public abstract void loadButtons();
}
