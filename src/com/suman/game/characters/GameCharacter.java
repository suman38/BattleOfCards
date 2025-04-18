package com.suman.game.characters;

public class GameCharacter {

	protected String name;
	protected int health, maxHealth;
	protected int mana, maxMana;
	protected int attack, defence;
	protected int level;

	public GameCharacter(String name, int level) {
		this.name = name;
		this.level = level;
		attack = 2 * level;
		defence = 1 * level;
		maxHealth = 100 + (25 * level);
		health = maxHealth;
		maxMana = 100 + (15 * level);
		mana = maxMana;
	}

	public void hurt(int damage) {
		health -= damage;
		if (health <= 0)
			health = 0;
	}

	public void heal(int heal) {
		health += heal;
		if (health >= maxHealth)
			health = maxHealth;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}

	public int getLevel() {
		return level;
	}
}
