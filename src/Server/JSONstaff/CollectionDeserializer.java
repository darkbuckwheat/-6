package Server.JSONstaff;

import Server.utillity.CollectionManager;

import Common.classes.Dragon;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashSet;

public class CollectionDeserializer implements JsonDeserializer<CollectionManager> {
    public CollectionManager deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        HashSet<Dragon> collection = new HashSet();
        for(JsonElement dragon : jsonObject.get("collection").getAsJsonArray()) {
            collection.add(context.deserialize(dragon, Dragon.class));
        }
        try {
            return new CollectionManager(collection, "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
