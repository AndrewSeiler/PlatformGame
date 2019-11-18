package com.electro593.platformGame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.electro593.platformGame.Defines;

public class TextureCache
{
    
    private static Map<String, BufferedImage> tileTextures = new HashMap<String, BufferedImage>();
    private static Map<String, BufferedImage> entityTextures = new HashMap<String, BufferedImage>();
    private static Map<String, BufferedImage> uiTextures = new HashMap<String, BufferedImage>();
    
    public static BufferedImage getTileTexture(String key)
    {
        return tileTextures.get(key);
    }
    
    public static void addTileTexture(String key, URL url)
    {
        try
        {
            tileTextures.put(key, ImageIO.read(url));
        }
        catch (IOException e)
        {
            tileTextures.put(key, null);
            Logger.getLogger(Defines.WINDOW_NAME).log(Level.SEVERE, String.format("Missing tile texture at %s", url.toExternalForm()), e);
        }
    }
    
    public static BufferedImage getEntityTexture(String key)
    {
        return entityTextures.get(key);
    }
    
    public static void addEntityTexture(String key, URL url)
    {
        try
        {
            entityTextures.put(key, ImageIO.read(url));
        }
        catch (IOException e)
        {
            entityTextures.put(key, null);
            Logger.getLogger(Defines.WINDOW_NAME).log(Level.SEVERE, String.format("Missing entity texture at %s", url.toExternalForm()), e);
        }
    }
    
    public static BufferedImage getUITexture(String key)
    {
        return entityTextures.get(key);
    }
    
    public static void addUITexture(String key, URL url)
    {
        try
        {
            uiTextures.put(key, ImageIO.read(url));
        }
        catch (IOException e)
        {
            uiTextures.put(key, null);
            Logger.getLogger(Defines.WINDOW_NAME).log(Level.SEVERE, String.format("Missing ui texture at %s", url.toExternalForm()), e);
        }
    }
    
    public static void empty()
    {
        tileTextures.clear();
        entityTextures.clear();
        uiTextures.clear();
    }
    
}