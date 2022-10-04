package Server.JSONstaff;

import Common.classes.DragonCave;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CaveSerializer implements JsonSerializer<DragonCave>
{
    @Override
    public JsonElement serialize(DragonCave cave, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject result = new JsonObject();
        result.addProperty("depth", cave.getDepth());
        return result;
    }
}