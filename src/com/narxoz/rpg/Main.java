package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.*;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        HeroProfile heroPrototype = new HeroProfile("Ranger", 100);
        BossEnemy bossPrototype = new BossEnemy("Grim Overlord", 120, 15);

        AttackAction basic = new BasicAttack("Quick Strike", 10);
        AttackAction enhancedA = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(basic)
                )
        );
        AttackAction enhancedB = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(basic)
                )
        );

        System.out.println("--- Decorator Preview ---");
        printAction("Base", basic);
        printAction("Enhanced A", enhancedA);
        printAction("Enhanced B", enhancedB);


        System.out.println("\n--- Facade Preview ---");

        HeroProfile hero = new HeroProfile(heroPrototype.getName(), heroPrototype.getHealth());
        BossEnemy boss = new BossEnemy(bossPrototype.getName(), bossPrototype.getHealth(), bossPrototype.getAttackPower());

        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, enhancedA);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        System.out.println();

        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }
    private static void printAction(String label, AttackAction action) {
        System.out.println(label + ": " + action.getActionName());
        System.out.println("  Damage: " + action.getDamage());
        System.out.println("  Effects:");
        System.out.println(action.getEffectSummary());
        System.out.println();
    }
}
