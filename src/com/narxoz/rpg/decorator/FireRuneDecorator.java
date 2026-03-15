package com.narxoz.rpg.decorator;

public class FireRuneDecorator extends ActionDecorator {
    private static final double MULTIPLIER = 1.30d;

    public FireRuneDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Fire Rune";
    }

    @Override
    public int getDamage() {
        int base = super.getDamage();
        double scaled = base * MULTIPLIER;
        long ceil = (long) Math.ceil(scaled);
        if (ceil > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) ceil;
    }

    @Override
    public String getEffectSummary() {
        return appendSummary(super.getEffectSummary(), "Fire Rune: +30% damage");
    }
}
