package com.narxoz.rpg.decorator;

import java.util.Objects;

public class BasicAttack implements AttackAction {
    private final String actionName;
    private final int baseDamage;

    public BasicAttack(String actionName, int baseDamage) {
        this.actionName = requireNonBlank(actionName, "actionName");
        if(baseDamage <= 0){
            throw new IllegalArgumentException("baseDamage must be > 0");
        }
        this.baseDamage = baseDamage;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public int getDamage() {
        return baseDamage;
    }

    @Override
    public String getEffectSummary() {
        return "Base attack";
    }
    private static String requireNonBlank(String value, String paramName){
        Objects.requireNonNull(value, paramName + " mustn't be null");
        String trimmed = value.trim();
        if(trimmed.isBlank()){
            throw new IllegalArgumentException(paramName + " mustn't be blank");
        }
        return trimmed;
    }
}
