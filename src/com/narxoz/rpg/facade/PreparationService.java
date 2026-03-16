package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Objects;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        Objects.requireNonNull(hero, "hero must not be null");
        Objects.requireNonNull(boss, "boss must not be null");
        Objects.requireNonNull(action, "action must not be null");

        if (hero.getHealth() <= 0) {
            throw new IllegalArgumentException("hero health must be > 0");
        }
        if (boss.getHealth() <= 0) {
            throw new IllegalArgumentException("boss health must be > 0");
        }
        if (boss.getAttackPower() <= 0) {
            throw new IllegalArgumentException("boss attackPower must be > 0");
        }
        if (action.getDamage() <= 0) {
            throw new IllegalArgumentException("action damage must be > 0");
        }
        return "Preparation complete: " + hero.getName() + " (HP=" + hero.getHealth() + ") vs "
                + boss.getName() + " (HP=" + boss.getHealth() + ", ATK=" + boss.getAttackPower() + ")\n"
                + "Hero action: " + action.getActionName() + " (DMG=" + action.getDamage() + ")\n"
                + action.getEffectSummary();
    }
}
