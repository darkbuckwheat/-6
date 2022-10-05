package Server.commands;

import Common.exceptions.FileException;
import Common.exceptions.InvalidCommandArguments;
import Common.exceptions.NumberOfArgumentException;

import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;

import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;

import java.io.File;

public class ChangeSaveFile extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public ChangeSaveFile(CollectionManager collectionManager,
                       CommandManager commandManager){
        super("change_save_file", "Изменяет путь к файлу, в который будет сохранена коллекция", CommandRequirement.NONE);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object)
            throws NumberOfArgumentException, InvalidCommandArguments, FileException {
        if (argument.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 1, 0);
        } else if(object != null){
            throw new InvalidCommandArguments(object.toString());
        }
        File file = new File(argument);
        if (!file.exists() || !file.canRead() || !file.canWrite()) {
            return new ServerResponse(ExecuteCode.ERROR);
        }
        collectionManager.setFilePath(argument);
        commandManager.addToHistory(this);
        return new ServerResponse(ExecuteCode.SUCCESS);
    }
}
