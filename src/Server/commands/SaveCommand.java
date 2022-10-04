package Server.commands;

import Client.exceptions.InvalidInputException;
import Common.exceptions.InvalidCommandArguments;
import Common.exceptions.NumberOfArgumentException;
import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;
import Server.utillity.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public SaveCommand(CollectionManager collectionManager,
                       CommandManager commandManager){
        super("save", "Сохраняет коллекцию в файл", CommandRequirement.NONE);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object)
            throws IOException, NumberOfArgumentException, InvalidInputException, InvalidCommandArguments {
        if (!argument.isEmpty()) {
            throw new InvalidCommandArguments(argument);
        } else if(object != null){
            throw new InvalidCommandArguments(object.toString());
        }
        try{
            File file = new File(collectionManager.getFilePath());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(Parser.convertToJSON(collectionManager).getBytes());
            fileOutputStream.close();
            commandManager.addToHistory(this);
        } catch(FileNotFoundException e){
            return new ServerResponse(ExecuteCode.ERROR);
        }
        return new ServerResponse(ExecuteCode.SUCCESS);
    }
}
