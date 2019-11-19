package com.electro593.platformGame;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.electro593.platformGame.ui.UIFrame;
import com.electro593.platformGame.ui.UIImage;
import com.electro593.platformGame.ui.UIString;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UI
{
    
    private Map<String, UIFrame> uiFrames;
    private UIFrame currUIFrame;
    
    public UI()
    {
        uiFrames = new HashMap<String, UIFrame>();
        currUIFrame = null;
        uiFrames.put(Defines.NULLTYPE, currUIFrame);
    }
    
    public void loadFrames()
    {
        JSONObject uiData = (JSONObject)JSONReader.read(Defines.JSON_UI);
        for (Iterator<?> iterator = uiData.keySet().iterator(); iterator.hasNext() && uiData != null;)
        {
            Object next = iterator.next();
            JSONObject frame = (JSONObject)next;
            String type = (String)frame.get(Defines.JSON_UI_TYPE);
            addUIFrame(type, loadRecursive(frame));
        }
    }
    
    private UIFrame loadRecursive(JSONObject frame)
    {
        Integer posX = (int)(long)frame.get(Defines.JSON_UI_POSX);
        Integer posY = (int)(long)frame.get(Defines.JSON_UI_POSY);
        Integer width = (int)(long)frame.get(Defines.JSON_UI_WIDTH);
        Integer height = (int)(long)frame.get(Defines.JSON_UI_HEIGHT);
        Double velX = (double)frame.get(Defines.JSON_UI_VELX);
        Double velY = (double)frame.get(Defines.JSON_UI_VELY);
        Double velXMax = (double)frame.get(Defines.JSON_UI_VELXMAX);
        Double velYMax = (double)frame.get(Defines.JSON_UI_VELYMAX);
        Double accelX = (double)frame.get(Defines.JSON_UI_ACCELX);
        Double accelY = (double)frame.get(Defines.JSON_UI_ACCELY);
        String textureMapKey = (String)frame.get(Defines.JSON_UI_TEXTURE);
        Float[] colorArray = (Float[])frame.get(Defines.JSON_UI_COLOR);
        String text = (String)frame.get(Defines.JSON_UI_TEXT);
        JSONObject fontObj = (JSONObject)frame.get(Defines.JSON_UI_FONT);
        Font font;
        if (fontObj != null)
        {
            String fontType = (String)fontObj.get(Defines.JSON_UI_FONT_TYPE);
            Integer fontSize = (int)(long)fontObj.get(Defines.JSON_UI_FONT_SIZE);
            font = new Font(fontType, Font.PLAIN, fontSize);
        }
        else
            font = Defines.DEFUALT_FONT;
        JSONObject other = (JSONObject)frame.get(Defines.JSON_UI_ATTRIBUTES);
        Map<String, String> attributes = new HashMap<String, String>();
        for (Iterator<?> iterator = other.keySet().iterator(); iterator.hasNext() && other != null;)
        {
            Object next = iterator.next();
            attributes.put((String)next, (String)other.get(next));
        }
        List<UIFrame> elements = new ArrayList<UIFrame>();
        JSONArray jsonElements = (JSONArray)frame.get(Defines.JSON_UI_SUBELEMENTS);
        if (jsonElements != null)
            for (Object obj : jsonElements)
                elements.add(loadRecursive((JSONObject)obj));
        
        UIFrame newFrame;
        switch ((String)frame.get(Defines.JSON_UI_TYPE))
        {
            case Defines.UI_TYPE_FRAME:
                newFrame = new UIFrame(
                    (posX == null) ? 0 : posX, (posY == null) ? 0 : posY,
                    (velX == null) ? 0 : velX, (velY == null) ? 0 : velY,
                    (velXMax == null) ? 0 : velXMax, (velYMax == null) ? 0 : velYMax,
                    (accelX == null) ? 0 : accelX, (accelY == null) ? 0 : accelY,
                    (width == null) ? 0 : width, (height == null) ? 0 : height,
                    (colorArray == null) ? Defines.BACKGROUND_COLOR : new Color(colorArray[0], colorArray[1], colorArray[2], colorArray[3]),
                    attributes, elements);
                return newFrame;
            case Defines.UI_TYPE_TEXT:
                newFrame = new UIString(
                    (posX == null) ? 0 : posX, (posY == null) ? 0 : posY,
                    (velX == null) ? 0 : velX, (velY == null) ? 0 : velY,
                    (velXMax == null) ? 0 : velXMax, (velYMax == null) ? 0 : velYMax,
                    (accelX == null) ? 0 : accelX, (accelY == null) ? 0 : accelY,
                    (width == null) ? 0 : width, (height == null) ? 0 : height,
                    (colorArray == null) ? Defines.BACKGROUND_COLOR : new Color(colorArray[0], colorArray[1], colorArray[2], colorArray[3]),
                    (text == null) ? "" : text, font, attributes);
                return newFrame;
            case Defines.UI_TYPE_IMAGE:
                newFrame = new UIImage(
                    (posX == null) ? 0 : posX, (posY == null) ? 0 : posY,
                    (velX == null) ? 0 : velX, (velY == null) ? 0 : velY,
                    (velXMax == null) ? 0 : velXMax, (velYMax == null) ? 0 : velYMax,
                    (accelX == null) ? 0 : accelX, (accelY == null) ? 0 : accelY,
                    (width == null) ? 0 : width, (height == null) ? 0 : height,
                    (textureMapKey == null) ? "" : textureMapKey, attributes);
                return newFrame;
        }
        return null;
    }
    
    public void update()
    {
        currUIFrame.update();
    }
    
    public void paint(Graphics g, double step)
    {
        currUIFrame.paint(g, step);
    }
    
    public UIFrame getUIFrame() { return currUIFrame; }
    public void setUIFrame(UIFrame uiFrame) { currUIFrame = uiFrame; }
    public UIFrame getUIFrame(String type) { return uiFrames.get(type); }
    public void addUIFrame(String type, UIFrame uiFrame) { uiFrames.put(type, uiFrame); }
    
}