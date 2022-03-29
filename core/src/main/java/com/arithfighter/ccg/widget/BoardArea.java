package com.arithfighter.ccg.widget;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoardArea {
    private final SpriteWidget widget;

    public BoardArea(Texture texture){
        widget = new SpriteWidget(texture, 3);

        widget.getSprite().setColor(Color.BROWN);
    }

    public void setPosition(int x, int y){
        widget.setPosition(x,y);
    }

    public void draw(SpriteBatch batch){
        widget.draw(batch);
    }

    public final void playCardOnBoard(int mouseX, int mouseY) {
        if (isOnBoard(mouseX, mouseY)) {
            checkCardPlayed();
        }
        initCardPosition();
    }

    public void initCardPosition() {

    }

    public void checkCardPlayed() {

    }

    public boolean isOnBoard(float x, float y){
        return widget.isOnWidget(x,y);
    }
}
