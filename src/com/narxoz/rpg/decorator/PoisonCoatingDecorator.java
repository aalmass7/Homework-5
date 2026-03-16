package com.narxoz.rpg.decorator;

public class PoisonCoatingDecorator extends ActionDecorator {
    private static final int BONUS_DAMAGE = 3;

    public PoisonCoatingDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Poison Coating";
    }

    @Override
    public int getDamage() {
        int base = super.getDamage();
        long boosted = (long) base  + BONUS_DAMAGE;
        if(boosted > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int) boosted;
    }

    @Override
    public String getEffectSummary() {
        return appendSummary(super.getEffectSummary(), "Poison Coating: +" + BONUS_DAMAGE + " damage");
    }
}
