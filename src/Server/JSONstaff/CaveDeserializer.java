package Server.JSONstaff;

import Common.classes.DragonCave;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

public class CaveDeserializer implements JsonDeserializer<DragonCave> {
    public DragonCave deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject jsonObject = json.getAsJsonObject();
        return new DragonCave(jsonObject.get("depth").getAsInt());
    }
}
