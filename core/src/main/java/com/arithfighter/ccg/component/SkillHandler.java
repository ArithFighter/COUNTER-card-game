package com.arithfighter.ccg.component;

public class SkillHandler {
    private enum SkillState {NEUTRAL, READY}
    private SkillState skillState = SkillState.NEUTRAL;

    public void setReady(){
        skillState = SkillState.READY;
    }

    public void init(){
        skillState = SkillState.NEUTRAL;
    }

    public boolean isSkillReady(){
        return skillState == SkillState.READY;
    }

    public boolean isSkillNeutral(){
        return skillState == SkillState.NEUTRAL;
    }
}
