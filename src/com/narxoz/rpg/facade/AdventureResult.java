package com.narxoz.rpg.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AdventureResult {
    private String winner;
    private int rounds;
    private String reward;
    private int heroStartHealth;
    private int heroEndHealth;
    private int bossStartHealth;
    private int bossEndHealth;
    private String actionName;
    private int actionDamage;

    private final List<String> log = new ArrayList<>();

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getHeroStartHealth() {
        return heroStartHealth;
    }

    public void setHeroStartHealth(int heroStartHealth) {
        this.heroStartHealth = heroStartHealth;
    }

    public int getHeroEndHealth() {
        return heroEndHealth;
    }

    public void setHeroEndHealth(int heroEndHealth) {
        this.heroEndHealth = heroEndHealth;
    }

    public int getBossStartHealth() {
        return bossStartHealth;
    }

    public void setBossStartHealth(int bossStartHealth) {
        this.bossStartHealth = bossStartHealth;
    }

    public int getBossEndHealth() {
        return bossEndHealth;
    }

    public void setBossEndHealth(int bossEndHealth) {
        this.bossEndHealth = bossEndHealth;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getActionDamage() {
        return actionDamage;
    }

    public void setActionDamage(int actionDamage) {
        this.actionDamage = actionDamage;
    }

    public void addLine(String line) {
        log.add(Objects.requireNonNull(line, "line mustn't be null"));
    }

    public void prependLine(String line) {
        log.add(0, Objects.requireNonNull(line, "line must not be null"));
    }

    public List<String> getLog() {
        return Collections.unmodifiableList(log);
    }
}
