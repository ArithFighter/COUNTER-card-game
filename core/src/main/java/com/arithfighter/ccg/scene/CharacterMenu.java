package com.arithfighter.ccg.scene;

import com.arithfighter.ccg.font.Font;
import com.arithfighter.ccg.widget.Button;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CharacterMenu {
    private final Font selectionFont;
    private final Button[] buttons;
    private final Button startButton;
    private int selectIndex = 0;
    private final String[] names = {"Knight", "Rogue", "Hunter", "Paladin", "Warrior"};
    private boolean isGameStart = false;
    private SpriteBatch batch;

    public CharacterMenu(Texture[] textures) {
        buttons = new Button[names.length];

        for (int i = 0; i < names.length; i++) {
            buttons[i] = new Button(textures[6]);
            buttons[i].setPosition(getButtonX(i), getButtonY(i));
        }

        startButton = new Button(textures[6]);
        startButton.setPosition(900, 120);

        selectionFont = new Font(36);
        selectionFont.setColor(Color.WHITE);
    }

    public void setBatch(SpriteBatch batch){
        this.batch = batch;
    }

    private int getButtonX(int i) {
        return i < 3 ? 200 : 500;
    }

    private int getButtonY(int i) {
        int initY = 480;
        int margin = 150;
        int row = 3;

        return i < row ? initY - i * margin : initY - (i - row) * margin;
    }

    public void init() {
        isGameStart = false;
    }

    public void draw() {
        for (int i = 0; i < buttons.length; i++)
            buttons[i].draw(batch, names[i]);

        startButton.draw(batch, "Start");

        selectionFont.draw(batch, names[getSelectIndex()], 900, 500);
    }

    public int getSelectIndex() {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isActive())
                selectIndex = i;
        }
        return selectIndex;
    }

    public boolean isStart() {
        if (startButton.isActive())
            isGameStart = true;
        return isGameStart;
    }

    public void activateButton(int mouseX, int mouseY) {
        for (Button button : buttons)
            button.activate(mouseX, mouseY);

        startButton.activate(mouseX, mouseY);
    }

    public void deactivateButton() {
        for (Button button : buttons)
            button.deactivate();

        startButton.deactivate();
    }

    public void dispose() {
        for (Button button : buttons)
            button.dispose();

        startButton.dispose();
        selectionFont.dispose();
    }
}