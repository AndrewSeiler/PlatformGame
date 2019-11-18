package com.electro593.platformGame;

import com.electro593.platformGame.Entity;
import com.electro593.platformGame.Level;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.GraphicsConfiguration;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import java.awt.image.BufferStrategy;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* 
 * TODO: UI (In Progress)
 * TODO: Better config generation
 * TODO: JSON Defines
 * TODO: JSON AI
 * TODO: Only draw on-screen tiles
 * TODO: Audio
**/
public class PlatformGame extends Frame
{
    
    private static final long serialVersionUID = 1L;
        
    private static Level level;
    private static KeyEvent keyEvent;
    private static UI ui;
    
    public static int levelNum = 0;
            
    public static void main(String[] args)
    {
        PlatformGame game = new PlatformGame(Defines.WINDOW_NAME);
        PlatformGame.setLevelNum(Defines.INITIAL_LEVEL);
        PlatformGame.setLevel(new Level());
        level.loadLevel(String.format("level%d.json", PlatformGame.levelNum));
        PlatformGame.setUI(new UI());
        ui.loadFrames();
        
        // Create Window
        keyEvent = new KeyEvent();
        PlatformGame.setKeyEvent(keyEvent);
        game.addKeyListener(keyEvent);
        game.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowEvent)
            {
                System.exit(0);
            }
        });
        game.setResizable(false);
        Canvas canvas = new Canvas();
        canvas.setSize(Defines.WINDOW_WIDTH, Defines.WINDOW_HEIGHT);
        canvas.setIgnoreRepaint(true);
        game.add(canvas);
        game.pack();
        game.setVisible(true);
        
        canvas.createBufferStrategy(2);
        BufferStrategy buffer = canvas.getBufferStrategy();
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage bi = gc.createCompatibleImage(Defines.WINDOW_WIDTH, Defines.WINDOW_HEIGHT);
        Graphics graphics = null;
        Graphics2D g2d = null;
        Color background = Color.WHITE;
        
        int interval = 16;
        int lastTime = (int)(System.nanoTime() / 1000000);
        double lag = 0.0;
        
        while (true)
        {
            try
            {
                int time = (int)(System.nanoTime() / 1000000);
                int elapsed = time - lastTime;
                lastTime = time;
                lag += elapsed;
                
                while (lag >= interval)
                {
                    game.update();
                    lag -= interval;
                }
                
                g2d = bi.createGraphics();
                g2d.setColor(background);
                g2d.fillRect(0, 0, Defines.WINDOW_WIDTH - 1, Defines.WINDOW_HEIGHT - 1);
                
                g2d.setFont(Defines.DEFUALT_FONT);
                g2d.setColor(Defines.BACKGROUND_COLOR);
                
                game.paint(g2d, lag / interval);
                
                graphics = buffer.getDrawGraphics();
                graphics.drawImage(bi, 0, 0, null);
                if (!buffer.contentsLost())
                    buffer.show();
                
                Thread.sleep(Math.max((time + interval) - (System.nanoTime() / 1000000), 0));
            }
            catch (InterruptedException e)
            {
                Logger.getLogger(Defines.WINDOW_NAME).log(java.util.logging.Level.SEVERE, "Interrupted Game Loop", e);
            }
            finally
            {
                if (graphics != null)
                    graphics.dispose();
                if (g2d != null)
                    g2d.dispose();
            }
        }
    }
    
    public PlatformGame(String name)
    {
        super(name);
    }
    
    public void update()
    {
        Entity[] entityMap = level.getEntityMap();
        for (Entity entity : entityMap)
        {
            if (entity != null)
                entity.update();
        }
        //ui.update();
    }
    
    public void paint(Graphics g, double step)
    {
        level.paint(g, step);
        //ui.paint(g, step);
    }
    
    public static Level getLevel() { return level; }
    public static void setLevel(Level level) { PlatformGame.level = level; }
    
    public static void changeLevel(int num) { PlatformGame.level.loadLevel(String.format("level%d.json", num)); }
    
    public static KeyEvent getKeyEvent() { return keyEvent; }
    public static void setKeyEvent(KeyEvent keyEvent) { PlatformGame.keyEvent = keyEvent; }
    
    public static UI getUI() { return ui; }
    public static void setUI(UI ui) { PlatformGame.ui = ui; }
    
    public static int getLevelNum() { return levelNum; }
    public static void setLevelNum(int levelNum) { PlatformGame.levelNum = levelNum; }
    
}