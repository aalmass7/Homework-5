package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Objects;
import java.util.Random;

public class BattleService {
    public static final int MAX_ROUNDS = 20;
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        Objects.requireNonNull(hero, "hero must not be null");
        Objects.requireNonNull(boss, "boss must not be null");
        Objects.requireNonNull(action, "action must not be null");

        if(hero.getHealth() <= 0){
            throw new IllegalArgumentException("hero health must be > 0");
        }
        if(boss.getHealth() <= 0){
            throw new IllegalArgumentException("boss health must be > 0");
        }
        if(action.getDamage() <= 0){
            throw new IllegalArgumentException("action health must be > 0");
        }

        AdventureResult result = new AdventureResult();
        result.setHeroStartHealth(hero.getHealth());
        result.setBossStartHealth(boss.getHealth());
        result.setActionName(action.getActionName());
        result.setActionDamage(action.getDamage());

        boolean heroStarts = random.nextBoolean();
        if (heroStarts) {
            result.addLine("Initiative: " + hero.getName() + " goes first.");
        } else {
            result.addLine("Initiative: " + boss.getName() + " goes first.");
        }
        int round = 0;
        while (hero.isAlive() && boss.isAlive() && round < MAX_ROUNDS) {
            round++;
            result.addLine("Round " + round + ":");

            if (heroStarts) {
                heroTurn(hero, boss, action, result);
                if (boss.isAlive()) {
                    bossTurn(hero, boss, result);
                }
            } else {
                bossTurn(hero, boss, result);
                if (hero.isAlive()) {
                    heroTurn(hero, boss, action, result);
                }
            }
            result.addLine("  End of round: " + hero.getName() + " HP=" + hero.getHealth() + ", "
                    + boss.getName() + " HP=" + boss.getHealth());
        }
        result.setRounds(round);

        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner(hero.getName());
        } else if (!hero.isAlive() && boss.isAlive()) {
            result.setWinner(boss.getName());
        } else if (!hero.isAlive() && !boss.isAlive()) {
            result.setWinner(boss.getName());
            result.addLine("Both combatants are down. Boss wins by rule.");
        } else {
            if (hero.getHealth() > boss.getHealth()) {
                result.setWinner(hero.getName());
                result.addLine("Max rounds reached. Winner by remaining HP: " + hero.getName());
            } else {
                result.setWinner(boss.getName());
                result.addLine("Max rounds reached. Winner by remaining HP: " + boss.getName());
            }
        }
        result.setHeroEndHealth(hero.getHealth());
        result.setBossEndHealth(boss.getHealth());
        result.addLine("Final: winner=" + result.getWinner() + ", rounds=" + result.getRounds());
        return result;
    }
    private void heroTurn(HeroProfile hero, BossEnemy boss, AttackAction action, AdventureResult result) {
        int damage = action.getDamage();
        boss.takeDamage(damage);
        result.addLine("  " + hero.getName() + " uses: " + action.getActionName());
        result.addLine("  Deals " + damage + " damage. " + boss.getName() + " HP=" + boss.getHealth());
    }
    private void bossTurn(HeroProfile hero, BossEnemy boss, AdventureResult result) {
        int base = boss.getAttackPower();
        int variance = random.nextInt(4); // 0..3
        boolean crit = random.nextDouble() < 0.15d;
        long raw = (long) base + variance;

        if (crit) {
            raw = raw * 2L;
        }

        int damage;
        if (raw > Integer.MAX_VALUE) {
            damage = Integer.MAX_VALUE;
        } else {
            damage = (int) raw;
        }

        hero.takeDamage(damage);

        String critText = "";
        if (crit) {
            critText = " (CRIT!)";
        }

        result.addLine("  " + boss.getName() + " attacks" + critText
                + " for " + damage + " damage. " + hero.getName() + " HP=" + hero.getHealth());
    }
}
