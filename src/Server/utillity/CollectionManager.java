package Server.utillity;

import Common.classes.Dragon;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.StringJoiner;


public class CollectionManager {
    private static long currentId = 0;
    private final HashSet<Dragon> collection;
    private final LocalDate initializationDate;
    private String filePath;

    public CollectionManager(HashSet<Dragon> collection, String filePath) {
        this.filePath = filePath;
        this.collection = collection;
        initializationDate = getDate();
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    public void setCurrentId(Long id){
        currentId = id;
    }

    public static long getNewId(){
        return currentId++;
    }

    public long getId(){
        return currentId;
    }


    public static LocalDate getDate(){
        return LocalDate.now();
    }

    public HashSet<Dragon> getCollection(){
        return collection;
    }

    public String getFilePath(){
        return filePath;
    }

    public void addElement(Dragon element){
        collection.add(element);
    }

    public Boolean containsId(Long id){
        System.out.println(collection.stream().anyMatch(d -> d.getId().equals(id)));
        return collection.stream().anyMatch(d -> d.getId().equals(id));
    }

    public void removeElementById(Long id){
        collection.removeIf(d -> d.getId().equals(id));
    }

    public Dragon getById(Long id){
        return collection.stream().filter(d -> d.getId().equals(id)).findFirst().get();
    }

    public void update(Long id, Dragon newDragon){
        Dragon old = getById(id);
        old.setName(newDragon.getName());
        old.setCoordinates(newDragon.getCoordinates());
        old.setAge(newDragon.getAge());
        old.setWingspan(newDragon.getWingspan());
        old.setSpeaking(newDragon.getSpeaking());
        old.setColor(newDragon.getColor());
        old.setCave(newDragon.getCave());
    }


    public void clear(){
        collection.clear();
    }

    public LocalDate getInitializationDate() {
        return initializationDate;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        if (collection.size() > 0) {
            collection.forEach((k) -> stringJoiner.add(k.toString()));
        } else {
            stringJoiner.add("The collection is empty");
        }
        return stringJoiner.toString();
    }
}
