package com.electro593.platformGame;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler
{
    
    public static void createNew(String path)
    {
        Path file = Paths.get(Defines.RESOURCES + path);
        try
        {
            Files.createFile(file);
            if (path == Defines.JSON_TILE)
                Files.write(file, Arrays.asList(tileJson), StandardCharsets.UTF_8);
            else if (path == Defines.JSON_ENTITY)
                Files.write(file, Arrays.asList(entityJson), StandardCharsets.UTF_8);
            else if (path == Defines.JSON_CONTROLS)
                Files.write(file, Arrays.asList(controlsJson), StandardCharsets.UTF_8);
            else
            {
                    Logger.getLogger(Defines.WINDOW_NAME).log(Level.SEVERE, String.format("Unknown filename %s", path));
                    System.exit(1);
            }
        }
        catch (IOException e)
        {
            Logger.getLogger(Defines.WINDOW_NAME).log(Level.SEVERE, String.format("Unable to create new file at %s", path), e);
            System.exit(1);
        }
    }
    
    private static String tileJson = "{\n\n}";
    private static String entityJson = "{\n\n}";
    private static String controlsJson = "{\n\t\"moveUp\": 87,\n\t\"moveDown\": 83,\n\t\"moveLeft\": 65,\n\t\"moveRight\": 68\n}";
    
}