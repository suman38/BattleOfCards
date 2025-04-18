package com.suman.game.screens;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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

	private Rectangle battlePanel, spellPanel, messagePanel;

	private Player player;
	private GameCharacter enemy;

	private String msg;
	private int turns;

	private Point mousepos;

	private int selectedCard;

	private int barWidth = 250;

	public BattleScreen(Game game) {
		super(game);

		battlePanel = new Rectangle(5, 5, game.GameWidth - 10, 265);
		spellPanel = new Rectangle(5, game.GameHeight - 320, game.GameWidth - 10, 200);
		messagePanel = new Rectangle(5, game.GameHeight - 110, game.GameWidth - 10, 105);

		player = new Player();
		enemy = new GameCharacter("Alpha1", 1);

		msg = "This is a sample Message that will be shown here\n Also this works upto 3 lines.";

		mousepos = new Point();

		selectedCard = -1;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g2) {
		drawBattlePanel(g2);
		drawSpellPanel(g2);
		drawMessagePanel(g2);

		drawTooltip(g2);
		drawDragAnimation(g2);
		drawTurnCounter(g2);
	}

	private void drawBattlePanel(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		g2.setStroke(new BasicStroke(5));
		g2.draw(battlePanel);

		drawPlayer(g2);
		drawEnemy(g2);
	}

	private void drawPlayer(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		g2.fillRect(battlePanel.x + 200, battlePanel.y + 150, 50, 50);

		g2.setColor(Color.RED);
		g2.fillRoundRect(20, 40, (barWidth * player.getHealth()) / player.getMaxHealth(), 20, 10, 10);

		g2.setColor(Color.CYAN);
		g2.fillRoundRect(20, 95, (barWidth * player.getMana()) / player.getMaxMana(), 20, 10, 10);

		g2.setFont(GameUtils.uiFont);
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(2));

		g2.drawRoundRect(20, 40, barWidth, 20, 10, 10);
		g2.drawString("Health: " + player.getHealth() + "/" + player.getMaxHealth(), 40, 35);

		g2.drawRoundRect(20, 95, barWidth, 20, 10, 10);
		g2.drawString("Mana: " + player.getMana() + "/" + player.getMaxMana(), 40, 90);

	}

	private void drawEnemy(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.fillRect(battlePanel.x + 765, battlePanel.y + 150, 50, 50);

		g2.setColor(Color.RED);
		g2.fillRoundRect(game.GameWidth - 270, 40, (barWidth * enemy.getHealth()) / enemy.getMaxHealth(), 20, 10, 10);

		g2.setColor(Color.CYAN);
		g2.fillRoundRect(game.GameWidth - 270, 95, (barWidth * enemy.getMana()) / enemy.getMaxMana(), 20, 10, 10);

		g2.setFont(GameUtils.uiFont);
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(2));

		g2.drawRoundRect(game.GameWidth - 270, 40, barWidth, 20, 10, 10);
		g2.drawString("Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth(), game.GameWidth - 200, 35);

		g2.drawRoundRect(game.GameWidth - 270, 95, barWidth, 20, 10, 10);
		g2.drawString("Mana: " + enemy.getMana() + "/" + enemy.getMaxMana(), game.GameWidth - 200, 90);
	}

	private void drawSpellPanel(Graphics2D g2) {
		g2.setColor(new Color(30, 30, 30));
		g2.setStroke(new BasicStroke(5));
		g2.draw(spellPanel);

		for (int i = 0; i < player.getCards().size(); i++) {
			SpellCard c = player.getCards().get(i);
			c.draw(g2, spellPanel.x + (c.getOffsetX() * (i + 1)) + (c.getWidth() * i), (spellPanel.y + c.getOffsetY()));
		}
	}

	private void drawMessagePanel(Graphics2D g2) {
		g2.setColor(new Color(30, 30, 30));
		g2.setStroke(new BasicStroke(5));
		g2.draw(messagePanel);

		String[] lines = msg.split("\n");

		g2.setColor(Color.WHITE);
		g2.setFont(GameUtils.mainFont);

		int height = g2.getFontMetrics(GameUtils.mainFont).getHeight();
		for (int i = 0; i < lines.length; i++) {
			g2.drawString(lines[i].trim(), messagePanel.x + 20, messagePanel.y + (i * height) + 30);
		}
	}

	private void drawTooltip(Graphics2D g2) {
		int x = mousepos.x - 30;
		int y = mousepos.y-5;

		g2.setStroke(new BasicStroke(2));
		for (SpellCard c : player.getCards()) {
			if (c.isHovering()) {
				g2.setColor(new Color(20, 20, 20));
				g2.fillRoundRect(x - 20, y + 20, 160, 140, 20, 20);

				g2.setColor(Color.ORANGE);
				g2.drawRoundRect(x - 20, y + 20, 160, 140, 20, 20);

				g2.setFont(GameUtils.mainFont);
				g2.drawString(c.getCardName(), x - 10, y + 45);

				g2.setFont(GameUtils.cardFont);
				g2.drawString("Ele: " + c.getCardElement().toString(), x - 10, y + 70);
				g2.drawString("Type: " + c.getCardType().toString(), x - 10, y + 90);
				g2.drawString(c.getCardEffect(), x - 10, y + 110);

				g2.drawString("Mana: "+c.getSpellMana(), x-10, y+130);
				
				if (c.getCardType() == CardType.Spell)
					g2.drawString("Cooldown: " + c.getCoolDown() + " turns", x - 10, y + 150);
				else if (c.getCardType() == CardType.Combo)
					g2.drawString("Active for: " + (c.getCoolDown() + 1) + " turns", x - 10, y + 150);
				
			}
		}
	}

	private void drawDragAnimation(Graphics2D g2) {
		if (selectedCard != -1) {
			SpellCard c = player.getCards().get(selectedCard);
			g2.setColor(Color.WHITE);
			g2.fillRoundRect(mousepos.x - 25, mousepos.y - 25, 50, 50, 10, 10);
			g2.drawImage(c.getCardIcon().getImage(), mousepos.x - 22, mousepos.y - 22, 44, 44, null);
		}
	}

	private void drawTurnCounter(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Cambria", Font.BOLD, 24));
		g2.drawString("Turns: " + (turns < 10 ? "0" + turns : turns), game.GameWidth / 2 - 40, 40);
	}

	private void useSelectedCard() {
		SpellCard c = player.getCards().get(selectedCard);
		if (c.isActive()) {
			if (player.getMana() >= c.getSpellMana()) {

				// add damage here
				enemy.hurt(c.getCardDamage());
				player.hurt(1);

				player.setMana(player.getMana() - c.getSpellMana());

				msg = String.format("You used %s and did %d damage \nYou got hit for 10 damage", c.getCardName(),
						c.getCardDamage());

				if (c.getCardType() == CardType.Spell) {
					player.getCards().get(selectedCard).setCdTimer(c.getCoolDown());
					player.getCards().get(selectedCard).setActive(false);
				}

				updateTurns();
			} else {
				msg = "You dont have sufficient mana to cast the spell";
			}
		} else {
			msg = "Cannot use inactive cards!";
		}

	}

	private void activateCombo(int index1, int index2) {
		int activeIndex = -1;
		if (index1 != index2) {
			if ((index1 == 2 && index2 == 3) || (index1 == 3 && index2 == 2)) {
				activeIndex = 5;
			} else if ((index1 == 2 && index2 == 4) || (index1 == 4 && index2 == 2)) {
				activeIndex = 6;
			} else if ((index1 == 3 && index2 == 4) || (index1 == 4 && index2 == 3)) {
				activeIndex = 7;
			}

			if (!player.getCards().get(activeIndex).isActive()) {
				player.getCards().get(activeIndex).setActive(true);
				player.getCards().get(activeIndex).setCdTimer(player.getCards().get(activeIndex).getCoolDown());
				msg = String.format("You successfully activated %s card",
						player.getCards().get(activeIndex).getCardName());
			} else {
				msg = String.format("%s card is already active", player.getCards().get(activeIndex).getCardName());
			}
		} else {
			msg = "Invalid action. Try again!";
		}
	}

	private void updateTurns() {
		turns++;
		for (SpellCard c : player.getCards()) {
			c.updateCooldown();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < player.getCards().size(); i++) {
			SpellCard c = player.getCards().get(i);
			if (c.getBounds().contains(e.getX(), e.getY())) {
				c.setDragging(true);
				c.setHovering(false);
				selectedCard = i;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// dragging onto the battle panel
		if (battlePanel.getBounds().contains(e.getX(), e.getY()) && selectedCard != -1) {
			useSelectedCard();
		}

		for (int i = 0; i < player.getCards().size(); i++) {

			SpellCard c = player.getCards().get(i);

			// dragging a selected card onto another card
			if (c.getBounds().contains(e.getX(), e.getY()) && selectedCard != -1) {

				SpellCard sc = player.getCards().get(selectedCard);

				// if both card are spells and active
				if (c.getCardType() == CardType.Spell && sc.getCardType() == CardType.Spell) {
					if (c.isActive() && sc.isActive()) {
						// activate the combo
						activateCombo(selectedCard, i);
					} else {
						msg = "Invalid action. Both spells must be active";
					}
				} else {
					msg = "Invalid action. Cannot use combos and utility spells in that way.";
				}
			}
		}

		for (SpellCard c : player.getCards()) {
			c.setDragging(false);
			selectedCard = -1;
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
		for (SpellCard c : player.getCards())
			c.setHovering(false);

		mousepos.x = e.getX();
		mousepos.y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (SpellCard c : player.getCards()) {
			if (c.getBounds().contains(e.getX(), e.getY())) {
				c.setHovering(true);
				mousepos.x = e.getX();
				mousepos.y = e.getY();
			} else {
				c.setHovering(false);
			}
		}
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
	public void loadButtons() {
		// TODO Auto-generated method stub

	}
}