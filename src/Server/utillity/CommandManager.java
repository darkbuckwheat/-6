package Server.utillity;

import java.util.ArrayList;
import java.util.HashMap;

import Client.utillity.DragonFiller;
import Common.utills.CommandRequirement;
import Server.commands.AbstractCommand;
import Server.commands.AddCommand;
import Server.commands.ClearCommand;

public class CommandManager {
    private final HashMap<String, AbstractCommand> commands = new HashMap<>();
    private final ArrayList<AbstractCommand> history = new ArrayList<>();
    private final HashMap<String, CommandRequirement> requirements = new HashMap<>();

    public CommandManager(CollectionManager collectionManager, DragonFiller dragonFiller) {
        ClearCommand clearCommand = new ClearCommand(collectionManager);
        commands.put(clearCommand.getName(), clearCommand);
        AddCommand addCommand = new AddCommand(collectionManager, dragonFiller, this);
        commands.put(addCommand.getName(), addCommand);
    }

    public HashMap<String, AbstractCommand> getCommands() {
        return commands;
    }

    public ArrayList<AbstractCommand> getHistory(){
        return history;
    }

    public HashMap<String, CommandRequirement> getRequirements() {
        return requirements;
    }

    public void addToHistory(AbstractCommand command){
        history.add(command);
        if (history.size() > 15){
            history.remove(0);
        }
    }
}
