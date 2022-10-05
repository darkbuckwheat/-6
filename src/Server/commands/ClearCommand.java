package Server.commands;

import Common.exceptions.InvalidCommandArguments;

import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;

import Server.utillity.CollectionManager;

public class ClearCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", CommandRequirement.NONE);
        this.collectionManager = collectionManager;
    }

    public ServerResponse execute(String argument, Object object) throws InvalidCommandArguments{
        if (!argument.isEmpty()) {
            throw new InvalidCommandArguments(argument);
        } else if(object != null){
            throw new InvalidCommandArguments(object.toString());
        }
        collectionManager.clear();
        return new ServerResponse(ExecuteCode.SUCCESS);
    }
}