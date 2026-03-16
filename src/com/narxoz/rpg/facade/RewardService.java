package com.narxoz.rpg.facade;

import java.util.Objects;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        Objects.requireNonNull(battleResult, "battleResult mustn't be null");

        if (battleResult.getRounds() <= 0) {
            return "No reward: adventure didn't start";
        }

        boolean heroWon = battleResult.getBossEndHealth() <= 0 && battleResult.getHeroEndHealth() > 0;
        if(heroWon){
            int baseGold = 100;
            int speedBonus = Math.max(0, BattleService.MAX_ROUNDS - battleResult.getRounds())*2;
            int gold = baseGold + speedBonus;
            return "Victory! Reward: gold=" + gold + ", trophy=\"Dungeon Shard\".";
        }
        return "Defeat: no reward";
    }
}
