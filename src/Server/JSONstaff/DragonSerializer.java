package Server.JSONstaff;

import Common.classes.Dragon;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class DragonSerializer implements JsonSerializer<Dragon>
{
    public JsonElement serialize(Dragon src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject result = new JsonObject();

        result.addProperty("id", src.getId());
        result.addProperty("name", src.getName());
        result.add("coordinates", context.serialize(src.getCoordinates()));
        result.addProperty("creationDate", src.getCreationDate().toString());
        result.addProperty("age", src.getAge());
        result.addProperty("wingspan", src.getWingspan());
        result.addProperty("speaking", src.getSpeaking());
        result.addProperty("color", src.getColor().toString());
        result.add("cave", context.serialize(src.getCave()));

        return result;
    }
}
