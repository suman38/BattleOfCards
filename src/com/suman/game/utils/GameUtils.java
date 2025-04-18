package com.suman.game.utils;

import java.awt.Font;

public class GameUtils {

	public static Font mainFont;
	public static Font btnFont;
	public static Font cardFont;
	public static Font uiFont;

	public static void init() {
		mainFont = new Font("Cambria", Font.ITALIC, 22);
		btnFont = new Font("Cambria", Font.BOLD, 24);
		cardFont = new Font("Cambria", Font.PLAIN, 18);
		uiFont = new Font("Consolas",Font.PLAIN,20);
	}
}
