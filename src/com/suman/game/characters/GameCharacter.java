package com.suman.game.characters;

public class GameCharacter {

	private String name;
	private int health, maxHealth;
	private int mana, maxMana;
	private int attack, defence;
	private int level;
	private int exp;

	public GameCharacter(String name,int level) {
		this.name = name;
		maxHealth = 100;
		health = maxHealth;
		maxMana = 100;
		mana = maxMana;
		this.level = level;
		attack = 2 * this.level;
		defence = 1 * this.level;
	}

	public void checkLevelUp() {
		if (this.exp >= 100) {
			this.exp -= 100;
			level++;
		}
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

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
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
