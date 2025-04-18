package com.suman.game;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.suman.game.screens.ScreenButton;
import com.suman.game.utils.ClickInterface;
import com.suman.game.utils.GameUtils;

public class UIManager {
	private List<ScreenButton> btns;

	public UIManager() {
		btns = new ArrayList<ScreenButton>();
	}

	public void tick() {
	}

	public void render(Graphics2D g2) {
		g2.setFont(GameUtils.btnFont);
		for (ScreenButton sb : btns) {
			FontMetrics metrics = g2.getFontMetrics(GameUtils.btnFont);			
			if (!sb.hovering) {
				g2.setColor(Color.WHITE);
				g2.drawRoundRect(sb.rect.x, sb.rect.y, ScreenButton.width, ScreenButton.height, ScreenButton.arcWidth,
						ScreenButton.arcHeight);
				g2.drawString(sb.name, sb.rect.x + ((ScreenButton.width / 2 - metrics.stringWidth(sb.name) / 2)),
						sb.rect.y + ((ScreenButton.height - metrics.getHeight())/2)+metrics.getAscent());
			} else {
				g2.setColor(Color.WHITE);
				g2.fillRoundRect(sb.rect.x, sb.rect.y, ScreenButton.width, ScreenButton.height, ScreenButton.arcWidth,
						ScreenButton.arcHeight);
				g2.setColor(Color.BLACK);
				g2.drawString(sb.name, sb.rect.x + ((ScreenButton.width / 2 - metrics.stringWidth(sb.name) / 2)),
						sb.rect.y + ((ScreenButton.height - metrics.getHeight())/2)+metrics.getAscent());
			}
		}
	}

	public void addButton(String name, int x, int y, ClickInterface clickInt) {
		btns.add(new ScreenButton(name, x, y, clickInt));
	}

	public void clearBtnList() {
		btns.removeAll(btns);
	}

	public void mouseMoved(MouseEvent e) {
		for (ScreenButton sb : btns) {
			if (sb.rect.contains(new Point(e.getX(), e.getY()))) {
				sb.hovering = true;
			} else {
				sb.hovering = false;
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		for (ScreenButton sb : btns) {
			if (sb.rect.contains(new Point(e.getX(), e.getY()))) {
				sb.click();
			}
		}
	}
}
