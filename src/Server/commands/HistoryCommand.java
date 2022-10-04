package Server.commands;

import Common.exceptions.InvalidCommandArguments;
import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CommandManager;

public class HistoryCommand extends AbstractCommand{
    private final CommandManager commandManager;

    public HistoryCommand(CommandManager commandManager){
        super("history", "Выводит последние 15 команд без их аргументов", CommandRequirement.NONE);
        this.commandManager = commandManager;
    }

    public ServerResponse execute(String argument, Object object) throws InvalidCommandArguments {
        if (!argument.isEmpty()) {
            throw new InvalidCommandArguments(argument);
        } else if(object != null){
            throw new InvalidCommandArguments(argument.toString());
        }
        commandManager.addToHistory(this);
        return new ServerResponse(commandManager.getHistory().toString(), ExecuteCode.VALUE);
    }
}
