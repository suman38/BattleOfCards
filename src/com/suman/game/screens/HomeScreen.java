package com.suman.game.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.suman.game.Game;
import com.suman.game.utils.ClickInterface;

public class HomeScreen extends Screen {

	public HomeScreen(Game game) {
		super(game);
	}

	@Override
	public void loadButtons() {
		game.getUIManager().clearBtnList();
		
		game.getUIManager().addButton("Start Game", game.GameWidth / 2 - ScreenButton.width / 2,
				game.GameHeight / 2 - 25, new ClickInterface() {
					@Override
					public void click() {
						game.changeScreen(ScreenType.Battle);
					}
				});
		
		game.getUIManager().addButton("Exit Game", game.GameWidth / 2 - ScreenButton.width / 2,
				game.GameHeight / 2 + 50, new ClickInterface() {
					@Override
					public void click() {
						game.stopGame();
					}
				});
	}

	@Override
	public void tick() {
		game.getUIManager().tick();
	}

	@Override
	public void render(Graphics2D g2) {

		g2.setFont(new Font("Cambria", Font.ITALIC, 60));
		g2.setColor(Color.WHITE);
		g2.drawString("Battle of Cards", game.GameWidth / 2 - 183, 150);

		game.getUIManager().render(g2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		game.getUIManager().mouseClicked(e);
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
	}

	@Override
	public void mouseExited(MouseEvent e) {
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
}
