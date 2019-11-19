package com.electro593.platformGame;

import java.awt.Font;
import java.awt.Color;

public class Defines
{
    
    public static final String JSON_LEVEL = "levels/";                             // Path location for level json files
    public static final String JSON_LEVEL_SETTINGS = "settings";                   // JSON object for level settings
    public static final String JSON_LEVEL_SETTINGS_LEVELWIDTH = "mapWidth";        // Integer value for level width, in SETTINGS
    public static final String JSON_LEVEL_SETTINGS_LEVELHEIGHT = "mapHeight";      // Integer value for level height, in SETTINGS
    public static final String JSON_LEVEL_SETTINGS_TILETOTAL = "totalTiles";       // Integer value for total tiles in level, in SETTINGS
    public static final String JSON_LEVEL_SETTINGS_ENTITYTOTAL = "totalEntities";  // Integer value for total entities in level, in SETTINGS
    public static final String JSON_LEVEL_TILES = "tiles";                         // JSON array for level tiles
    public static final String JSON_LEVEL_TILES_TILETYPE = "type";                 // String value for type of tile
    public static final String JSON_LEVEL_TILES_XPOS = "x";                        // Integer value for x position of tile
    public static final String JSON_LEVEL_TILES_YPOS = "y";                        // Integer value for y position of tile
    public static final String JSON_LEVEL_ENTITIES = "entities";                   // JSON array for level entities
    public static final String JSON_LEVEL_ENTITIES_ENTITYTYPE = "type";            // String value for type of entitiy
    public static final String JSON_LEVEL_ENTITIES_XPOS = "x";                     // Integer value for initial x position of entity
    public static final String JSON_LEVEL_ENTITIES_YPOS = "y";                     // Integer value for initial y position of entity
    
    public static final String JSON_TILE = "config/tileData.json";                 // Path location for tile data json file
    public static final String JSON_TILE_WIDTH = "width";                          // Integer value for width of specified tile
    public static final String JSON_TILE_HEIGHT = "height";                        // Integer value for height of specified tile
    public static final String JSON_TILE_ISSOLID = "solid";                        // Boolean value for if the specified tile can be walked through
    public static final String JSON_TILE_TEXTURE = "texture";                      // String value for path of specified tile's texture
    public static final String JSON_TILE_ATTRIBUTES = "other";                     // String value for array of tile's attributes
    public static final String JSON_TILE_ATTRIBUTES_GOAL = "goal";                 // String value for if tile is end-of-level
    
    public static final String JSON_ENTITY = "config/entityData.json";             // Path location for entity data json file
    public static final String JSON_ENTITY_VELX = "velocityX";                     // Double value for specified entity's initial x velocity
    public static final String JSON_ENTITY_VELY = "velocityY";                     // Double value for specified entity's initial y velocity
    public static final String JSON_ENTITY_WIDTH = "width";                        // Integer value for specified entity's width
    public static final String JSON_ENTITY_HEIGHT = "height";                      // Integer value for specified entity's height
    public static final String JSON_ENTITY_AI = "aiType";                          // Integer value for specified entity's ai type
    public static final String JSON_ENTITY_TEXTURE = "texture";                    // String value for specified entity's texture
    public static final String JSON_ENTITY_VELXMAX = "velocityXMax";               // Double value for specified entity's max x velocity
    public static final String JSON_ENTITY_VELYMAX = "velocityYMax";               // Double value for specified entity's max y velocity
    public static final String JSON_ENTITY_ACCELX = "accelerationX";               // Double value for specified entity's initial x acceleration
    public static final String JSON_ENTITY_ACCELY = "accelerationY";               // Double value for specified entity's initial y acceleration
    public static final String JSON_ENTITY_ACCELXDEFAULT = "accelerationXDefault"; // Double value for default x acceleration when moving
    public static final String JSON_ENTITY_ACCELYDEFAULT = "accelerationYDefault"; // Double value for default y acceleration when moving
    public static final String JSON_ENTITY_MASS = "mass";                          // Double value for entity's mass
    
    public static final String JSON_CONTROLS = "config/controls.json";             // Path location for player controls
    public static final String JSON_CONTROLS_MOVEUP = "moveUp";                    // Integer value for ASCII key press, movement up
    public static final String JSON_CONTROLS_MOVEDOWN = "moveDown";                // Integer value for ASCII key press, movement down
    public static final String JSON_CONTROLS_MOVELEFT = "moveLeft";                // Integer value for ASCII key press, movement left
    public static final String JSON_CONTROLS_MOVERIGHT = "moveRight";              // Integer value for ASCII key press, movement right
    
    public static final String JSON_UI = "config/ui.json";
    public static final String JSON_UI_POSX = "x"; // Integer
    public static final String JSON_UI_POSY = "y"; // Integer
    public static final String JSON_UI_VELX = "velocityX"; // Double
    public static final String JSON_UI_VELY = "velocityY"; // Double
    public static final String JSON_UI_VELXMAX = "velocityXMax"; // Double
    public static final String JSON_UI_VELYMAX = "velocityYMax"; // Double
    public static final String JSON_UI_ACCELX = "accelerationX"; // Double
    public static final String JSON_UI_ACCELY = "accelerationY"; // Double
    public static final String JSON_UI_WIDTH = "width"; // Integer
    public static final String JSON_UI_HEIGHT = "height"; // Integer
    public static final String JSON_UI_TYPE = "type"; // String
    public static final String JSON_UI_COLOR = "color"; // JSONArray<Double>
    public static final String JSON_UI_TEXT = "text"; // String
    public static final String JSON_UI_ATTRIBUTES = "other"; // JSONArray<JSONObject>
    public static final String JSON_UI_SUBELEMENTS = "elements"; // JSONArray<JSONObject>
    public static final String JSON_UI_FONT = "font"; // String
    public static final String JSON_UI_FONT_SIZE = "size"; // Integer
    public static final String JSON_UI_FONT_TYPE = "type"; // String
    public static final String JSON_UI_TEXTURE = "texture"; // String
    
    public static final String UI_TYPE_FRAME = "frame";
    public static final String UI_TYPE_IMAGE = "image";
    public static final String UI_TYPE_TEXT = "text";
    
  //public static final String  = ""; // 
    
    public static final String TEXTURE_TILES = "textures/tiles/";                  // Path location for tile texture files
    public static final String TEXTURE_ENTITIES = "textures/entities/";            // Path location for entity texture files
    
    public static final String RESOURCES = "src/main/resources/";                  // Path for all resources
    
    public static final String NULLTYPE = "NONE";                                  // Entity / Tile type if not specified
    
    public static final String WINDOW_NAME = "Platform Game";                      // Name of window
    public static final int WINDOW_WIDTH = 800;                                    // Width (in pixels) of window
    public static final int WINDOW_HEIGHT = 640;                                   // Height (in pixels) of window
    public static final int INITIAL_LEVEL = 0;                                     // Starting level
    public static final int PLAYER_AI = 1;                                         // AI value for controllable player
    
    public static final double GRAVITY = 0.2;                                      // Downwards acceleration from gravity
    
    public static final boolean DEBUG_ENABLED = true;                              // Whether or not to show debug information
    
    public static final Font DEFUALT_FONT = new Font("Courier New", Font.PLAIN, 12);
    public static final Color BACKGROUND_COLOR = Color.BLUE;
    
}