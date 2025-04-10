package com.suman.game.screens;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.suman.game.Game;
import com.suman.game.cards.CardElement;
import com.suman.game.cards.CardType;
import com.suman.game.cards.SpellCard;
import com.suman.game.utils.GameUtils;

public class BattleScreen extends Screen {

	private List<SpellCard> cards;

	private Rectangle spellPanel, messagePanel;
	private String msg;

	private SpellCard selectedCard;// , droppedon;

	private Point mousepos;

	public BattleScreen(Game game) {
		super(game);

//		spellPanel = new Rectangle(5, game.GameHeight - 210, game.GameWidth - 10, 200);
//		messagePanel = new Rectangle(5,game.GameHeight-320,game.GameWidth-10,100);

		spellPanel = new Rectangle(5, game.GameHeight - 320, game.GameWidth - 10, 200);
		messagePanel = new Rectangle(5, game.GameHeight - 110, game.GameWidth - 10, 100);

		msg = "This is a sample text that will be shown inside the message panel.";

		mousepos = new Point();

		cards = new ArrayList<SpellCard>();
		cards.add(new SpellCard("Attack", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Utility, CardElement.Normal, "Dmg: 999"));
		cards.add(new SpellCard("Defense", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Utility, CardElement.Normal, "Def: 999"));
		cards.add(new SpellCard("Heat Blast", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Spell, CardElement.Fire, "Dmg: 999")); // burst attack
		cards.add(new SpellCard("Fire Fang", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Spell, CardElement.Fire, "Dmg: 999")); // burned dps
		cards.add(new SpellCard("Flare", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Spell, CardElement.Fire, "Heal: 999")); // blind debuff
		cards.add(new SpellCard("Fang Blast", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Combo, CardElement.Fire, "Dmg: 999")); // combo1
		cards.add(new SpellCard("Blast Flare", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Combo, CardElement.Fire, "Dmg: 999")); // combo2
		cards.add(new SpellCard("Ember Flare", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Combo, CardElement.Fire, "Dmg: 999")); // combo3
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			game.changeScreen(ScreenType.Pause);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		for (SpellCard c : cards) {
			if (c.getBounds().contains(e.getX(), e.getY())) {
				c.setSelected(true);
				c.setToolTip(false);
				selectedCard = c;
//				System.out.println("pressed: "+c.getCardName()+" "+c.isSelected());
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println(e.getX()+" "+ e.getY());
		for (SpellCard c : cards) {
			if (c.getBounds().contains(e.getX(), e.getY())) {
				if (selectedCard.getCardType() == CardType.Spell && c.getCardType() == CardType.Spell
						&& selectedCard != c) {
//					System.out.println(selectedCard.getCardName() + " Dropped on " + c.getCardName());
//					droppedon = c;

					msg = String.format("You combined %s and %s", selectedCard.getCardName(), c.getCardName());
				}
			}
			c.setSelected(false);
//			System.out.println("relesed: "+c.getCardName()+" "+c.isSelected());
		}

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
		for (SpellCard c : cards) {
			if (c.isSelected()) {
				mousepos.x = e.getX();
				mousepos.y = e.getY();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (SpellCard card : cards) {
			if (card.getBounds().contains(e.getX(), e.getY())) {
				card.setToolTip(true);
				mousepos.x = e.getX();
				mousepos.y = e.getY();
			} else
				card.setToolTip(false);
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics2D g2) {

		g2.setColor(new Color(30, 30, 30));
		g2.setStroke(new BasicStroke(5));

//		g2.drawRect(5, game.GameHeight - 210, game.GameWidth - 10, 200);
		g2.draw(messagePanel);
		g2.draw(spellPanel);

		drawMessagePanel(g2);
		drawSpellPanel(g2);
		drawToolTip(g2);
		drawDragAnimation(g2);
	}

	private void drawDragAnimation(Graphics2D g2) {
		for (SpellCard sc : cards) {
			if (sc.isSelected()) {
				g2.setColor(Color.CYAN);
				g2.fillRoundRect(mousepos.x - 25, mousepos.y - 25, 60, 60, 10, 10);
				g2.drawImage(sc.getCardIcon().getImage(), mousepos.x - 20, mousepos.y - 20, 50, 50, null);
			}
		}
	}

	private void drawMessagePanel(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(GameUtils.mainFont);

		int height = g2.getFontMetrics(GameUtils.mainFont).getAscent();
		g2.drawString(msg, messagePanel.x + 10, messagePanel.y + height + 10);
	}

	private void drawToolTip(Graphics2D g2) {

		int x = mousepos.x - 30;
		int y = mousepos.y;

		for (SpellCard c : cards) {
			if (c.isToolTip()) {
				g2.setColor(Color.BLACK);
				g2.fillRoundRect(x - 20, y + 20, 150, 100, 20, 20);

				g2.setColor(Color.WHITE);
				g2.drawRoundRect(x - 20, y + 20, 150, 100, 20, 20);

				g2.drawString(c.getCardName(), x - 10, y + 45);
				g2.drawString("Ele: " + c.getCardElement().toString(), x - 10, y + 65);
				g2.drawString("Type: " + c.getCardType().toString(), x - 10, y + 85);
				g2.drawString(c.getCardEffect(), x - 10, y + 105);

//				msg = String.format("%s card,   Type: %s,   %s,   Element: %s", c.getCardName(),
//						c.getCardType().toString(), c.getCardEffect(), c.getCardElement());
			}
		}
	}

	private void drawSpellPanel(Graphics2D g2) {

		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(2));

		for (int i = 0; i < cards.size(); i++) {
//			System.out.println(spellPanel.x+cards.get(i).getWidth()*i);

			SpellCard c = cards.get(i);
//			System.out.println(i+" "+(spellPanel.x+(c.getWidth()*i)+(c.getOffsetX()*(i+1))));

			c.draw(g2, spellPanel.x + (c.getOffsetX() * (i + 1)) + (c.getWidth() * i), spellPanel.y + c.getOffsetY());

		}

//		for(int i =0;i<cards.size();i++)
//		{
//			//instead of doing this way it is better to draw the card itself
//			//i mean make a class called Card and give an outline like below
//			//add image, text, tooltip text and draw the card directly
//			g2.drawRoundRect(spellPanel.x+(125*i)+10, spellPanel.y+20, 115, 160, 20, 20);
//		}

//		g2.drawRoundRect(spellPanel.x+(100*0)+20, spellPanel.y+20, 100, 160, 20, 20);
//		g2.drawRoundRect(spellPanel.x+(100*1)+20, spellPanel.y+20, 100, 160, 20, 20);
//		g2.drawRoundRect(spellPanel.x+260, spellPanel.y+20, 100, 160, 20, 20);
//		g2.drawRoundRect(spellPanel.x+380, spellPanel.y+20, 100, 160, 20, 20);
//		g2.drawRoundRect(spellPanel.x+0, spellPanel.y+20, 100, 160, 20, 20);
//		g2.drawRoundRect(spellPanel.x+670, spellPanel.y+20, 100, 160, 20, 20);
//		g2.drawRoundRect(spellPanel.x+800, spellPanel.y+20, 100, 160, 20, 20);
//		g2.drawRoundRect(spellPanel.x+930, spellPanel.y+20, 100, 160, 20, 20);

	}

	@Override
	public void loadButtons() {
		// TODO Auto-generated method stub

	}

}
