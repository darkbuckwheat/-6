package Server.commands;

import Client.exceptions.InvalidInputException;
import Client.exceptions.ScriptException;
import Common.classes.Dragon;
import Common.exceptions.IncorrectFieldValueException;
import Common.exceptions.InvalidCommandArguments;
import Common.exceptions.NumberOfArgumentException;
import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;

import java.util.ArrayList;
import java.util.List;

public class FilterLessThanSpeakingCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public FilterLessThanSpeakingCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("filter_less_than_speaking", "Выводит все элементы, у которых значение speaking меньше(?) заданного",
                CommandRequirement.DRAGON);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object)
            throws InvalidCommandArguments, InvalidInputException, IncorrectFieldValueException, ScriptException, NumberOfArgumentException {
        StringBuilder res = new StringBuilder();
        if (object != null) {
            throw new InvalidCommandArguments();
        } else if(argument.isEmpty()){
            throw new NumberOfArgumentException(this.getName(), 1, 0);
        } else if (argument.split(" ").length > 1) {
            throw new NumberOfArgumentException(this.getName(), 1, argument.split(" ").length);
        } else if (!argument.equals("true") && !argument.equals("false")){
            throw new InvalidCommandArguments(argument);
        }
        else {
            List<Dragon> list = new ArrayList<>(collectionManager.getCollection());
            list = list.stream().filter(o-> Boolean.parseBoolean(argument) && !o.getSpeaking()).toList();
            for(Dragon dragon : list){
                res.append(dragon.toString());
            }
            commandManager.addToHistory(this);
        }
        return new ServerResponse(res.toString(), ExecuteCode.VALUE);
    }
}
