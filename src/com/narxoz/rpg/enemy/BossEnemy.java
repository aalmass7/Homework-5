package com.narxoz.rpg.enemy;

import java.util.Objects;

public class BossEnemy {
    private final String name;
    private int health;
    private final int attackPower;

    public BossEnemy(String name, int health, int attackPower) {
        this.name = requireNonBlank(name, "name");
        if (health <= 0) {
            throw new IllegalArgumentException("health must be > 0");
        }
        if (attackPower <= 0) {
            throw new IllegalArgumentException("attackPower must be > 0");
        }
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("damage amount must be >= 0");
        }
        health = Math.max(0, health - amount);
    }

    public boolean isAlive() {
        return health > 0;
    }

    private static String requireNonBlank(String value, String paramName) {
        Objects.requireNonNull(value, paramName + " must not be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(paramName + " must not be blank");
        }
        return trimmed;
    }
}
