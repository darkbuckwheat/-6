package Server.commands;

import Client.exceptions.InvalidInputException;
import Common.exceptions.InvalidCommandArguments;
import Common.exceptions.NumberOfArgumentException;
import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CollectionManager;

import java.io.IOException;

public class ExitCommand extends AbstractCommand{
    private final CollectionManager collectionManager;

    public ExitCommand(CollectionManager collectionManager) {
        super("exit", "завершить программу (без сохранения в файл)", CommandRequirement.NONE);
        this.collectionManager = collectionManager;
    }

    public ServerResponse execute(String argument, Object object) throws InvalidCommandArguments,
            InvalidInputException, NumberOfArgumentException, IOException {
        if (!argument.isEmpty()) {
            throw new InvalidCommandArguments(argument);
        } else if(object != null){
            throw new InvalidCommandArguments(object.toString());
        }
        return new ServerResponse(ExecuteCode.EXIT);
    }
}
