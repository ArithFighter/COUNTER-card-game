package com.arithfighter.ccg.scene;

import com.arithfighter.ccg.entity.CharacterList;
import com.arithfighter.ccg.font.Font;
import com.arithfighter.ccg.widget.Button;
import com.arithfighter.ccg.widget.PanelButton;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CharacterMenu {
    private final Font selectionFont;
    private final PanelButton[] buttons;
    private final Button startButton;
    private int selectIndex = 0;
    private enum GameReady {NEUTRAL, READY, START}
    private GameReady gameReady = GameReady.NEUTRAL;
    private SpriteBatch batch;

    public CharacterMenu(Texture[] textures, Texture[] panels) {
        int length = CharacterList.values().length;
        buttons = new PanelButton[length];

        ButtonPlacer placer = new ButtonPlacer();

        for (int i = 0; i < length; i++) {
            buttons[i] = new PanelButton(panels[i]);
            buttons[i].setPosition(placer.getButtonX(i), placer.getButtonY(i));
        }

        startButton = new Button(textures[6], 1.8f);
        startButton.setPosition(900, 120);

        selectionFont = new Font(36);
        selectionFont.setColor(Color.WHITE);
    }

    public boolean isCharButtonActive() {
        return buttons[selectIndex].isActive();
    }

    public boolean isStartButtonActive(){
        return startButton.isActive();
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void init() {
        gameReady = GameReady.NEUTRAL;
    }

    public void draw() {
        handleStartButton();

        CharacterList[] characters = CharacterList.values();

        for (PanelButton button : buttons) button.draw(batch);

        startButton.draw(batch, "Start");

        selectionFont.draw(batch, characters[getSelectIndex()].name(), 900, 500);
    }

    private void handleStartButton(){
        if (startButton.isActive())
            gameReady = GameReady.READY;
        else{
            if (gameReady == GameReady.READY)
                gameReady = GameReady.START;
        }
    }

    public int getSelectIndex() {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isActive())
                selectIndex = i;
        }
        return selectIndex;
    }

    public boolean isGameStart() {
        return gameReady == GameReady.START;
    }

    public void activateButton(int mouseX, int mouseY) {
        for (PanelButton button : buttons)
            button.activate(mouseX, mouseY);

        startButton.activate(mouseX, mouseY);
    }

    public void deactivateButton() {
        for (PanelButton button : buttons)
            button.deactivate();

        startButton.deactivate();
    }

    public void dispose() {
        startButton.dispose();
        selectionFont.dispose();
    }
}

class ButtonPlacer{
    public int getButtonX(int i) {
        return i < 3 ? 200 : 500;
    }

    public int getButtonY(int i) {
        int initY = 480;
        int margin = 180;
        int row = 3;

        return i < row ? initY - i * margin : initY - (i - row) * margin;
    }
}