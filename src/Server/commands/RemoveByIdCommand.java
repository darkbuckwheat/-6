package Server.commands;

import Common.classes.Dragon;
import Common.exceptions.IllegalKeyException;
import Common.exceptions.InvalidCommandArguments;
import Common.exceptions.NumberOfArgumentException;
import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;

public class RemoveByIdCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public RemoveByIdCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("remove_by_id", "Удаляет из коллекции элемент с соответствующим id", CommandRequirement.NONE);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object) throws IllegalKeyException,
            InvalidCommandArguments, NumberOfArgumentException {
        if(argument.isEmpty()){
            throw new NumberOfArgumentException(this.getName(), 1, 0);
        } else if (argument.split(" ").length > 1) {
            throw new NumberOfArgumentException(this.getName(), 1, argument.split(" ").length);
        } else if (object != null){
            throw new InvalidCommandArguments();
        }
        Long id = Long.valueOf(argument);
        if (!collectionManager.containsId(id)) {
            throw new IllegalKeyException("There's no value with that id.");
        }
        collectionManager.removeElementById(id);
        commandManager.addToHistory(this);
        return new ServerResponse(ExecuteCode.SUCCESS);
    }
}
