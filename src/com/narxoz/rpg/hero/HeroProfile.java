package com.narxoz.rpg.hero;

import java.util.Objects;

public class HeroProfile {
    private final String name;
    private int health;

    public HeroProfile(String name, int health) {
        this.name = requireNonBlank(name, "name");
        if(health <= 0){
            throw new IllegalArgumentException("health mut be > 0");
        }
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
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

    private static  String requireNonBlank(String value, String paramName){
        Objects.requireNonNull(value, paramName + " mustn't be null");
        String trimmed = value.trim();
        if(trimmed.isEmpty()){
            throw new IllegalArgumentException(paramName + " mustn't be null");
        }
        return trimmed;
    }
}
