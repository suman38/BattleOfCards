package com.suman.game.characters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.suman.game.cards.CardElement;
import com.suman.game.cards.CardType;
import com.suman.game.cards.SpellCard;

public class Player extends GameCharacter {

	private List<SpellCard> cards;
	
	private int exp;

	public Player() {
		super("Suman",1);
		
		cards = new ArrayList<SpellCard>();
		cards.add(new SpellCard(0, "Attack", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Utility, CardElement.Normal, "Dmg: "+attack));
		cards.add(new SpellCard(1, "Defense", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Utility, CardElement.Normal, "Def: "+defence));
		cards.add(new SpellCard(2, "Heat Blast", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Spell, CardElement.Fire, "Dmg: 999")); // burst attack
		cards.add(new SpellCard(3, "Fire Fang", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Spell, CardElement.Fire, "Dmg: 999")); // burned dps
		cards.add(new SpellCard(4, "Flare", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Spell, CardElement.Fire, "Heal: 999")); // blind debuff
		cards.add(new SpellCard(5, "Fang Blast", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Combo, CardElement.Fire, "Dmg: 999")); // combo1
		cards.add(new SpellCard(6, "Blast Flare", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Combo, CardElement.Fire, "Dmg: 999")); // combo2
		cards.add(new SpellCard(7, "Ember Flare", new ImageIcon(getClass().getResource("/spellcards/water16x16.png")),
				CardType.Combo, CardElement.Fire, "Dmg: 999")); // combo3
		
	}

	public void addExp(int exp) {
		this.exp += exp;
		if (this.exp >= 100) {
			this.exp -= 100;
			this.level++;
		}
	}
	
	public List<SpellCard> getCards() {
		return cards;
	}

}
