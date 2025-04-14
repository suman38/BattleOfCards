package com.suman.game.screens;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.suman.game.Game;
import com.suman.game.cards.CardType;
import com.suman.game.cards.SpellCard;
import com.suman.game.characters.GameCharacter;
import com.suman.game.characters.Player;
import com.suman.game.utils.GameUtils;

public class BattleScreen extends Screen {

	private Rectangle spellPanel, messagePanel, battlePanel;
	private String msg;

	private Player player;
	private GameCharacter enemy;

	private SpellCard selectedCard;

	private Point mousepos;

	private int turns;

	public BattleScreen(Game game) {
		super(game);

		battlePanel = new Rectangle(5, 5, game.GameWidth - 10, 265);
		spellPanel = new Rectangle(5, game.GameHeight - 320, game.GameWidth - 10, 200);
		messagePanel = new Rectangle(5, game.GameHeight - 110, game.GameWidth - 10, 100);

		msg = new String();

		turns = 0;

		player = new Player();
		enemy = new GameCharacter("Alpha1", 1);

		mousepos = new Point();
	}

	private void unlockComboSpell(SpellCard c1, SpellCard c2) {
		if ((c1.getCardId() == 2 || c1.getCardId() == 3) && (c2.getCardId() == 3 || c2.getCardId() == 2)) {
			player.getCards().get(5).setActive(true);
		}

		if ((c1.getCardId() == 2 || c1.getCardId() == 4) && (c2.getCardId() == 4 || c2.getCardId() == 2)) {
			player.getCards().get(6).setActive(true);
		}

		if ((c1.getCardId() == 3 || c1.getCardId() == 4) && (c2.getCardId() == 4 || c2.getCardId() == 3)) {
			player.getCards().get(7).setActive(true);
		}
	}

	private void updateTurnCounter() {
		turns++;
		for (SpellCard c : player.getCards()) {
			c.updateCounter();
		}

		for (SpellCard c : player.getCards()) {
			if (c.getCdCounter() == 0) {
				if (c.getCardType() == CardType.Spell)
					c.setActive(true);
				else if (c.getCardType() == CardType.Combo)
					c.setActive(false);
			}
		}
	}

	private void useCard() {
		enemy.hurt(selectedCard.getCardDamage());
		msg = String.format("You hit %s for %d damage", enemy.getName(), selectedCard.getCardDamage());
		selectedCard.setCdCounter(selectedCard.getCardCd());
		if(selectedCard.getCardType() == CardType.Spell)
			selectedCard.setActive(false);

		updateTurnCounter();
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

		for (SpellCard c : player.getCards()) {
			if (c.getBounds().contains(e.getX(), e.getY())) {
				c.setSelected(true);
				c.setToolTip(false);
				selectedCard = c;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (battlePanel.contains(e.getX(), e.getY())) {
			if (selectedCard.isActive())
				useCard();
			else
				msg = "Cannot use inactive cards";
		}

		for (SpellCard c : player.getCards()) {
			if (c.getBounds().contains(e.getX(), e.getY())) {
				if (selectedCard.getCardType() == CardType.Spell && c.getCardType() == CardType.Spell
						&& selectedCard != c) {

					unlockComboSpell(selectedCard, c);

					msg = String.format("You combined %s and %s", selectedCard.getCardName(), c.getCardName());
				}
			}

			c.setSelected(false);
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
		for (SpellCard c : player.getCards()) {
			if (c.isSelected()) {
				mousepos.x = e.getX();
				mousepos.y = e.getY();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (SpellCard card : player.getCards()) {
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

		// Utility spells dont have any cd or timers, they can be used freely
		// Common spells have their own cd
		// Combo spells dont have cd, but they have activation timer
		// this means they stay active for few turns.

		// Update all the card timers here
		// -------------------

//		if (cards.get(5).isActive()) {
//			timer++;
//			if (timer >= 100) {
//				cards.get(5).setActive(false);
//				timer = 0;
//			}
//		}
	}

	@Override
	public void render(Graphics2D g2) {
		drawBattlePanel(g2);
		drawSpellPanel(g2);
		drawMessagePanel(g2);
		drawToolTip(g2);
		drawDragAnimation(g2);

		g2.setColor(Color.WHITE);
		g2.setFont(GameUtils.cardFont);
		g2.drawString("Turns: " + turns, 10, 40);
	}

	private void drawBattlePanel(Graphics2D g2) {
		g2.setColor(new Color(30, 30, 30));
		g2.setStroke(new BasicStroke(5));
		g2.draw(battlePanel);

		g2.setColor(Color.GREEN);
		g2.fillRect(battlePanel.x + 200, battlePanel.y + 150, 50, 50);

		g2.setColor(Color.YELLOW);
		g2.fillRect(battlePanel.x + 800, battlePanel.y + 150, 50, 50);

		g2.setFont(GameUtils.mainFont);
		g2.setColor(Color.WHITE);
		g2.drawString("health: " + player.getHealth() + "/" + player.getMaxHealth(), battlePanel.x + 160,
				battlePanel.y + 140);
		g2.drawString("health: " + enemy.getHealth() + "/" + enemy.getMaxHealth(), battlePanel.x + 760,
				battlePanel.y + 140);

//		g2.setColor(Color.RED);
//		g2.fillRoundRect(battlePanel.x + 150, battlePanel.y + 120, 150, 10, 10, 10);
//		g2.fillRoundRect(battlePanel.x + 750, battlePanel.y + 120, 150, 10, 10, 10);
	}

	private void drawDragAnimation(Graphics2D g2) {
		for (SpellCard sc : player.getCards()) {
			if (sc.isSelected()) {
				g2.setColor(Color.WHITE);
				g2.fillRoundRect(mousepos.x - 25, mousepos.y - 25, 60, 60, 10, 10);
				g2.drawImage(sc.getCardIcon().getImage(), mousepos.x - 20, mousepos.y - 20, 50, 50, null);
			}
		}
	}

	private void drawMessagePanel(Graphics2D g2) {
		g2.setColor(new Color(30, 30, 30));
		g2.setStroke(new BasicStroke(5));
		g2.draw(messagePanel);

		g2.setColor(Color.WHITE);
		g2.setFont(GameUtils.mainFont);
		int height = g2.getFontMetrics(GameUtils.mainFont).getAscent();
		g2.drawString(msg, messagePanel.x + 10, messagePanel.y + height + 10);
	}

	private void drawToolTip(Graphics2D g2) {

		int x = mousepos.x - 30;
		int y = mousepos.y;

		for (SpellCard c : player.getCards()) {
			if (c.isToolTip()) {
				g2.setColor(Color.BLACK);
				g2.fillRoundRect(x - 20, y + 20, 150, 100, 20, 20);

				g2.setColor(Color.WHITE);
				g2.drawRoundRect(x - 20, y + 20, 150, 100, 20, 20);

				g2.setFont(GameUtils.cardFont);
				g2.drawString(c.getCardName(), x - 10, y + 45);
				g2.drawString("Ele: " + c.getCardElement().toString(), x - 10, y + 65);
				g2.drawString("Type: " + c.getCardType().toString(), x - 10, y + 85);
				g2.drawString(c.getCardEffect(), x - 10, y + 105);
			}
		}
	}

	private void drawSpellPanel(Graphics2D g2) {
		g2.setColor(new Color(30, 30, 30));
		g2.setStroke(new BasicStroke(5));
		g2.draw(spellPanel);

		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(2));
		for (int i = 0; i < player.getCards().size(); i++) {
			SpellCard c = player.getCards().get(i);
			c.draw(g2, spellPanel.x + (c.getOffsetX() * (i + 1)) + (c.getWidth() * i), spellPanel.y + c.getOffsetY());
		}
	}

	@Override
	public void loadButtons() {
		// TODO Auto-generated method stub
	}
}
