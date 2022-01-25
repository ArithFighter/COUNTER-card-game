package com.arithfighter.ccg;

import com.arithfighter.ccg.cardgame.GameComponent;
import com.arithfighter.ccg.cardgame.RandomNumArrayGenerator;
import com.arithfighter.ccg.widget.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameCore {
    AssetManager assetManager = new AssetManager();
    FileLibrary fileLibrary = new FileLibrary();
    GameDataDisplacer dataDisplacer;
    GameComponent gameComponent;
    CursorPositionAccessor cursorPos = new CursorPositionAccessor();
    SpriteBatch batch;
    Texture[] textures;
    SumAccessor sumAccessor = new SumAccessor();
    Recorder playRecorder = new Recorder();
    Recorder scoreRecorder = new Recorder();
    RandomNumArrayGenerator randomNumArrayGenerator = new RandomNumArrayGenerator();
    int[] numberList = new int[randomNumArrayGenerator.getMaxQuantity()];
    NumberListInspector numberListInspector = new NumberListInspector();

    InputAdapter mouseAdapter = new InputAdapter() {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            gameComponent.getHand().checkActive(cursorPos.getX(), cursorPos.getY());
            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            gameComponent.getHand().updateWhenDrag(cursorPos.getX(), cursorPos.getY());
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            gameComponent.whenPlayCardOnTable(cursorPos.getX(), cursorPos.getY());
            return true;
        }
    };

    public void create() {
        for (String textureFile : fileLibrary.getTextureFile())
            assetManager.load(textureFile, Texture.class);

        assetManager.finishLoading();

        storeTextures();

        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(mouseAdapter);

        dataDisplacer = new GameDataDisplacer();

        gameComponent = new GameComponent(textures) {
            @Override
            public void doWhenCardPlayed() {
                playRecorder.update();
                sumAccessor.updateSum(gameComponent.getHand().getCardNumber());

                if (gameComponent.getHand().isResetCard())
                    sumAccessor.resetSum();
            }
        };
    }

    private void storeTextures() {
        textures = new Texture[fileLibrary.getTextureFile().length];
        for (int i = 0; i < fileLibrary.getTextureFile().length; i++)
            textures[i] = assetManager.get(fileLibrary.getTextureFile()[i]);
    }

    public void render() {
        assetManager.update(17);

        cursorPos.updateCursorPosition();

        numberList = randomNumArrayGenerator.generateNumbers();

        gameComponent.getNumbers(numberList);

        handleWhenNumMatchedSum();

//        numberListInspector.inspectNumberList(numberList);

        if (numberListInspector.isAllNumberAreZero()){
            randomNumArrayGenerator.clear();
        }

        resetAnyThingsManually();

        workSpriteBatch();
    }

    private void handleWhenNumMatchedSum() {
//        for (int i = 0; i < numberSet.size(); i++) {
//            if (sumAccessor.getSum() == numberList.get(i)) {
//                if (numberList.get(i) > 0) {
//                    scoreRecorder.update();
//                    numberList.set(i, 0);
//                }
//            }
//        }
    }

    private void resetAnyThingsManually() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            randomNumArrayGenerator.clear();
            resetVariable();
        }
    }

    private void resetVariable() {
        playRecorder.reset();
        scoreRecorder.reset();
        sumAccessor.resetSum();
    }

    private void workSpriteBatch() {
        batch.begin();
        drawComponent();
        batch.end();
    }

    private void drawComponent() {
        dataDisplacer.drawMousePos(cursorPos.getX(), cursorPos.getY(), batch);
        dataDisplacer.drawRecord(playRecorder.getRecord(), batch);
        dataDisplacer.drawScoreBoard(scoreRecorder.getRecord(), batch);

        gameComponent.drawTableAndNumbers(batch, sumAccessor.getSum());
        gameComponent.drawHand(batch, cursorPos.getX(), cursorPos.getY());
    }

    public void dispose() {
        batch.dispose();
        dataDisplacer.dispose();
        gameComponent.dispose();
    }
}
