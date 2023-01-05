package saveload;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.IOException;

import boardgame.Saveable;

/**
 * This class is a utility class that can save and load any object that implements the Saveable
 * interface
 */
public class DataSaveable {

    /**
     * This function takes a Saveable object and a path to a file, and writes the Saveable object's
     * string representation to the file. Any class that implements the Saveable interface will
     * have access to this method
     * 
     * @param toSave the object that is to be saved
     * @param absolutePath the path to the file you want to save to.
     */
    public static void save(Saveable toSave, String absolutePath) throws RuntimeException{
        Path path = FileSystems.getDefault().getPath(absolutePath);

        try{
            Files.writeString(path, toSave.getStringToSave());
        }catch(IOException e){
            throw new RuntimeException();            
        }
    }

    /**
     * This function takes a Saveable object and a path to a file, and loads the Saveable object's
     * string representation from the file. Any class that implements the Saveable interface will
     * have access to this method
     * 
     * @param toLoad The object that will be loaded.
     * @param absolutePath The absolute path to the file you want to load.
     */
    public static void load(Saveable toLoad, String absolutePath) throws RuntimeException{        
        Path path = FileSystems.getDefault().getPath(absolutePath);
        
        try{            
            String fileLines = String.join(",",Files.readAllLines(path));            
            toLoad.loadSavedString(fileLines);
        }catch(IOException e){            
            throw new RuntimeException();
        }
    }
}
