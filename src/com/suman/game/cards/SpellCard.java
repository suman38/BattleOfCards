package com.suman.game.cards;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.suman.game.utils.GameUtils;

public class SpellCard {

	private int cardId;
	private String cardName;
	private ImageIcon cardIcon;
	private CardType cardType;
	private boolean toolTip;
	private String cardEffect;
	private CardElement cardEle;
	private boolean active;
	private boolean selected;
	private Rectangle bounds;
	private int cardDamage;

	private int cardCD;
	private int cdCounter;

	private final int cardWidth = 100, cardHeight = 140;
	private final int offsetX = 24, offsetY = 20;

	public SpellCard(int id, String name, ImageIcon icon, CardType type, CardElement ele, String effect) {
		this.cardId = id;
		this.cardName = name;
		this.cardIcon = icon;
		this.cardType = type;
		this.cardEffect = effect;
		this.cardEle = ele;
		this.cardDamage = 5;
		this.cardCD = 3;
		this.cdCounter = 0;

		bounds = new Rectangle(0, 0, cardWidth, cardHeight);
		if (type == CardType.Combo)
			active = false;
		else
			active = true;
	}

	public void updateCounter() {
		if (cdCounter > 0)
			cdCounter--;
		else
			cdCounter = 0;
		
//		System.out.println(cardName+" "+cdCounter);
	}

	public void draw(Graphics2D g2, int x, int y) {

		// updating the bounds
		bounds.x = x;
		bounds.y = y;

		// animating the selected card
		if (selected) {
			g2.setColor(Color.CYAN);
			g2.fillRoundRect(x, y, cardWidth, cardHeight, 20, 20);
		}

		// Coloring the active cards only
		if (active) {
			g2.setColor(Color.WHITE);
		} else {
			g2.setColor(new Color(60, 60, 60));
		}

		// Card Structure
		g2.setStroke(new BasicStroke(2));
		g2.drawRoundRect(x, y, cardWidth, cardHeight, 20, 20);

		g2.setFont(GameUtils.cardFont);
		FontMetrics metrics = g2.getFontMetrics(GameUtils.cardFont);

		// card effect text
		g2.drawString(cardEffect, x + (cardWidth / 2 - metrics.stringWidth(cardEffect) / 2), y + 120);

		// card type text
		g2.drawString(cardType.toString(), x + (cardWidth / 2 - metrics.stringWidth(cardType.toString()) / 2),
				y + (cardHeight + metrics.getHeight()));

		// Card Image icon
		g2.fillRoundRect(x + (cardWidth / 2 - 40), y + 10, 80, 80, 10, 10);
		g2.drawImage(cardIcon.getImage(), x + (cardWidth / 2 - 35), y + 15, 70, 70, null);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setActive(boolean state) {
		this.active = state;
	}

	public boolean isActive() {
		return active;
	}

	public void setSelected(boolean state) {
		this.selected = state;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setToolTip(boolean state) {
		this.toolTip = state;
	}

	public boolean isToolTip() {
		return toolTip;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public int getWidth() {
		return cardWidth;
	}

	public int getHeight() {
		return cardHeight;
	}

	public int getCardDamage() {
		return cardDamage;
	}

	public CardElement getCardElement() {
		return cardEle;
	}

	public String getCardName() {
		return cardName;
	}

	public ImageIcon getCardIcon() {
		return cardIcon;
	}

	public CardType getCardType() {
		return cardType;
	}

	public String getCardEffect() {
		return cardEffect;
	}

	public int getCardId() {
		return cardId;
	}

	public int getCardCd() {
		return cardCD;
	}

	public void setCdCounter(int cd) {
		cdCounter = cd;
	}
	
	public int getCdCounter()
	{
		return cdCounter;
	}
}
