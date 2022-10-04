package Server.commands;

import Common.classes.Dragon;
import Common.exceptions.InvalidCommandArguments;
import Common.exceptions.NumberOfArgumentException;
import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PrintByWingspanCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public PrintByWingspanCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("print_field_ascending_wingspan",
                "Выводит на экран элементы коллекции в порядке возрастания значения поля wingspan",
                CommandRequirement.DRAGON);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object) throws InvalidCommandArguments{
        if (!argument.isEmpty()) {
            throw new InvalidCommandArguments(argument);
        } else if(object != null){
            throw new InvalidCommandArguments(argument.toString());
        }
        Comparator<Dragon> compareByWingspan = (o1, o2) -> {
            if (o1.getWingspan() - o2.getWingspan() > 0){
                return 1;
            }
            else if(o1.getWingspan() - o2.getWingspan() == 0){
                return 0;
            }
            else{
                return -1;
            }
        };
        StringBuilder res = new StringBuilder();
        List<Dragon> list = new ArrayList<>(collectionManager.getCollection());
        list.sort(compareByWingspan);
        for(Dragon dragon : list){
            res.append(dragon.getWingspan());
        }
        commandManager.addToHistory(this);
        return new ServerResponse(res.toString(), ExecuteCode.VALUE);
    }
}
