package Server.commands;

import Common.exceptions.InvalidCommandArguments;
import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;

public class ShowCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public ShowCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("show", "Выводит все элементы коллекции в строковом представлении", CommandRequirement.NONE);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object)
            throws InvalidCommandArguments{
        if (!argument.isEmpty()) {
            throw new InvalidCommandArguments(argument);
        } else if(object != null){
            throw new InvalidCommandArguments(argument.toString());
        }
        commandManager.addToHistory(this);
        return new ServerResponse(collectionManager.toString(), ExecuteCode.VALUE);
    }
}
