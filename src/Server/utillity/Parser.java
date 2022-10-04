package Server.utillity;

import Common.classes.Coordinates;
import Common.classes.Dragon;
import Common.classes.DragonCave;

import Server.JSONstaff.CaveSerializer;
import Server.JSONstaff.CaveDeserializer;
import Server.JSONstaff.DragonSerializer;
import Server.JSONstaff.DragonDeserializer;
import Server.JSONstaff.CollectionSerializer;
import Server.JSONstaff.CoordinatesDeserializer;
import Server.JSONstaff.CoordinatesSerializer;
import Server.JSONstaff.CollectionDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.StringJoiner;

public class Parser {
    private Parser() {
    }

    public static CollectionManager convertToJavaObject(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        StringJoiner data = new StringJoiner("");
        while (scan.hasNextLine()){
            data.add(scan.nextLine());
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(CollectionManager.class, new CollectionDeserializer())
                .registerTypeAdapter(Dragon.class, new DragonDeserializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
                .registerTypeAdapter(DragonCave.class, new CaveDeserializer())
                .create();
        CollectionManager result = gson.fromJson(data.toString(), CollectionManager.class);
        result.setFilePath(file.getPath());
        return result;
    }

    public static String convertToJSON(CollectionManager data){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(CollectionManager.class, new CollectionSerializer())
                .registerTypeAdapter(Dragon.class, new DragonSerializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesSerializer())
                .registerTypeAdapter(DragonCave.class, new CaveSerializer())
                .create();
        return gson.toJson(data);
    }
}
