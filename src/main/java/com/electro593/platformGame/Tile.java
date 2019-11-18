package com.electro593.platformGame;

public class Tile
{
    
    private int posX;
    private int posY;
    private int width;
    private int height;
    private boolean solid;
    private String textureMapKey;
    private String[] attributes;
    
    public Tile(int posX, int posY, int width, int height, boolean solid, String textureMapKey, String[] other)
    {
        this.setPosX(posX);
        this.setPosY(posY);
        this.setWidth(width);
        this.setHeight(height);
        this.setSolid(solid);
        this.setTextureMapKey(textureMapKey);
        attributes = other;
    }
    
    public int getPosX() { return posX; }
    public void setPosX(int posX) { this.posX = posX; }
    
    public int getPosY() { return posY; }
    public void setPosY(int posY) { this.posY = posY; }
    
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    
    public void setSolid(boolean solid) { this.solid = solid; }
    public boolean getSolid() { return solid; }
    
    public String getTextureMapKey() { return textureMapKey; }
    public void setTextureMapKey(String textureMapKey) { this.textureMapKey = textureMapKey; }
    
    public boolean hasAttribute(String key)
    {
        for (String str : attributes)
            if (str.equals(key))
                return true;
        return false;
    }
    
}