package com.suman.game.utils;

import java.awt.Font;

public class GameUtils {

	public static Font mainFont;
	public static Font btnFont;

	public static void init() {
		mainFont = new Font("Cambria", Font.ITALIC, 24);
		btnFont = new Font("Cambria", Font.BOLD, 24);
	}
}
