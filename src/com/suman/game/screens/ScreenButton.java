package com.suman.game.screens;

import java.awt.Rectangle;

import com.suman.game.utils.ClickInterface;

public class ScreenButton implements ClickInterface{
	public Rectangle rect;
	public boolean hovering;
	public String name;
	public final static int width=250, height=50;
	public final static int arcWidth = 50, arcHeight = 50;
	public ClickInterface clickInt;

	public ScreenButton(String name, int x, int y, ClickInterface clickInt) {
		this.name = name;
		this.rect = new Rectangle(x, y, width, height);
		this.hovering = false;
		this.clickInt = clickInt;
	}

	@Override
	public void click() {
		clickInt.click();
	}
}