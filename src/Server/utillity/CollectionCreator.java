package Server.utillity;

import ch.qos.logback.classic.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

public class CollectionCreator {
    private CollectionCreator(){}

    public static CollectionManager load(String filePath, Logger logger) throws FileNotFoundException {
        CollectionManager collectionManager;
        if (filePath == null){
            collectionManager = new CollectionManager(new HashSet<>(), "");
            logger.info("Filepath is null. A new empty collection"
                    + "has been created.");
            return collectionManager;
        }
        File file = new File(filePath);
        if (file.exists() && file.length() != 0) {
            collectionManager = Parser.convertToJavaObject(file);
            collectionManager.setFilePath(filePath);
            logger.info("The collection was successfully loaded from the file " + filePath);
        } else {
            collectionManager = new CollectionManager(new HashSet<>(), filePath);
            if (!file.exists()) {
                logger.info("No file with this name was found. A new empty collection has been created.");
            } else {
                logger.info("The collection was successfully loaded from the file" + filePath);
            }
        }
        return collectionManager;
    }
}
