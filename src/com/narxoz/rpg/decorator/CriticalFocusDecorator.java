package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {
    private static final int MULTIPLIER = 2;

    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Critical Focus";
    }

    @Override
    public int getDamage() {
        int base = super.getDamage();
        long scaled = (long) base * MULTIPLIER;
        if (scaled > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) scaled;
    }

    @Override
    public String getEffectSummary() {
        return appendSummary(super.getEffectSummary(), "Critical Focus: x" + MULTIPLIER + " damage");
    }
}
