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

public class RemoveLowerCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public RemoveLowerCommand(CollectionManager collectionManager,
                                CommandManager commandManager){
        super("remove_lower", "Удаляет из коллекции элементы, меньшие, чем введённый", CommandRequirement.NONE);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object)
            throws InvalidCommandArguments, InvalidInputException, IncorrectFieldValueException,
            ScriptException, NumberOfArgumentException {
        if (object == null || object.getClass() != Dragon.class) {
            throw new InvalidCommandArguments();
        } else if (!argument.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 0, argument.split(" ").length);
        }
        List<Dragon> list = new ArrayList<>(collectionManager.getCollection());
        list = list.stream().filter(o-> o.compareTo((Dragon) object) < 0).toList();
        for (Long id : list.stream().map(Dragon::getId).toList()){
            collectionManager.removeElementById(id);
        }
        commandManager.addToHistory(this);
        return new ServerResponse(ExecuteCode.SUCCESS);
    }
}
