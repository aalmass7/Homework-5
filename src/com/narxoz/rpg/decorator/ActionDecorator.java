package com.narxoz.rpg.decorator;

import java.util.Objects;

public abstract class ActionDecorator implements AttackAction {
    private final AttackAction wrappedAction;

    protected ActionDecorator(AttackAction wrappedAction) {
        this.wrappedAction = wrappedAction;
    }

    protected AttackAction getWrappedAction() {
        return wrappedAction;
    }

    @Override
    public String getActionName() {
        return wrappedAction.getActionName();
    }

    @Override
    public int getDamage() {
        return wrappedAction.getDamage();
    }

    @Override
    public String getEffectSummary() {
        return wrappedAction.getEffectSummary();
    }
    protected static String appendSummary(String base, String addition){
        Objects.requireNonNull(addition, "addition mustn't be null");
        if(base == null || base.isBlank()){
            return addition;
        }
        return base + "\n - " + addition;
    }
}
