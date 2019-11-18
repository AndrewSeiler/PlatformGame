package com.electro593.platformGame;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;

import com.electro593.platformGame.Defines;
import com.electro593.platformGame.JSONReader;

import org.json.simple.JSONObject;

public class KeyEvent implements KeyListener
{
    
    private HashMap<Integer, Boolean> keyMap;
    private HashMap<String, Integer> keyNameMap;
    
    public KeyEvent()
    {
        keyMap = new HashMap<Integer, Boolean>();
        keyNameMap = new HashMap<String, Integer>();
        JSONObject controls = JSONReader.read(Defines.JSON_CONTROLS);
        for (Iterator<?> iterator = controls.keySet().iterator(); iterator.hasNext();)
        {
            Object next = iterator.next();
            int code = (int)(long)controls.get(next);
            keyMap.put(code, false);
            keyNameMap.put((String)next, code);
        }
    }
    
    @Override
    public void keyTyped(java.awt.event.KeyEvent e)
    {
    }
    
    @Override
    public void keyPressed(java.awt.event.KeyEvent e)
    {
        int code = e.getKeyCode();
        if (keyMap.get(code) == null)
            keyMap.put(code, true);
        else
            keyMap.replace(code, true);
    }
    
    @Override
    public void keyReleased(java.awt.event.KeyEvent e)
    {
        int code = e.getKeyCode();
        if (keyMap.get(code) == null)
            keyMap.put(code, false);
        else
            keyMap.replace(code, false);
    }
    
    public boolean getPressed(int code) { return keyMap.get(code); }
    
    public boolean getPressed(String key) { return getPressed(keyNameMap.get(key)); }
    
}