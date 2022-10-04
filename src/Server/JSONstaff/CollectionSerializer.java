package Server.JSONstaff;

import Common.classes.Dragon;

import Server.utillity.CollectionManager;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonArray;

import java.lang.reflect.Type;

public class CollectionSerializer implements JsonSerializer<CollectionManager> {
    public JsonElement serialize(CollectionManager collectionManager, Type typeOfSrc, JsonSerializationContext context){
        JsonObject result = new JsonObject();
        JsonArray col = new JsonArray();

        for(Dragon dragon : collectionManager.getCollection()) {
            col.add(context.serialize(dragon));
        }
        result.addProperty("currentId", collectionManager.getId());
        result.add("collection", col);
        //result.addProperty("filePath", collectionManager.getFilePath());
        //не думаю, что файл должен хранить в себе путь к себе же
        result.addProperty("initializationDate", collectionManager.getInitializationDate().toString());
        return result;
    }
}
