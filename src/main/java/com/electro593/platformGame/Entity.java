package com.electro593.platformGame;

public class Entity
{
    
    private double posX;
    private double posY;
    private double prevPosX;
    private double prevPosY;
    private double velX;
    private double velY;
    private double velXMax;
    private double velYMax;
    private double accelX;
    private double accelY;
    private double accelXDefault;
    private double accelYDefault;
    private int width;
    private int height;
    private double mass;
    private int aiType;
    private String textureMapKey;
    private boolean decelerateX;
    private boolean jumped;
    
    
    public Entity(double posX, double posY,
                      double velX, double velY,
                      double velXMax, double velYMax,
                      double accelX, double accelY,
                      double accelXDefault, double accelYDefault,
                      int width, int height,
                      double mass,
                      int aiType,
                      String textureMapKey)
    {
        this.setPosX(posX);
        this.setPosY(posY);
        this.setPrevPosX(posX);
        this.setPrevPosY(posY);
        this.setVelX(velX);
        this.setVelY(velY);
        this.setVelXMax(velXMax);
        this.setVelYMax(velYMax);
        this.setAccelX(accelX);
        this.setAccelY(accelY);
        this.setAccelXDefault(accelXDefault);
        this.setAccelYDefault(accelYDefault);
        this.setWidth(width);
        this.setHeight(height);
        this.setMass(mass);
        this.setAIType(aiType);
        this.setTextureMapKey(textureMapKey);
        jumped = true;
        decelerateX = false;
    }
    
    public void update()
    {
        prevPosX = posX;
        prevPosY = posY;
        accelY = Defines.GRAVITY * mass;
        switch (aiType)
        {
            case Defines.PLAYER_AI:
                if (!jumped && PlatformGame.getKeyEvent().getPressed(Defines.JSON_CONTROLS_MOVEUP))
                {
                    velY = -accelYDefault;
                    jumped = true;
                }
                
                if (PlatformGame.getKeyEvent().getPressed(Defines.JSON_CONTROLS_MOVELEFT))
                {
                    accelX = -accelXDefault;
                    decelerateX = false;
                }
                else if (PlatformGame.getKeyEvent().getPressed(Defines.JSON_CONTROLS_MOVERIGHT))
                {
                    accelX = accelXDefault;
                    decelerateX = false;
                }
                else if (!decelerateX && accelX != 0)
                {
                    accelX = -accelX;
                    decelerateX = true;
                }
                else if (decelerateX && Math.abs(velX) - Math.abs(accelX) < 0)
                {
                    velX = 0;
                    accelX = 0;
                    decelerateX = false;
                }
                break;
        }
        
        velX += accelX;
        if (velX > velXMax)
            velX = velXMax;
        if (velX < -velXMax)
            velX = -velXMax;
        velY += accelY;
        if (velY > velYMax)
            velY = velYMax;
        if (velY < -velYMax)
            velY = -velYMax;
        while (!checkBoundsX((int)(posX + velX), (int)posY))
        {
            if (Math.abs(velX) < 0.01)
            {
                velX = 0;
                break;
            }
            velX /= 2;
        }
        posX += velX;
        while (!checkBoundsY((int)(posY + velY), (int)posX))
        {
            if (Math.abs(velY) < 0.01)
            {
                velY = 0;
                break;
            }
            velY /= 2;
            if (velY >= 0)
                jumped = false;
        }
        posY += velY;
        
        if (aiType == Defines.PLAYER_AI)
            PlatformGame.getLevel().update((int)posX, (int)posY);
        
        if (checkTouching(Defines.JSON_TILE_ATTRIBUTES_GOAL))
        {
            int num = PlatformGame.getLevelNum();
            PlatformGame.setLevelNum(num + 1);
            PlatformGame.changeLevel(num + 1);
        }
    }
    
    public boolean checkBoundsX(int newPosX, int oldPosY)
    {
        if (newPosX < 0 || newPosX > PlatformGame.getLevel().getTotalWidth() - width)
            return false;
        for (Tile tile : PlatformGame.getLevel().getTileMap())
        {
            if (tile.getSolid())
            {
                int tileX = tile.getPosX();
                int tileY = tile.getPosY();
                if (newPosX + width < tileX + tile.getWidth() + width && newPosX > tileX - width &&
                    oldPosY + height < tileY + tile.getHeight() + height && oldPosY > tileY - height)
                    return false;
            }
        }
        return true;
    }
    
    public boolean checkBoundsY(int newPosY, int oldPosX)
    {
        if (newPosY < 0 || newPosY > PlatformGame.getLevel().getTotalHeight() - height)
            return false;
        for (Tile tile : PlatformGame.getLevel().getTileMap())
        {
            if (tile.getSolid())
            {
                int tileX = tile.getPosX();
                int tileY = tile.getPosY();
                int tileW = tile.getWidth();
                int tileH = tile.getHeight();
                if (newPosY + height < tileY + tileH + height && newPosY > tileY - height &&
                    oldPosX + width < tileX + tileW + width && oldPosX > tileX - width)
                    return false;
            }
        }
        return true;
    }
    
    public boolean checkTouching(String criteria)
    {
        for (Tile tile : PlatformGame.getLevel().getTileMap())
        {
            if (tile.hasAttribute(criteria))
            {
                int tileX = tile.getPosX();
                int tileY = tile.getPosY();
                int tileW = tile.getWidth();
                int tileH = tile.getHeight();
                if (posY + height < tileY + tileH + height && posY > tileY - height &&
                    posX + width < tileX + tileW + width && posX > tileX - width)
                    return true;
            }
        }
        return false;
    }
    
    public double getPosX() { return posX; }
    public void setPosX(double posX) { this.posX = posX; }
    
    public double getPosY() { return posY; }
    public void setPosY(double posY) { this.posY = posY; }
    
    public double getPrevPosX() { return prevPosX; }
    public void setPrevPosX(double prevPosX) { this.prevPosX = prevPosX; }
    
    public double getPrevPosY() { return prevPosY; }
    public void setPrevPosY(double prevPosY) { this.prevPosY = prevPosY; }
    
    public double getVelX() { return velX; }
    public void setVelX(double velX) { this.velX = velX; }
    
    public double getVelY() { return velY; }
    public void setVelY(double velY) { this.velY = velY; }
    
    public double getVelXMax() { return velXMax; }
    public void setVelXMax(double velXMax) { this.velXMax = velXMax; }
    
    public double getVelYMax() { return velYMax; }
    public void setVelYMax(double velYMax) { this.velYMax = velYMax; }
    
    public double getAccelX() { return accelX; }
    public void setAccelX(double accelX) { this.accelX = accelX; }
    
    public double getAccelY() { return accelY; }    
    public void setAccelY(double accelY) { this.accelY = accelY; }
    
    public double getAccelXDefault() { return accelXDefault; }
    public void setAccelXDefault(double accelXDefault) { this.accelXDefault = accelXDefault; }
    
    public double getAccelYDefault() { return accelYDefault; }    
    public void setAccelYDefault(double accelYDefault) { this.accelYDefault = accelYDefault; }
    
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    
    public double getMass() { return mass; }
    public void setMass(double mass) { this.mass = mass; }
    
    public int getAIType() { return aiType; }
    public void setAIType(int aiType) { this.aiType = aiType; }
    
    public String getTextureMapKey() { return textureMapKey; }
    public void setTextureMapKey(String textureMapKey) { this.textureMapKey = textureMapKey; }
    
}