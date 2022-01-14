package com.arithfighter.ccg.widget;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Desk {
    int deskX;
    int deskY;
    Sprite desk;
    float deskWidth;
    float deskHeight;
    
    public Desk(int x, int y, Texture texture, float scale){
        deskX = x;
        deskY = y;

        desk = new Sprite(texture);
        desk.setPosition(deskX,deskY);
        desk.setSize(texture.getWidth()*scale, texture.getHeight()*scale);

        deskWidth = texture.getWidth()*scale;
        deskHeight = texture.getHeight()*scale;
    }
    
    public void draw(SpriteBatch batch){
        desk.draw(batch);
    }
    
    public boolean isOnDesk(float x, float y){
        return x > deskX && x < deskX + deskWidth &&
                y > deskY && y < deskY + deskHeight;
    }
}
