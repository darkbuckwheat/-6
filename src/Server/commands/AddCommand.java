package Server.commands;

import Client.exceptions.InvalidInputException;
import Client.exceptions.ScriptException;
import Client.utillity.DragonFiller;
import Common.classes.Dragon;
import Common.exceptions.IncorrectFieldValueException;
import Common.exceptions.InvalidCommandArguments;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;

import Common.utills.CommandRequirement;

public class AddCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public AddCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("add", "Добавляет элемент в коллекцию", CommandRequirement.DRAGON);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object)
            throws InvalidCommandArguments, InvalidInputException, IncorrectFieldValueException, ScriptException {
        if (!argument.isEmpty() || object == null){
            throw new InvalidCommandArguments();
        }
        collectionManager.addElement((Dragon) object);
        commandManager.addToHistory(this);
        return new ServerResponse(ExecuteCode.SUCCESS);
    }
}
