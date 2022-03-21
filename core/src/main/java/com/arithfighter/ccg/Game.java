package com.arithfighter.ccg;

import com.arithfighter.ccg.entity.*;
import com.arithfighter.ccg.widget.BoardArea;
import com.arithfighter.ccg.widget.SumBox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.arithfighter.ccg.WindowSetting.*;

public class Game {
    private final GameDataAccessor dataAccessor;
    private final BoardArea boardArea;
    private final NumberBoxDisplacer numberBoxDisplacer;
    private final Player player;
    private final SumBox sumBox;
    private int cursorX;
    private int cursorY;

    public Game(Texture[] textures, Texture[] cards){
        dataAccessor = new GameDataAccessor();

        player = new Player(textures, cards, CharacterList.ROGUE) {
            @Override
            public void doWhenCardPlayed() {
                dataAccessor.updatePlayTimes();
            }

            @Override
            public void castSkill(CharacterList character) {
                castCharacterSkill(character);
            }
        };

        boardArea = new BoardArea(textures[1]) {
            @Override
            public void initCardPosition() {
                player.initHand();
            }

            @Override
            public void checkCardPlayed() {
                player.playCard();
            }
        };
        boardArea.setPosition(CENTER_X + GRID_X * 4, GRID_Y * 6);

        numberBoxDisplacer = new NumberBoxDisplacer(textures[3]) {
            @Override
            public void doWhenSumAndNumMatched() {
                dataAccessor.updateScore(1);
            }
        };

        sumBox = new SumBox(textures[2]);
        sumBox.setPosition(CENTER_X + GRID_X * 8, GRID_Y * 7);
    }

    public Player getPlayer() {
        return player;
    }

    public BoardArea getBoardArea(){
        return boardArea;
    }

    private void castCharacterSkill(CharacterList character) {
        switch (character) {
            case KNIGHT:
                //change one value of numberBox
                numberBoxDisplacer.set(0, 33);
                break;
            case ROGUE:
                //reduce all values by 1
                for(int i = 0; i< numberBoxDisplacer.getNumberBoxQuantity();i++)
                    if (numberBoxDisplacer.getNumberList().get(i)>0){
                        numberBoxDisplacer.set(
                                i,
                                numberBoxDisplacer.getNumberList().get(i)-1);
                    }
                break;
        }
    }

    public void update(int mouseX, int mouseY){
        cursorX = mouseX;
        cursorY = mouseY;

        numberBoxDisplacer.update(player.getSum());

        player.checkCardIsTouched(mouseX, mouseY);

        //This is for test, will remove in future version
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            refresh();
        }
    }

    private void refresh(){
        dataAccessor.resetRecorder();
        numberBoxDisplacer.refresh();
    }

    public void draw(SpriteBatch batch) {
        dataAccessor.draw(cursorX, cursorY, player.getEnergy(), batch);//for dev

        boardArea.draw(batch);

        numberBoxDisplacer.draw(batch);

        sumBox.changeColor(player.getCondition());
        sumBox.draw(player.getSum(), batch);

        player.draw(batch);
    }

    public void dispose(){
        dataAccessor.dispose();
        numberBoxDisplacer.dispose();
        player.dispose();
        sumBox.dispose();
    }
}