package com.arithfighter.ccg.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Font {
    FreeTypeFontGenerator fontGenerator;
	FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
	BitmapFont font;

    public Font(int size){
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fontstyle/pcsenior.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = size;
        font = fontGenerator.generateFont(fontParameter);
    }

    public void setColor(Color color){
        font.setColor(color);
    }

    public void draw(SpriteBatch batch, String content, float numberX, float numberY){
        font.draw(batch, content, numberX,numberY);
    }

    public void dispose(){
        font.dispose();
        fontGenerator.dispose();
    }
}
