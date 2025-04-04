package com.suman.game.screens;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.suman.game.Game;
import com.suman.game.utils.ClickInterface;

public class PauseScreen extends Screen {

	public PauseScreen(Game game) {
		super(game);
	}
	
	@Override
	public void loadButtons()
	{
		game.getUIManager().clearBtnList();
		
		game.getUIManager().addButton("Resume Game", game.GameWidth / 2 - ScreenButton.width / 2,
				game.GameHeight / 2 - 25, new ClickInterface() {
					@Override
					public void click() {
						game.changeScreen(ScreenType.Game);
					}
				});
		
		game.getUIManager().addButton("Main Menu", game.GameWidth / 2 - ScreenButton.width / 2,
				game.GameHeight / 2 + 50, new ClickInterface() {
					@Override
					public void click() {
						game.changeScreen(ScreenType.Home);
					}
				});
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		game.getUIManager().mouseClicked(e);
//		for (ScreenButton sb : btns) {
//			if (sb.rect.contains(new Point(e.getX(), e.getY()))) {
//				if (sb.name.equals("Resume Game"))
//					game.changeScreen(ScreenType.Game);
//				else if (sb.name.equals("Main Menu"))
//					game.changeScreen(ScreenType.Home);
//			}
//		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		game.getUIManager().mouseMoved(e);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g2) {
		game.getUIManager().render(g2);
	}
}
