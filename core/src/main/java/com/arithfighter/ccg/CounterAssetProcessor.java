package com.arithfighter.ccg;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class CounterAssetProcessor {
    private final AssetManager assetManager;
    private final FileLibrary fileLibrary;

    public CounterAssetProcessor(){
        assetManager = new AssetManager();
        fileLibrary = new FileLibrary();
    }

    public void loadTextures(){
        for (String textureFile : fileLibrary.getTextureFile())
            assetManager.load(textureFile, Texture.class);
    }

    public void loadCards(){
        for(String cardFile : fileLibrary.getCardFiles())
            assetManager.load(cardFile, Texture.class);
    }

    public void finishLoading(){
        assetManager.finishLoading();
    }

    public void update(int millis){
        assetManager.update(millis);
    }

    public Texture[] getTextures() {
        return getTextures(fileLibrary.getTextureFile());
    }

    public Texture[] getCards(){
        return getTextures(fileLibrary.getCardFiles());
    }

    public Texture[] getTextures(String[] files){
        int length = files.length;
        Texture[] textures = new Texture[length];

        for (int i = 0; i < length; i++)
            textures[i] = assetManager.get(files[i]);

        return textures;
    }
}
