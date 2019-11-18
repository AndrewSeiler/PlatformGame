package com.electro593.platformGame;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader
{
    
    private static JSONParser parser = new JSONParser();
    
    public static JSONObject read(String path)
    {
        String pathStr = Defines.RESOURCES + path;
        try
        {
            if (!new File(pathStr).exists())
            {
                Logger.getLogger(Defines.WINDOW_NAME).log(Level.SEVERE, String.format("Null file at %s", pathStr));
                FileHandler.createNew(path);
                return read(path);
            }
            FileReader reader = new FileReader(pathStr);
            JSONObject data = (JSONObject)parser.parse(reader);
            reader.close();
            return data;
        }
        catch (IOException e)
        {
            Logger.getLogger(Defines.WINDOW_NAME).log(Level.SEVERE, String.format("Unable to open file at %s", pathStr), e);
        }
        catch (ParseException e)
        {
            Logger.getLogger(Defines.WINDOW_NAME).log(Level.SEVERE, String.format("Unable to parse file at %s", pathStr), e);
        }
        catch (IndexOutOfBoundsException e)
        {
            Logger.getLogger(Defines.WINDOW_NAME).log(Level.SEVERE, String.format("Error parsing file at %s", pathStr), e);
        }
        return null;
    }
    
}