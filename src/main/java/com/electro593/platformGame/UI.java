package com.electro593.platformGame;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import com.electro593.platformGame.ui.UIFrame;

public class UI
{
    
    private Map<String, UIFrame> uiFrames;
    private UIFrame currUIFrame;
    
    public UI()
    {
        uiFrames = new HashMap<String, UIFrame>();
        currUIFrame = new UIFrame();
        uiFrames.put(Defines.NULLTYPE, currUIFrame);
    }
    
    public void loadFrames()
    {
        
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