package com.arithfighter.ccg.widget;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mask {
    private final SpriteWidget widget;

    public Mask(Texture texture, float scale){
        widget = new SpriteWidget(texture, scale);
    }

    public void setPosition(float x, float y){
        widget.setPosition(x,y);
    }

    public void draw(SpriteBatch batch){
        widget.getSprite().setColor(Color.BLACK);
        widget.draw(batch);
    }

    public void debug(SpriteBatch batch){
        widget.getSprite().setColor(Color.GREEN);
        widget.draw(batch);
    }
}