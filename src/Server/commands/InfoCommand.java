package Server.commands;

import Common.exceptions.InvalidCommandArguments;
import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;

public class InfoCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public InfoCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("info", "Выводит информацию о коллекции", CommandRequirement.NONE);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object) throws InvalidCommandArguments {
        if (!argument.isEmpty()) {
            throw new InvalidCommandArguments(argument);
        } else if(object != null){
            throw new InvalidCommandArguments(argument.toString());
        }
        String res = "Некоторая информация о коллекции, с которой работает программа:" +
                "\nТип коллекции: " + collectionManager.getCollection().getClass().getName() +
                "\nДата инициализации: " + collectionManager.getInitializationDate() +
                "\nКоличество элементов: " + collectionManager.getCollection().size() +
                "\nФайл для сохранения: " + collectionManager.getFilePath();
        commandManager.addToHistory(this);
        return new ServerResponse(res, ExecuteCode.VALUE);
    }
}
