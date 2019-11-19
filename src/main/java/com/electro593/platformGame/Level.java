package com.electro593.platformGame;

import com.electro593.platformGame.Entity;
import com.electro593.platformGame.Tile;

import com.electro593.platformGame.Defines;
import com.electro593.platformGame.JSONReader;
import com.electro593.platformGame.TextureCache;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.Graphics;

public class Level
{
    private int viewPosX;
    private int viewPosY;
    private int totalWidth;
    private int totalHeight;
    private Tile[] tileMap;
    private Entity[] entityMap;
    
    public Level()
    {
        TextureCache.empty();
    }
    
    public void loadLevel(String filename)
    {
        JSONObject levelData = (JSONObject)JSONReader.read(Defines.JSON_LEVEL + filename);
        JSONObject tileData = (JSONObject)JSONReader.read(Defines.JSON_TILE);
        JSONObject entityData = (JSONObject)JSONReader.read(Defines.JSON_ENTITY);
        
        JSONObject settings = (JSONObject)levelData.get(Defines.JSON_LEVEL_SETTINGS);
        totalWidth = (int)(long)settings.get(Defines.JSON_LEVEL_SETTINGS_LEVELWIDTH);
        totalHeight = (int)(long)settings.get(Defines.JSON_LEVEL_SETTINGS_LEVELHEIGHT);
        int totalTiles = (int)(long)settings.get(Defines.JSON_LEVEL_SETTINGS_TILETOTAL);
        int totalEntities = (int)(long)settings.get(Defines.JSON_LEVEL_SETTINGS_ENTITYTOTAL);
        
        JSONArray tiles = (JSONArray)levelData.get(Defines.JSON_LEVEL_TILES);
        tileMap = new Tile[totalTiles];
        int index = 0;
        for (Object tileObj : tiles)
        {
            JSONObject tile = (JSONObject)tileObj;
            String type = (String)tile.get(Defines.JSON_LEVEL_TILES_TILETYPE);
            JSONObject data = (JSONObject)tileData.get((type == null) ? Defines.NULLTYPE : type);
            Integer x = (int)(long)tile.get(Defines.JSON_LEVEL_TILES_XPOS);
            Integer y = (int)(long)tile.get(Defines.JSON_LEVEL_TILES_YPOS);
            Integer width = (int)(long)data.get(Defines.JSON_TILE_WIDTH);
            Integer height = (int)(long)data.get(Defines.JSON_TILE_HEIGHT);
            Boolean solid = (boolean)data.get(Defines.JSON_TILE_ISSOLID);
            JSONArray other = (JSONArray)data.get(Defines.JSON_TILE_ATTRIBUTES);
            String[] attributes = new String[other.size()];
            for (int i = 0; i < other.size(); i++)
                attributes[i] = String.valueOf(other.get(i));
            if (TextureCache.getTileTexture(type) == null)
                TextureCache.addTileTexture(type, getClass().getClassLoader().getResource(Defines.TEXTURE_TILES + (String)data.get(Defines.JSON_TILE_TEXTURE)));
            tileMap[index] = new Tile(
                (x == null) ? 0 : x, (y == null) ? 0 : y,
                (width == null) ? 0 : width, (height == null) ? 0 : height,
                (solid == null) ? false : solid,
                (type == null) ? Defines.NULLTYPE : type,
                (other == null) ? new String[0] : attributes);
            index++;
        }
        
        JSONArray entities = (JSONArray)levelData.get(Defines.JSON_LEVEL_ENTITIES);
        entityMap = new Entity[totalEntities];
        index = 0;
        for (Object entityObj : entities)
        {
            JSONObject entity = (JSONObject)entityObj;
            String type = (String)entity.get(Defines.JSON_LEVEL_ENTITIES_ENTITYTYPE);
            JSONObject data = (JSONObject)entityData.get((type == null) ? Defines.NULLTYPE : type);
            Integer x = (int)(long)entity.get(Defines.JSON_LEVEL_ENTITIES_XPOS);
            Integer y = (int)(long)entity.get(Defines.JSON_LEVEL_ENTITIES_YPOS);
            Double velX = (double)data.get(Defines.JSON_ENTITY_VELX);
            Double velY = (double)data.get(Defines.JSON_ENTITY_VELY);
            Double velXMax = (double)data.get(Defines.JSON_ENTITY_VELXMAX);
            Double velYMax = (double)data.get(Defines.JSON_ENTITY_VELYMAX);
            Double accelX = (double)data.get(Defines.JSON_ENTITY_ACCELX);
            Double accelY = (double)data.get(Defines.JSON_ENTITY_ACCELY);
            Double accelXDefault = (double)data.get(Defines.JSON_ENTITY_ACCELXDEFAULT);
            Double accelYDefault = (double)data.get(Defines.JSON_ENTITY_ACCELYDEFAULT);
            Integer width = (int)(long)data.get(Defines.JSON_ENTITY_WIDTH);
            Integer height = (int)(long)data.get(Defines.JSON_ENTITY_HEIGHT);
            Double weight = (double)data.get(Defines.JSON_ENTITY_MASS);
            Integer aiType = (int)(long)data.get(Defines.JSON_ENTITY_AI);
            if (aiType == Defines.PLAYER_AI)
            {
                viewPosX = x;
                viewPosY = y;
            }
            if (TextureCache.getEntityTexture(type) == null)
                TextureCache.addEntityTexture(type, getClass().getClassLoader().getResource(Defines.TEXTURE_ENTITIES + (String)data.get(Defines.JSON_ENTITY_TEXTURE)));
            entityMap[index] = new Entity(
                (x == null) ? 0 : (double)x, (y == null) ? 0 : (double)y,
                (velX == null) ? 0 : velX, (velY == null) ? 0 : velY,
                (velXMax == null) ? 0 : velXMax, (velYMax == null) ? 0 : velYMax,
                (accelX == null) ? 0 : accelX, (accelY == null) ? 0 : accelY,
                (accelXDefault == null) ? 0 : accelXDefault, (accelYDefault == null) ? 0 : accelYDefault,
                (width == null) ? 0 : width, (height == null) ? 0 : height,
                (weight == null) ? 0 : weight,
                (aiType == null) ? 0 : aiType,
                (type == null) ? Defines.NULLTYPE : type);
            index++;
        }
    }
    
    public void update(int posX, int posY)
    {
        int width = Defines.WINDOW_WIDTH;
        int height = Defines.WINDOW_HEIGHT;
        int levelWidth = PlatformGame.getLevel().getTotalWidth();
        int levelHeight = PlatformGame.getLevel().getTotalHeight();
        this.viewPosX = posX - (width / 2);
        this.viewPosY = posY - (height / 2);
        if (this.viewPosX < 0)
            this.viewPosX = 0;
        if (this.viewPosY < 0)
            this.viewPosY = 0;
        if (this.viewPosX > levelWidth - width)
            this.viewPosX = levelWidth - width;
        if (this.viewPosY > levelHeight - height)
            this.viewPosY = levelHeight - height;
        if (width > levelWidth)
            this.viewPosX /= 2;
        if (height > levelHeight)
            this.viewPosY /= 2;
    }
    
    public void paint(Graphics g, double step)
    {
        for (Entity e : entityMap)
        {
            if (e.getAIType() == Defines.PLAYER_AI)
            {
                double prevPosX = e.getPrevPosX();
                double prevPosY = e.getPrevPosY();
                update((int)(prevPosX + ((e.getPosX() - prevPosX) * step)), (int)(prevPosY + ((e.getPosY() - prevPosY) * step)));
            }
        }
        int windowWidth = Defines.WINDOW_WIDTH;
        int windowHeight = Defines.WINDOW_HEIGHT;
                
        for (Tile tile : tileMap)
        {
            int drawX = tile.getPosX() - viewPosX;
            int drawY = tile.getPosY() - viewPosY;
            int drawW = tile.getWidth();
            int drawH = tile.getHeight();
            if (drawX >= 0 - drawW && drawX <= windowWidth && drawY >= 0 - drawH && drawY <= windowHeight)
                g.drawImage(
                    TextureCache.getTileTexture(tile.getTextureMapKey()),
                    drawX, drawY, drawW, drawH, null);
        }
        for (Entity entity : entityMap)
        {
            if (entity.getAIType() == Defines.PLAYER_AI)
            {
                int drawX = windowWidth / 2;
                int drawY = windowHeight / 2;
                int drawW = entity.getWidth();
                int drawH = entity.getHeight();
                
                if (viewPosX <= 0)
                    drawX = (int)entity.getPosX() - viewPosX;
                else if (viewPosX >= totalWidth - windowWidth)
                    drawX = windowWidth - (totalWidth - (int)entity.getPosX());
                if (viewPosY <= 0)
                    drawY = (int)entity.getPosY() - viewPosY;
                else if (viewPosY >= totalHeight - windowHeight)
                    drawY = windowHeight - (totalHeight - (int)entity.getPosY());
                
                if (drawX >= 0 - drawW && drawX <= windowWidth && drawY >= 0 - drawH && drawY <= windowHeight)
                    g.drawImage(
                        TextureCache.getEntityTexture(entity.getTextureMapKey()),
                        drawX, drawY, drawW, drawH, null);
                
                if (Defines.DEBUG_ENABLED)
                {
                    g.drawString(String.format("PosX: %f", entity.getPosX()), 40, 60);
                    g.drawString(String.format("PosY: %f", entity.getPosY()), 40, 80);
                    g.drawString(String.format("VelX: %f", entity.getVelX()), 40, 100);
                    g.drawString(String.format("VelY: %f", entity.getVelY()), 40, 120);
                    g.drawString(String.format("AccelX: %f", entity.getAccelX()), 40, 140);
                    g.drawString(String.format("AccelY: %f", entity.getAccelY()), 40, 160);
                }
            }
            else
            {
                int drawX = (int)entity.getPosX() - viewPosX;
                int drawY = (int)entity.getPosY() - viewPosY;
                int drawW = entity.getWidth();
                int drawH = entity.getHeight();
                
                if (drawX >= 0 - drawW && drawX <= totalWidth && drawY >= 0 - drawH && drawY <= totalHeight)
                    g.drawImage(
                        TextureCache.getEntityTexture(entity.getTextureMapKey()),
                        drawX, drawY, drawW, drawH, null);
            }
        }
    }
    
    public int getViewPosX() { return viewPosX; }
    
    public int getViewPosY() { return viewPosY; }
    
    public int getTotalWidth() { return totalWidth; }
    
    public int getTotalHeight() { return totalHeight; }
    
    public Tile[] getTileMap() { return tileMap; }
    
    public Entity[] getEntityMap() { return entityMap; }
    
}