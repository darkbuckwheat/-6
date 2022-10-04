package Server.commands;

import Common.classes.Dragon;
import Common.exceptions.IllegalKeyException;
import Common.exceptions.InvalidCommandArguments;
import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;

public class UpdateByIdCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public UpdateByIdCommand(CollectionManager collectionManager,
                             CommandManager commandManager){
        super("update", "Обновляет значения полей элемента коллекции по id", CommandRequirement.DRAGON);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object) throws IllegalKeyException, InvalidCommandArguments {
        if (argument == null || object == null || object.getClass() != Dragon.class) {
            throw new InvalidCommandArguments();
        }
        Long id = Long.valueOf(argument);
        if (!collectionManager.containsId(id)) {
            throw new IllegalKeyException("There's no value with that id.");
        }
        collectionManager.update(id, (Dragon) object);
        commandManager.addToHistory(this);
        return new ServerResponse(ExecuteCode.SUCCESS);
    }
}

